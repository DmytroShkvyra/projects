/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Decoder;

/*************************************************************************
 *  Compilation:  javac InplaceFFT.java
 *  Execution:    java InplaceFFT N
 *  Dependencies: Complex.java
 *
 *  Compute the FFT of a length N complex sequence in-place.
 *  Uses a non-recursive version of the Cooley-Tukey FFT.
 *  Runs in O(N log N) time.
 *
 *  Reference:  Algorithm 1.6.1 in Computational Frameworks for the
 *  Fast Fourier Transform by Charles Van Loan.
 *
 *
 *  Limitations
 *  -----------
 *   -  assumes N is a power of 2
 *
 *  
 *************************************************************************/

public class InplaceFFT {

    private static int reverse(int i) {
        // HD, Figure 7-1
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
            ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }
    
    private static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }    
    
    // compute the FFT of x[], assuming its length is a power of 2
    public static void fft(Complex[] x) {

        // check that length is a power of 2
        int N = x.length;

        // bit reversal permutation
        int shift = 1 + numberOfLeadingZeros(N);
      
        for (int k = 0; k < N; k++) {
            int j = reverse(k) >>> shift;
            if (j > k) {
                Complex temp = x[j];
                x[j] = x[k];
                x[k] = temp;
            }
        }

        // butterfly updates
        for (int L = 2; L <= N; L = L+L) {
            for (int k = 0; k < L/2; k++) {
                double kth = -2 * k * Math.PI / L;
                Complex w = new Complex(Math.cos(kth), Math.sin(kth));
                for (int j = 0; j < N/L; j++) {
                    Complex tao = w.times(x[j*L + k + L/2]);
                    x[j*L + k + L/2] = x[j*L + k].minus(tao); 
                    x[j*L + k]       = x[j*L + k].plus(tao); 
                }
            }
        }
    }


    public static double[] getSpectr(short[] frame, short[] numOfFreq){
    	double[] res = new double[numOfFreq.length];
    	int N = frame.length;
    	Complex[] x = new Complex[N];
        for (int i = 0; i < N; i++) {
            x[i] = new Complex(frame[i], 0);
        }
        fft(x);
        System.out.println("------");
        double sr = 0.0;
        for (int i = 0; i < 8; i++)
        	sr+= x[i*8+21].abs()/(8);
        
        double sumXx2 = 0.0;
        for (int i = 0; i < 8; i++)
        	sumXx2 += ((x[i*8+21].abs()-sr)*(x[i*8+21].abs()-sr));
        double sigma = Math.sqrt(sumXx2/sr);

        for (int i = 0; i < 8; i++){
        	if((x[i*8+21].abs()- sr) / sigma > 100)
        		System.out.println("SIG=\t"+((x[i*8+21].abs()- sr)/sigma)+"\t" + x[i].abs()+"\t"+i*2*(8000f/x.length)+"Hz");
        }	
        
    	for (int i = 0; i < numOfFreq.length; i++)
    		
    		res[i] = x[numOfFreq[i]].abs();
        
    	return res;
    }
    
    
    
    
    // test client
    public static void main(String[] args) { 
        int N = Integer.parseInt(args[0]);
        Complex[] x = new Complex[N];

        // original data
        for (int i = 0; i < N; i++) {
            x[i] = new Complex(Math.cos(Math.PI * 14 * i / 32)+ Math.cos(Math.PI * 58 * i / 32), 0);
            // x[i] = new Complex(-2*Math.random() + 1, 0);
        }
        for (int i = 0; i < N; i++)
            System.out.println(x[i]);
        System.out.println();

        // FFT of original data
        fft(x);
        for (int i = 0; i < N; i++)
            System.out.println(x[i]);
        System.out.println();
	
	for (int i = 0; i < N; i++)
            System.out.println(x[i].abs()+" "+i*2+" Hz");
        System.out.println();
	

    }

}

