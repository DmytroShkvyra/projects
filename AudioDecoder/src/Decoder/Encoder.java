package Decoder;

import java.nio.ShortBuffer;
import java.util.Hashtable;


public class Encoder {
	private Hashtable map = new Hashtable();
	//private short[] freqs = new short[]{250, 500, 750, 1000, 1250, 1500, 1750, 2000};
	private short[] freqs = new short[]{697, 770, 852, 941, 1209, 1336, 1477, 1633/*, 440,480*/};
	//private short[] freqs = new short[]{697,706,713,721,732,750,777,815,865,929,1007,1101,1211,1336,1477,1633};
		

    public byte[][] encode(String s){
    	byte[][] res = new byte[s.length()*4][freqs.length];
    	for (int i = 0; i < s.length(); i++) {
    		res[i*4] = getMatrixByChar(""+s.charAt(i));
    		res[i*4+1] = getMatrixByChar(""+s.charAt(i));
    		res[i*4+2] = getMatrixByChar(""+s.charAt(i));
    		res[i*4+3] = new byte[]{-1,-1,-1,-1,-1,-1,-1,-1};
		}
    	
    	return res;
    }

	public static short offset = 1; 
	
	public Encoder() {
        //{697	770	852	941	1209	1336	1477	1633}
        
        map.put("1", new byte[]{1,-1,-1,-1,1,-1,-1,-1});
        map.put("2", new byte[]{1,-1,-1,-1,-1,1,-1,-1});
        map.put("3", new byte[]{1,-1,-1,-1,-1,-1,1,-1});
        map.put("4", new byte[]{-1,1,-1,-1,1,-1,-1,-1});
        map.put("5", new byte[]{-1,1,-1,-1,-1,1,-1,-1});
        map.put("6", new byte[]{-1,1,-1,-1,-1,-1,1,-1});
        map.put("7", new byte[]{-1,-1,1,-1,1,-1,-1,-1});
        map.put("8", new byte[]{-1,-1,1,-1,-1,1,-1,-1});
        map.put("9", new byte[]{-1,-1,1,-1,-1,-1,1,-1});
        map.put("A", new byte[]{1,-1,-1,-1,-1,-1,-1,1});
        map.put("B", new byte[]{-1,1,-1,-1,-1,-1,-1,1});
        map.put("C", new byte[]{-1,-1,1,-1,-1,-1,-1,1});
        map.put("D", new byte[]{-1,-1,-1,1,-1,-1,-1,1});
        map.put("0", new byte[]{-1,-1,-1,1,-1,1,-1,-1});
        map.put("*", new byte[]{-1,-1,-1,1,1,-1,-1,-1});
        map.put("#", new byte[]{-1,-1,-1,1,-1,-1,1,-1});
	}

	public Hashtable getMap() {
		return map;
	}

	public short[] getFreqs() {
		short[] buf = new short[freqs.length];
		for (int i = 0; i < buf.length; i++) {
			buf[i]=(short)(freqs[i]/offset);
		}
		return buf;
		//return freqs;
	}

	public float getFreq(int i) {
		float bitrate = 8000;
		return i*2*(bitrate/512);
	}	
	
	public short[] getFreqsByChar(String s){
		ShortBuffer buffer = ShortBuffer.wrap(new short[freqs.length]);
		buffer.clear();
		int j = 0;
		byte[] matrix = (byte[])map.get(s);
		short[]bufffreq = this.getFreqs();
		for (int i = 0; i < bufffreq.length; i++) {
			if(matrix[i]>0){
				buffer.put(bufffreq[i]);
				j++;
			}
		}
		buffer.flip();
		buffer.limit(j);
		return buffer.array();
	}
	
	public byte[] getMatrixByChar(String s){
		return (byte[])map.get(s);
	}
	
	
}
