package Decoder;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class GeneratedInputStreamFactory {

	private static double doubleP = 2.0 * Math.PI;
	private static int rate = 16000;
	private static int sampleSizeInBytes = 2;
	private static int channels = 1;
	private static int impFreq = 30;//20;
	private static Encoder/*Codec*/ encoder;
	private static byte[][] signal;
	private static byte[] header = getAUHeader();
	private static int bytesPerChar;
	private static int dataBytes;
	private static int totalBytes;
	private static ByteBuffer buffer;

	public static ByteArrayInputStream getInstance(String s, int j){
		encoder = /*new Codec();*/new Encoder();
		impFreq = j*2;
		bytesPerChar = (rate / impFreq)* channels * sampleSizeInBytes;

		signal = encoder.encode(s);//s.getBytes());

		dataBytes = bytesPerChar * signal.length;
		totalBytes = header.length + dataBytes;
		buffer = ByteBuffer.wrap(new byte[totalBytes]);
		buffer.clear();
		buffer.put(header);
		generate();
		buffer.flip();
		return new ByteArrayInputStream(buffer.array());
	}

	private static void generate(){
		for (int i = 0; i < dataBytes; i += sampleSizeInBytes * channels) {
			int wave = getWave(i);
			// wave = (wave > 0 ? 127 : -127);
			if (channels == 1) { 
				if (sampleSizeInBytes == 1) {
					buffer.put((byte) wave);
				} else if (sampleSizeInBytes == 2) {
					buffer.put((byte) wave);
					buffer.put((byte) (wave >>> 8));
				}
			} else if (channels == 2) {
				if (sampleSizeInBytes == 1) {
					buffer.put((byte) wave);
					buffer.put((byte) wave);
				} else if (sampleSizeInBytes == 2) {
					buffer.put((byte) wave);
					buffer.put((byte) (wave >>> 8));
					buffer.put((byte) wave);
					buffer.put((byte) (wave >>> 8));
				}
			}
		}
	}


	private static byte[] getAUHeader(){
		byte[] res = new byte[48];
		//magic word
		res[0] = 0x2e;
		res[1] = 0x73;
		res[2] = 0x6e;
		res[3] = 0x64;
		
		//data offset
		res[7] = 0x18;
		//lengs data
		res[8] = (byte) 0xff;
		res[9] = (byte) 0xff;
		res[10] = (byte) 0xff;
		res[11] = (byte) 0xff;
		//encoding
		res[15] = 0x03;
		
		//bitrate
		//res[16] = 0x2e;
		//res[17] = 0x2e;
		//8000
		//res[18] = 0x1f;
		//res[19] = 0x40;
		//16000
		res[18] = 0x3e;
		res[19] = (byte) 0x80;		
		
		// chanels
		res[23] = 0x01;
		return res;
	}

	
	
	private static double getWave(int tick, float frequency){
		return  Math.sin(doubleP * frequency * tick / (rate * sampleSizeInBytes * channels));
	}	

	private static int getWave(int tick, byte[] signal){
		double tmp = 0;
		int cnt = 0;
		for (int i = 0; i < signal.length; i++) {
			if(signal[i]>0){
				if((cnt+1)%2 > 0){
				 tmp += getWave(tick, encoder.getFreqs()[i]/*Codec.FREQUENCIES[i]*/);
				}else{
					tmp += Math.cos(doubleP * encoder.getFreqs()[i] * tick / (rate * sampleSizeInBytes * channels));
				}
				cnt++;
			}
			
		}
		return (cnt>0)?(int)((127.0/cnt) * tmp) : 0;
	}
	
	private static int getWave(int tick){
		return getWave(tick, signal[tick / bytesPerChar]);
	}			


}
