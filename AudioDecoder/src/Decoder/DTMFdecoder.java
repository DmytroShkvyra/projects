/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Decoder;

import java.util.Vector;


/**
 *
 * @author USer
 */
public class DTMFdecoder {
Vector vector = new Vector();
    
    public IEventHandler handler;
    

    private String[] freqs = new String[]{"697Hz\t","770Hz\t","852Hz\t","941Hz\t","1209Hz\t","1336Hz\t","1477Hz\t","1633Hz\t","1800Hz\t"};
    private String[] chars = new String[]{"1","2","3","A","4","5","6","B","7","8","9","C","*","0","#","D"};
    private Detector detector = new Detector(8000, new Encoder().getFreqs());
    private String prevRes = "";
    double prevSignalPower = 0;
    public DTMFdecoder() {

    }
    

    
  public String decode(short[] frame){
	  
	 double[] coefs = detector.getSpectr(frame);
	 //double[] coefs = InplaceFFT.getSpectr(frame, new short[]{21,22,23,24,25,26,27,28});
	 double[] hi = new double[4];
	 double[] low = new double[4];

     String res = "";
     double tmp = 0;
     //int indTmp = 0;
     //double signalPower = 0;
     
     
	 
	 for (int i = 0; i < 4; i++) {
		 low[i] = coefs[i];
		 hi[i] = coefs[i+4];
	 }
	 
	 double sumPower = 0;	 
	 int ind = 0;
		for (int j = 0; j < low.length; j++) {
			//Filter lowFilter = detector.filters[j];
			for (int k = 0; k < hi.length; k++) {
				//Filter hiFilter = detector.filters[k+4];
				double mult = Math.sqrt(low[j]*hi[k]);
				sumPower += mult/16;
				if(tmp < mult){
					tmp = mult;
					res = chars[ind];
					//indTmp = ind;
				}
				ind++;
			}
		}
		
	 sumPower -= tmp/16;
	 sumPower = tmp/sumPower;

          
          if((sumPower-prevSignalPower) > 0){
        	  prevSignalPower = sumPower;
        	  //System.out.println(sumPower);
          }else {
        	  if(!prevRes.equals(res) && ((prevSignalPower) > 5)){    
           	   prevRes = res;
           	   if(handler != null) handler.eventHandler(res);
           	  }
        	  prevSignalPower = sumPower;
          }

      
          /*if(handler != null && ((sumPower) > 4.5)){
        	  prevSignalPower = sumPower;
        	  if(!prevRes.equals(res)){
        	   prevRes = res;
        	   handler.eventHandler(res);
        	  }
          } */    
      
      //if(tmp<=/*q*3.2*//*2.9*/200000/*0000*/){
   /* 	  res = "";
          tmpRes = res;
          //printSpectr(coefs);
      }
      else{
          if(handler != null && (tmpRes == null ? res != null : !tmpRes.equals(res))){
              handler.eventHandler(res);
              tmpRes = res;
              //System.out.println(tmp);
              //printSpectr(coefs);
          }
          
      } */
      
     return res; 
  }
  
  public void printSpectr(double[] coefs){
      String res = "\t";
      for (int i = 0; i < coefs.length; i++) {
          res += freqs[i]+coefs[i] + "\t";
          
      }
      System.out.println(res);
      
  } 
  


  
  
}
