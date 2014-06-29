/*
 * Patient holds all personal information about each individual patient
 */

public class Patient {
	// fields
	private String id;
	private char gender;
	private int totalCholesterol;
	private int hdl;
	private int triglycerides;
	
	/**
	 * Constructor
	 * @param id - The id of a patient
	 * @param gender - The gender of the patient
	 * @param totalCholesterol - The patient's total cholesterol level
	 * @param hdl - The hdl value of a patient
	 * @param triglycerides - The triglyceride value of a patient
	 */
	public Patient(String id, char gender, int totalCholesterol, int hdl, int triglycerides){
		// ensure that the gender is valid
		if(gender != 'm' && gender != 'M' && gender != 'f'&& gender != 'F'){
			System.out.println("ERROR: The gender of a patient must be m (male) or f (female).\nExit and try again.");
			System.exit(0);
		}
		
		// ensure cholesterol level is positive
		if(totalCholesterol < 0){
			System.out.println("ERROR: Total cholesterol must be positive.");
			System.exit(0);
		}
		
		// ensure hdl level is positive
		if(hdl < 0){
			System.out.println("ERROR: Total cholesterol must be positive.");
			System.exit(0);
		}
		
		// ensure triglycerides level is positive
		if(triglycerides < 0){
			System.out.println("ERROR: Total cholesterol must be positive.");
			System.exit(0);
		}
		
		this.id = id;
		this.gender = gender;
		this.totalCholesterol = totalCholesterol;
		this.hdl = hdl;
		this.triglycerides = triglycerides;
	}
	
	// -------------------------- getters
	public String getId(){
		return id;
	}
	
	public char getGender(){
		return gender;
	}
	
	public int getTotalCholesterol(){
		return totalCholesterol;
	}
	
	public int getHDL(){
		return hdl;
	}
	
	public int getTriglycerides(){
		return triglycerides;
	}
	
	// ------------------------------------------------------ lipid classifications
	public String getTotalCholesterolClassification(){
		
		if(totalCholesterol < 200){
			return "DSRBL";
		}
		else if(totalCholesterol >= 200 && totalCholesterol <= 239){
			return "BHIGH";
		}
		else{
			return "HIGH";
		}
	}
	
	public String getHDLClassification(){
		if(gender == 'm' || gender == 'M'){
			if(hdl < 40){	// for men
				return "LOW";
			}
			else if(hdl >= 40 && hdl <= 59){
				return "AVG";
			}
			else{
				return "HIGH";
			}
		}
		else{	// for women
			if(hdl < 50){
				return "LOW";
			}
			else if(hdl >= 50 && hdl <= 59){
				return "AVG";
			}
			else{
				return "HIGH";
			}
		}
	}
	
	public String getLDLClassification(){
		double ldl = getLDL();
		if(ldl < 100){
			return "OPT";
		}
		else if(ldl >= 100 && ldl <= 129){
			return "NOPT";
		}
		else if(ldl >= 130 && ldl <= 159){
			return "BHIGH";
		}
		else{
			return "HIGH";
		}
	}
	
	public String getTriglyceridesClassification(){
		if(triglycerides < 150){
			return "DSRBL";
		}
		else if(triglycerides >= 150 && triglycerides <= 199){
			return "BHIGH";
		}
		else{
			return "HIGH";
		}
	}
	
	public String getRatioClassification(){
		double ratio = getCholesterolRatio();
		if(ratio < 3.3){
			return "DEC";
		}
		else if(ratio >= 3.3 && ratio <= 5.0){
			return "AVG";
		}
		else{
			return "INC";
		}
	}
	// --------------------------------------------------------------
	
	/**
	 * getLDL: get the ldl value for a patient based on their current cholesterol, hdl, and triglyceride levels
	 * @return - The ldl value for the patient
	 */
	public int getLDL(){
		return (totalCholesterol - hdl - triglycerides / 5);
	}
	
	/**
	 * getCholesterolRatio: get the patient's ratio level based on their current total cholesterol and hdl levels
	 * @return - The cholesterol ratio level for a patient
	 */
	public double getCholesterolRatio(){
		return Math.round(((double) totalCholesterol / hdl) * 10.0) / 10.0;
	}
	
	/**
	 * getGroup: get the group that the patient belongs to
	 * @return - The patient's group 
	 */
	public char getGroup(){
		return (id.charAt(id.length() - 1));
	}
	
	/**
	 * displayReport: compose a report for the patient
	 * @return - A string compiled of the report for the patient
	 */
	public String displayReport(){
		String str = "";
		str = id + "\t" + gender + "\t" + totalCholesterol + " (" + getTotalCholesterolClassification() + ")\t" + hdl + " (" + getHDLClassification() + ")\t" + getLDL() + " (" + getLDLClassification() + ")\t" + triglycerides + " (" + getTriglyceridesClassification() + ")\t" + getCholesterolRatio() + " (" + getRatioClassification() + ")";
		return str;
	}
}
