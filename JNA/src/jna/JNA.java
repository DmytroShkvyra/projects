
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jna;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 *
 * @author USer
 */
public class JNA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kernel32 lib = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
        /*lib.Beep(698, 500);
        lib.Sleep(500);
        lib.Beep(698, 500);*/
        int n = 1024*1024*1024;
        ByteBuffer buf = ByteBuffer.allocateDirect(n);
        //float[] matrix = buf.asFloatBuffer().array();
        System.out.println(new Date());
        int k = 0;
        for (int j = 0; j < 100; j++) {
            

        for (int i = 0; i < n/4; i++) {
           buf.asFloatBuffer().put(i);
           k = i;
        }
      
        }
/*buf.putShort(0, (short)0x123);
buf.putInt(2, 0x12345678);
buf.putLong(8, 0x1122334455667788L);*/

System.out.println(k);
        System.out.println(new Date());
    }
    
    public interface Kernel32 extends Library {
       // FREQUENCY is expressed in hertz and ranges from 37 to 32767
       // DURATION is expressed in milliseconds
       public boolean Beep(int FREQUENCY, int DURATION);
       public void Sleep(int DURATION);
   }
    
}
