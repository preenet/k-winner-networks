# k-winner-networks
A K-Winner network should converge to a binary state: all neural activations at either the maximum or minimum activations. The neurons that converge to the maximum state (the "winners") should correspond to the neurons with the largest initial activation.

A K-Winner network should converge to a binary state: all neural activations at either the maximum or minimum activations. The neurons that converge to the maximum state (the "winners") should correspond to the neurons with the largest initial activations.
The architecture and equations of motion for a K-Winner network are given below. Specific values for the parameters are given, (e.g.: N=20) while the step size (step) and the external input (ext) are treated as simulation variables.
Write a computer program (any language) that simulates the network performance for the 12 cases specified by the choices of step and ext.
For the chosen parameter settings, the simulation should:

1. Randomly initialize each neuron's activation, between the min and max activation.
2. Update the activations at each iteration, synchronously (all neurons update at the same time).
3. Calculate the energy of the network at each iteration.
4. Halt the program when the network has converged.
 
##Network Architecture:

N = Number of neurons.
Connection strengths:
The connection strength matrix: W = (wij):
wij = connection strength between neuron i and neuron j.
Connection strengths are symmetric: wij = wji.
wij = -1 if i is not equal to j
wii = 0 (no self-connections)
W is an nxn symmetric matrix.
Each neuron has an "activation" bounded by maximum and minimum values:
m <= ai <= M i = 1, ..., N
All of the activations can be expressed as a single N-dimensional vector: a.
Each neuron has an "external input":
m <= ei <= M i = 1, ..., N
All of the external inputs can be expressed as an N-dimensional vector: e.
 
##Equations of Motions:

at time t, the ith neuron has:
activation ai(t).
external input ei(t).
net input:
net i(t) = [Wa(t) + e(t)]i
Maximum activation of: M = 1
Minimum activation of: m = 0
Note that net i(t) is just the weighted sum of all the activations times connections strengths (plus the external input):
 
##Updating of the neural activation is done synchronously:
ai(t+1) = ai(t) +step*(M-ai(t))* (ai(t) - m) * neti(t).
Where "step" is a numerical "step size" and its value is one of the variables of the simulation, but a rule of thumb is that that it should be smaller as N gets bigger.
Intuition: the "net" factor is trying to drive the system into lower energy states, while the (M-a)(a-m) factor is trying to force the activation vector to converge to a hypercube corner (keeping it inside the hypercube).
 
##Energy Function:

At each time t, the energy of the network is defined to be:
E(t) = - (1/2) aT W a - aT e
 
##Simulation:

Set up the network and systematically test it with the following parameter ranges:
M = 1
m = 0
N = 20
For step, try these values:
step = step1 = (0.1) * (1/N)
step = step2 = (0.5) * (1/N)
step = step3 = (1.0) * (1/N)
step = step4 = (2.0) * (1/N)
For the external input, try these values:
ext = ext1 = 0.5
ext = ext2 = 1.5
ext = ext3 = 2.5
Note: the external inputs are the same for each neuron: ei = ej = ext).
For each simulation run, randomly initialize the activations
ai(0) = random values between m and M.
Note: Each neuron gets its own random value. If you set all the initial activations to the same value the network will "hang" -- try it and see what happens.

##How to compile:
prmpt$>javac KWTA.java
prmpt$>java KWTA
 
or
 
prmpt$> java -jar kwta.jar
 
Example of console output:
Running step 0.0050 ext 0.5
Running step 0.0050 ext 1.5
Running step 0.0050 ext 2.5
Running step 0.025 ext 0.5
Running step 0.025 ext 1.5
Running step 0.025 ext 2.5
Running step 0.05 ext 0.5
Running step 0.05 ext 1.5
Running step 0.05 ext 2.5
Running step 0.1 ext 0.5
Running step 0.1 ext 1.5
Running step 0.1 ext 2.5

## Data 
[Download an executable file here](http://preet.sesolution.com/CSC5542/p1/kwta.jar) (compiled the jar file with jdk1.6.0_06)
[Download raw data here](http://preet.sesolution.com/CSC5542/p1/rawdata.html)

##References:
[1]W.J. Wolfe, D. Mathis, C. Anderson, J. Rothman, M. Gottler, G.Brad, "K-Winner Networks", IEEE Transactions on neural networks, vol. 2, no. 2, march 1991
[2]Mark Ottenberg's work, http://www.riverrock.org/~mark/edu/csc5542/proj1/, K-winner network, 2005
