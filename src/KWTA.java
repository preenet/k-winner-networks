/********************************************************************
 * Neural Networks       									
 * K-Winner Take All   
 * Pree Thiengburanathum    									
 * Due date: June 11, 2008        				
 ********************************************************************/

public class KWTA {
	/* initialize simulation parameters. */
	public static int N = 20; // number of neurons
	
	public static double[] step = {0.005, 0.025, 0.05, 0.1}; // step size
	public static double[] ext = {0.5, 1.5, 2.5}; // external input
	public static int maxIter = 5000; // number of iterations
	public static boolean random = false; // flag for initial activations to be all equal.
	
	public static void main(String args[]) {

		for(int i=0; i<step.length; i++) {
			for(int j=0; j<ext.length; j++) {
				System.out.println("Running step " + step[i] + " ext " + ext[j]);
				KWTASim mySim = new KWTASim(N, step[i], ext[j], maxIter, random);
				mySim.runSim();
			}
		}
	}// end main
}// end class KWTA 
