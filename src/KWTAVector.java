/********************************************************************
 * CSC5542: Neural Networks       									
 * Project: K-Winner Take All   
 * Pree Thiengburanathum    									
 * Due date: June 11, 2008        																	*
 ********************************************************************/
import java.util.*;
import java.io.*;

public class KWTAVector {
	private Vector<Double> v;
	private BufferedWriter out;
	private String outFile;

	public KWTAVector(int size, double step, double ext, String outFile) {
		this.v = new Vector<Double> (size);
		this.outFile = step + "s_" + ext + "a_" + outFile + ".csv";
		try {
			this.out = new BufferedWriter(new FileWriter(this.outFile));
		}catch(IOException e){}
		
		for(int i=0; i<size; i++)
			v.addElement(null);
	}// end constructor
	
	public void setValue(int index, double value) {
		v.setElementAt(value, index);
	}// end method setValue
	
	public double getValue(int index) {
		return this.v.elementAt(index);
	}// end method getValue
	
	public Vector<Double> getVector() {
		return this.v;
	}// end method getVector
	
	public void writeToFile() {
		try {
			out.write(this.toString());
		} catch(IOException e){}
	}// end method writeToFile
	
	public void clear() {
		v.clear();
		try {
			out.close();
		} catch(IOException e){}
		System.gc();
	}// end method clear

	public String toString() {
		String result = "";
		for(int i=0; i<v.size(); i++)
			result += this.v.elementAt(i).toString() + ',' + ' ';
		return result + '\n';
	}// end method toString
}// end class KWTAVector

