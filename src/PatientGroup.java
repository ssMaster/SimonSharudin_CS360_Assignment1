
/*
 *  This class is meant to provide functionality to a group of patients
 */

import java.util.ArrayList;

public class PatientGroup { 
	// fields 
	private int totalCholesterolSum;
	private int hdlSum;
	private int ldlSum;
	private int triglycerideSum;
	private double ratioSum;

	private ArrayList<Patient> group;	// Too hold a list of patients
	
	/**
	 * Constructor: initialize values
	 */
	public PatientGroup(){
		group = new ArrayList<Patient>();
		totalCholesterolSum = 0;
		hdlSum = 0;
		ldlSum = 0;
		triglycerideSum = 0;
		ratioSum = 0;
	}
	
	// ---------------------------------------------- getters
	public int getTotalCholesterolSum(){
		return totalCholesterolSum;
	}
	
	public int getHDLSum(){
		return hdlSum;
	}
	
	public int getLDLSum(){
		return ldlSum;
	}
	
	public int getTriglycerideSum(){
		return triglycerideSum;
	}
	
	public double getRatioSum(){
		return ratioSum;
	}
	
	public ArrayList<Patient> getGroup(){
		return group;
	}
	// --------------------------------------------------------
	
	/**
	 * addToGroup: add a patient to an ArrayList called 'group'
	 * @param p
	 */
	public void addToGroup(Patient p){
		group.add(p);
	}
	
	/**
	 * adds up the sums for the patients in the group
	 */
	public void makeGroupSums(){
		int index;
		
		for(index = 0; index < group.size(); index++){
			totalCholesterolSum += group.get(index).getTotalCholesterol();
			hdlSum += group.get(index).getHDL();
			ldlSum += group.get(index).getLDL();
			triglycerideSum += group.get(index).getTriglycerides();
			ratioSum += group.get(index).getCholesterolRatio();
		}
	}
	
	/**
	 * getAverage: return the average of a number as an integer with integer division
	 * @param sum - The total sum
	 * @param n - The number of contributing factors to the sum
	 * @return - The average to return
	 */
	public int getAverage(int sum, int n){
		return (sum / n);
	}
	
	/**
	 * getAverage: returns the average as a double 
	 * @param sum - The total sum 
	 * @param n - The number of contributing factors to the sum
	 * @return - The average to return
	 */
	public double getAverage(double sum, int n){
		return Math.round((double) (sum / n) * 10.0) / 10.0;
	}
	
	/**
	 * getDifferences: returns the difference between two numbers - double version
	 * @param a - The value being subtracted, in this case, the Control Group
	 * @param b - The value to subtract from, in this case, the Treatment Group
	 * @return - The difference between the two values: a and b
	 */
	public double getDifferences(double a, double b){
		return Math.round((double) (b - a) * 10.0) / 10.0;
	}
	
	/**
	 * getDifferences: returns the difference between two numbers - integer verion
	 * @param a - The value being subtracted, in this case, the Control Group
	 * @param b - The value to subtract from, in this case, the Treatment Group
	 * @return - The difference between the two values: a and b
	 */
	public int getDifferences(int a, int b){
		return b - a;
	}
	
	/**
	 * getAverageList: creates a list to hold all of the averages for all of the different values
	 * @return - The list of averages
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList getAverageList(){
		ArrayList list = new ArrayList();
		int size = group.size();
		
		list.add(getAverage(totalCholesterolSum, size));
		list.add(getAverage(hdlSum, size));
		list.add(getAverage(ldlSum, size));
		list.add(getAverage(triglycerideSum, size));
		list.add(getAverage(ratioSum, size));
		
		return list;
	}
	
	/**
	 * displayReport: compose a report for the groups
	 * @return - A string report for the averages for the groups
	 */
	public String displayAverageReport(){
		String str = "";
		int size = getAverageList().size();
		
		for(int i = 0; i < size; i++){
			str += getAverageList().get(i) + "\t";
		}
		return str;
	}
}