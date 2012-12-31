package Decoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author USer
 */
public class Codec {

	public static final short[] FREQUENCIES = new short[] { 697, 706, 713, 721,
			732, 750, 777, 815, 865, 929, 1007, 1101, 1211, 1336, 1477, 1633 };
	public static final short[] walsh = new short[] { 0x5555, 0x3333, 0x6666,
			0x0F0F, 0x5A5A, 0x3C3C, 0x6969 };
	private static final short[] templates = new short[] { 0x0000, 0x00FF,
			(short) 0xFF00, (short) 0xFFFF };

	private byte[] debrain = new byte[]{
			0x05,0x0B,0x17,0x0E,0x1C,0x19,0x12,0x05,0x0B

	};

	public IEventHandler handler;

	public short[] splitToBits(byte[] b) {
		short[] res = new short[b.length * 7];
		for (int i = 0; i < b.length; i++) {
			byte crc = 0;
			boolean isFirst = true;
			byte crcByte = b[i];
			for (int j = 0; j < 8; j++) {
				byte bit = (byte) ((crcByte & 0x80) >> 7);
				crcByte = (byte) (crcByte << 1);
				if(bit==1){
					if(isFirst){
						isFirst = false;
						crc = debrain[j];
					}else{
						crc = (byte) (crc ^ debrain[j]);
					}
					
				}
			}
			if(i%2 > 0){
				if(isFirst){
					isFirst = false;
					crc = debrain[8];
				}else{
					crc = (byte) (crc ^ debrain[8]);
				}
			}
			System.out.println(">>>>>>>>crc="+crc);
			short oneByte = (short) (b[i] | 0x0000);
			oneByte = (short) ((oneByte<<8)|((short)(i%2)<<7)| ((short)crc << 2));
			for (int j = 0; j < 7; j++) {
				byte bits = (byte) ((((oneByte << j * 2) & 0xC000)) >> 14);
				res[i * 7 + j] = templates[bits];
			}
		}

		return res;
	}

	public short[] encodeChanel(short b, short w) {
		short code = (short) ~(b ^ w);
		short res[] = new short[16];
		for (int i = 0; i < res.length; i++) {
			res[i] = (short) ((0x8000 & (code << 1 * i)) >> 15);
			res[i] = (short) ((res[i] == 0) ? -1 : 1);

		}
		return res;
	}

	public double[][] encode(byte[] b) {
		short[] tmp = splitToBits(b);
		short[][] packet = new short[(tmp.length / 7)][walsh.length];
		for (int i = 0; i < packet.length; i++) {
			int k = 0;
			for (int j = i * (walsh.length); j < (walsh.length)	* (i + 1); j++) {
				packet[i][k] = tmp[j];
				k++;
			}
		}
		double[][] signals = new double[packet.length][];
		for (int i = 0; i < packet.length; i++) {
			short[] ses = packet[i];
			double[] signal = new double[FREQUENCIES.length];
			double min = 0;
			double max = 0;
			for (int j = 0; j < ses.length; j++) {
				short[] k = encodeChanel(ses[j], walsh[j]);
				for (int l = 0; l < k.length; l++) {
					signal[l] += k[l];

				}
			}

			for (int j = 0; j < signal.length; j++) {
				if (signal[j] < min) {
					min = signal[j];
				}
				if (signal[j] > max) {
					max = signal[j];
				}
			}
			for (int j = 0; j < signal.length; j++) {
				signal[j] = (signal[j] - min) / (max - min);

			}
			signals[i] = signal;
		}

		return signals;
	}

	public void decode(double[] signal) {
		byte[]digits = new byte[7];
		double power = decodeChanels(signal, digits);
		if(power > 70){
			byte crcFact = (byte) (digits[4]<<2);
			crcFact = (byte) ((crcFact | digits[5])<<2);
			crcFact = (byte) (crcFact | digits[6]);
			
			byte takt = (byte) ((crcFact & 0x08)>>5);
			crcFact = (byte) ((crcFact<<1)>>1);
			byte res = (byte) (digits[0]<<2);
			res = (byte) ((res | digits[1])<<2);
			res = (byte) ((res | digits[2])<<2);
			res = (byte) (res | digits[3]);
			
		

			System.out.print("\tBYTE="+res+"\t");
			System.out.print("\tcrcFact="+crcFact+"\t");
			short resWithTakt = (short) ((((short)res | 0x0000)<< 8)|((takt| 0x0000) << 7)) ;
			

			byte crc = 0;
			boolean isFirst = true;
			for (int i = 0; i < 9; i++) {
				byte bit = (byte) ((resWithTakt & 0x8000) >> 15);
				resWithTakt = (short) (resWithTakt << 1);
				if(bit==1){
					if(isFirst){
						isFirst = false;
						crc = debrain[i];
					}else{
						crc = (byte) (crc ^ debrain[i]);
					}
					
				}				
			}
			System.out.print("crc="+crc+"\t");
			if((crc == crcFact) && handler != null) {
				//handler.eventHandler(res);
				System.out.print("OK\t");
			}
			else System.out.print("ERROR\t");
			
			for (int i = 0; i < 4; i++) {
				String s = Integer.toBinaryString(digits[i]);
				if(s.length()==1) s = "0"+s;
				System.out.print(s);
			}
			System.out.print("\t");
			for (int i = 4; i < 7; i++) {
				String s = Integer.toBinaryString(digits[i]);
				if(s.length()==1) s = "0"+s;
				System.out.print(s);
			}
			
			System.out.print("\t"+takt+"\n");
		}	
		
	}

	public byte decodeChanel(double[] signal, int chanel, double[] power) {
		byte res = 0;
		double[] sw = new double[signal.length];
		double[] wArray = walshToArray(walsh[chanel]);
		double[] tmp = new double[2];
		for (int i = 0; i < sw.length; i++) {
			sw[i] = signal[i] * wArray[i];

		}
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < 8; j++) {
				tmp[i] += sw[j + (i * 8)];
			}
			power[chanel] += Math.abs(tmp[i]);
				if (tmp[i] >= 0) {
					res = (byte) ((res << 1) | 0x01);
				} else {
					res = (byte) (res << 1);
				}
		}
		power[chanel] = power[chanel]/tmp.length;
		return res;
	}

	public double decodeChanels(double[]signal, byte[]digits){
		double power = 0;
		double[] powers = new double[7];
		for (int i = 0; i < powers.length; i++) {
			digits[i] = decodeChanel(signal,i,powers);
			power += powers[i]/powers.length;
		}
		return power;
	}
	
	public double[] walshToArray(short w) {
		double[] res = new double[16];
		for (int i = 0; i < res.length; i++) {
			res[i] = ((0x8000 & (w << 1 * i)) >> 15);
			res[i] = (res[i] == 0) ? -1 : 1;
		}

		return res;
	}
}
