/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Decoder;



/**
 *
 * @author USer
 */
public class Detector {

    private int bitrate;
    public Filter[] filters;

    public Detector(int bitrate, short[] freqs) {
        this.bitrate = bitrate;
        filters = new Filter[freqs.length];
        for (int i = 0; i < freqs.length; i++) {
            filters[i] = new Filter(freqs[i]);
        }
    }

    
    public double[] getNormalizedSpectr(){
        double[] spectr = new double[filters.length];
        double avg = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < filters.length; i++) {
            spectr[i] = filters[i].magnitude;
            avg += spectr[i]/spectr.length;
            if(spectr[i]>max) max = spectr[i];
            if(spectr[i]< min) min = spectr[i];
        }

        /*for (int i = 0; i < filters.length; i++) {
          spectr[i] = (spectr[i]/max);// - (min/max);
          filters[i].setMagnitude(spectr[i]);
        }*/
        
        return spectr;
    }    

    public static short byte2short(byte b1, byte b2, boolean bigEndian) {
        if (bigEndian) {
            return (short) ((b1 << 8) | (b2));
        } else {
            return (short) ((b2 << 8) | (b1));
        }
    }

    public static int short2int(byte b1, byte b2, byte b3, boolean bigEndian) {
       int w1 = byte2short(b1, b2, bigEndian);
       int w2 = byte2short(b3, (byte)0, bigEndian);
            return (int) (w1 | w2);
    }

    
    public double[] getSpectr(byte[] sample, boolean bigEndian, int type) throws Exception {
        if(type == 1){
           return getSpectr(sample); 
        }else if(type == 2){
                short[] shortsArray = new short[sample.length / 2];
                for (int i = 0; i < sample.length / 2; i++) {
                    shortsArray[i] = byte2short(sample[i * 2], sample[i * 2 + 1], bigEndian);
                }
                return getSpectr(shortsArray);            
        }else if(type == 3){
                int[] intArray = new int[sample.length / 4];
                for (int i = 0; i < sample.length / 2; i++) {
                    intArray[i] = short2int(sample[i * 4], sample[i * 4 + 1], sample[i * 4 + 2], bigEndian);
                }
                return getSpectr(intArray);            
        }else{
                throw new Exception("Undefined integer type");            
        }
        
    }

    public double[] getSpectr(int[] sample) {
        double[] spectr = new double[filters.length];
        for (int i = 0; i < filters.length; i++) {
            spectr[i] = filters[i].getMagnitude(sample);
        }
        return spectr;
    }

    public double[] getSpectr(short[] sample) {
        double[] spectr = new double[filters.length];
        for (int i = 0; i < filters.length; i++) {
            spectr[i] = filters[i].getMagnitude(sample);
        }
        return spectr;
    }

    public double[] getSpectr(byte[] sample) {
        double[] spectr = new double[filters.length];
        for (int i = 0; i < filters.length; i++) {
            spectr[i] = filters[i].getMagnitude(sample);
        }
        return spectr;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < filters.length; i++) {
            res += filters[i] + ", ";
        }
        return res;
    }

    class Filter {

        private short frequency;
        private double omega;
        private double Q1;
        private double Q2;
        public RealImag realImag = new RealImag();
        //private int k;
        private double coeff;
        private double sine;
        private double cosine;
       // private int N;
        private double magnitude;
        

        public double getMagnitude() {
			return magnitude;
		}

		public Filter(short frequency) {
            this.frequency = frequency;
            this.omega = (2.0 * Math.PI * frequency / bitrate);
            this.sine = Math.sin(omega);
            this.cosine = Math.cos(omega);
            this.coeff = 2.0 * cosine;
        }

        public void processSample(int sample) {
            double Q0;
            Q0 = coeff * Q1 - Q2 + sample;
            Q2 = Q1;
            Q1 = Q0;
        }
        
        private void init(int length){
            this.Q1 = 0;
            this.Q2 = 0;
            //this.N = length;
            //this.k = ((N * frequency) / bitrate);           
        }
       
        
        public double getMagnitude(int[] sample) {
            init(sample.length);
            for (int i = 0; i < sample.length; i++) {
                processSample(sample[i]);
            }
            double real = realImag.getImag();
            double image = realImag.getReal();
            this.magnitude = Math.sqrt((image * image) + (real * real));
            return this.magnitude;
        }

        public double getMagnitude(short[] sample) {
            init(sample.length);
            for (int i = 0; i < sample.length; i++) {
                processSample(sample[i]);
            }
            double real = realImag.getImag();
            double image = realImag.getReal();
            this.magnitude = Math.sqrt((image * image) + (real * real));
            return this.magnitude;
        }

        public double getMagnitude(byte[] sample) {
            init(sample.length);
            for (int i = 0; i < sample.length; i++) {
                processSample(sample[i]);
            }
            double real = realImag.getImag();
            double image = realImag.getReal();
            this.magnitude = Math.sqrt((image * image) + (real * real));
            return this.magnitude;
        }

        public String toString() {
            return "" + frequency + "Hz=" + magnitude;
        }

        public class RealImag {

            public double getReal() {
                return (Q1 - Q2 * cosine);
            }

            public double getImag() {
                return (Q2 * sine);
            }
        }
    }
}
