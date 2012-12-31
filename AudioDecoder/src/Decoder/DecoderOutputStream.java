package Decoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;


public class DecoderOutputStream extends ByteArrayOutputStream 
{
	private ByteBuffer buffer = ByteBuffer.allocateDirect(400000);
	private ShortBuffer shortBuffer;
	private IEventHandler handler;
	//private Detector detector;
	private DTMFdecoder/*Codec*/ fdecoder = /*new Codec();*/new DTMFdecoder();
	private int frameSize;

	
	
	public DecoderOutputStream(int frameSize) {
		this.frameSize = frameSize;
		//detector = new Detector(8000, new Encoder().getFreqs());
		//detector = new Detector(8000, new Codec().FREQUENCIES);
	}


	public void setHandler(IEventHandler handler) {
		this.handler = handler;
		fdecoder.handler = this.handler;
	}

	
	public synchronized void close() throws IOException {
		buffer.clear();
	}

	public synchronized void reset() {
		buffer.clear();

	}

	public int size() {
		return buffer.capacity();
	}

	public synchronized byte[] toByteArray() {
		return buffer.array();
	}

	public String toString() {
		return buffer.toString();
	}

	public synchronized void write(byte[] arg0, int arg1, int arg2) {

		buffer.put(arg0, arg1, arg2);
		buffer.flip();
		shortBuffer = buffer.asShortBuffer();
		
		//ShortBuffer frameBuffer = ShortBuffer.wrap(new short[frameSize]);
		short[] frame = new short[frameSize];
		
		while(shortBuffer.remaining() >= frameSize){

			shortBuffer.get(frame);
			fdecoder.decode(frame);
			shortBuffer.position(shortBuffer.position()-(frameSize/4)*3);
			
			//fdecoder.decode(detector.getNormalizedSpectr());
		}
		/*ShortBuffer frameBuffer = ShortBuffer.wrap(new short[frameSize]);
		short[] halfBuff = new short[frameSize/2];
		short[] frame = new short[frameSize/2];
		while(shortBuffer.remaining() >= frameSize){
			frameBuffer.clear();
			
			shortBuffer.get(frame);
			frameBuffer.put(halfBuff);
			frameBuffer.put(frame);
			shortBuffer.get(halfBuff);
			frameBuffer.flip();
			
			fdecoder.decode(frameBuffer.array());
			//fdecoder.decode(detector.getNormalizedSpectr());
		}*/
		frame = new short[shortBuffer.remaining()];
		byte[] tail = new byte[frame.length*2];
		shortBuffer.get(frame);
		for (int i = 0; i < frame.length; i++) {
			byte b1 = (byte)(frame[1] & 0xff);
			byte b2 = (byte)((frame[1] >> 8) & 0xff);
			tail[i*2] = b1;
			tail[i*2+1] = b2;
		}
		buffer.clear();
		buffer.put(tail);
		
	}

	public synchronized void write(int value) {
		buffer.put((byte)value);
	}

}
