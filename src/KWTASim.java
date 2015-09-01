/********************************************************************
 * CSC5542: Neural Networks       									
 * Project: K-Winner Take All   
 * Pree Thiengburanathum    									
 * Due date: June 11, 2008        																	*
 ********************************************************************/
import java.util.*;

public class KWTASim {
	private int N;		 		 // number of neuron
	private int maxIter; 		 // number of maximum iterations represents time tick
	private int M = 1;			 // maximum value
	private int m = 0;           // minimum value
	private double step;         // value of step side
	private double ext;          // value of external input
	private double[][] Wmatrix;  // N x N matrix
	private boolean random;

	private KWTAVector activation;
	private KWTAVector net;
	private KWTAVector energy;
	
	public KWTASim(int N, double step, double ext, int maxIter, boolean random) {
		this.N = N;
		this.maxIter = maxIter;
		this.step = step;
		this.ext = ext;
		this.random = random;
		Wmatrix = new double[N][N];
		activation = new KWTAVector(N, step, ext, "activation");
		net = new KWTAVector(N, step, ext, "net");
		energy = new KWTAVector(N, step, ext, "energy");

	}// end default constructor
	
	public void runSim() {
		initNetworkArchitecture();
		for(int i=0; i<maxIter; i++) {	
			calculateNet();
			updateActivation();
			calculateEnergy();
		}

		activation.clear();
		net.clear();
		energy.clear();

	}// end method runKWTASim
	
	public void initNetworkArchitecture() {
	     /*  
		  * The connection strength matrix: W = (wij):
		  * wij = connection strength between neuron i and neuron j.
		  * Connection strengths are symmetric: wij = wji.
		  * wij = -1 if i is not equal to j
		  * wii = 0 (no self-connections)
		  * W is an nxn symmetric matrix.
	      */
		
		for(int i=0; i< N; i++) {
			for(int j=0; j< N; j++) {
				if(i!=j)
					Wmatrix[i][j] = -1;
			}
		}
		/*  
	  	 * Create activation matrix
	 	 * All of the activations can be expressed as a single N-dimensional vector.
	 	 * m <= ai <= M for i = 1 to N
	 	 * Randomly initialize each neuron's activation, between the
	 	 * min and max activation ( min = 0, max = 1)
	 	 * There are 2 initial setup for random and equal
		 */
		if(random) {
			double r = 0.0;
			
			Random generator = new Random();
			r = generator.nextDouble();
			for(int i=0; i< N; i++) {
				activation.setValue(i, r);
				r = generator.nextDouble();
			}
			activation.writeToFile();
		}else {
			for(int i=0; i< N; i++) 
				activation.setValue(i, 0.5);
		}
	}// end method createNetworkArchitecture
	
	private void calculateNet() {
		/* Calculate the net input vector.
		 * Neti = Sum(Wij * aj) + ext
		 * Neti is just the weighted sum of all activations times connections strengths
		 * plus the external input.
		 */
		for(int i=0; i< N; i++) {
			double value = 0.0;
			for(int j=0; j< N; j++)  {
				value += Wmatrix[i][j] * activation.getValue(j);
			}
			net.setValue(i, value + ext);
		}
		net.writeToFile();
	}// end calculateNet
	
	private void updateActivation() {
		/*
		 * Calculate the new activation vector.
		 * Updating of the neural activation is done synchronously.
		 */
		for(int i=0; i< N; i++) {
			activation.setValue(i, activation.getValue(i) + step* (M-activation.getValue(i))* 
					(activation.getValue(i)-m) * net.getValue(i));			
		}
		activation.writeToFile();
	}// end method updateActivation
	
	public void calculateEnergy() {
		/*
		 * Calculate the energy vector.
		 * At each time t, the energy of the network is defined to be
		 * E(t) = -(1/2) aT W a - aTe
		 */
		double secondTerm = 0.0;
		double firstTerm = 0.0;
		int i = 0;
		while(i != N) {
			for(int j=0; j< N; j++) 
				firstTerm += activation.getValue(i) * Wmatrix[i][j] * activation.getValue(j);
			
			secondTerm += activation.getValue(i) * ext; 	
			energy.setValue(i, ((-0.5) * firstTerm) - secondTerm);
			i++;
		}// end while
		energy.writeToFile();
	}// end method calculateEnergy
	
}// end class KWTASim
