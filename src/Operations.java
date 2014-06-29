
/*  
 * Operations class holds the major operations of reading the patient info from a file, constructing lipid and profile reports
 * for the patients, and writing the lipid report to a file
 */ 

import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;

public class Operations {
	private ArrayList<Patient> patientList;	// To hold the list of patients
	private ArrayList<String> lines;		// To hold the lines of the file containing patient info
	
	/**
	 * Constructor: initialize ArrayLists
	 */
	public Operations(){
		patientList = new ArrayList<Patient>();
		lines = new ArrayList<String>();
	}
	
	// --------------------------------------------- getters
	public ArrayList<Patient> getPatientList(){
		return patientList;
	}
	
	public ArrayList<String> getLines(){
		return lines;
	}
	// ---------------------------------------------
	
	/**
	 * addToPatientList: add a patient to the list
	 * @param p
	 */
	public void addToPatientList(Patient p){
		patientList.add(p);
	}
	
	/**
	 * createPatient: tokenize the lines and create a new Patient instance
	 */
	public void createPatient(){
		Patient patient;	// To hold a patient
		StringTokenizer token;	// split the information for each Patient
		final int MAX_FIELDS = 5;	// To hold the maximum amount of fields per line per patient
		
		// for the patient
		String patientID;
		String gender;
		char patientGender;
		int patientCholesterol;
		int patientHDL;
		int patientTriglycerides;
		
		int lines = getLines().size();
		
		// get the patient information and add the new patient to the list
		for(int index = 0; index < lines; index++){
			token = new StringTokenizer(getLines().get(index));	
			
			if(MAX_FIELDS != token.countTokens()){
				System.out.println("ERROR! Field mismatch in patient data file...\nForce exit.");
				System.exit(0);
			}
			
			patientID = (String) token.nextElement();
			gender = (String) token.nextElement();
			patientCholesterol = Integer.parseInt((String) token.nextElement());
			patientHDL = Integer.parseInt((String) token.nextElement());
			patientTriglycerides = Integer.parseInt((String) token.nextElement());
			patientGender = gender.charAt(0);
			
			patient = new Patient(patientID, patientGender, patientCholesterol, patientHDL, patientTriglycerides);
			addToPatientList(patient);
		}
	}
	
	/**
	 * readFromStream: reads in lines and adds them to an ArrayList called 'lines'
	 * @param in - The stream to read
	 * @throws IOException
	 */
	@SuppressWarnings({ })
	public void readFromStream(Reader in) throws IOException{
		BufferedReader br = null;
		
		try{	// try to read the contents of the file
			br = new BufferedReader(in);
			String line;
			
			// add the line to a list
			while((line = br.readLine()) != null){
				lines.add(line);
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
		catch(IOException io){
			io.printStackTrace();
		}
		finally{	
			try{	// close the BufferedReader if it hasn't closed yet
				if(br != null){
					br.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * writeToStream: write the patient's report to the file
	 * @param w 
	 * @throws IOException
	 */
	public void writeToStream(Writer w) throws IOException {
		PrintWriter pw = new PrintWriter(w);
		
		pw.println("Id\tSex\tTot\t\tHDL\t\tLDL\t\tTRI\t\tRAT");
		pw.println("-------------------------------------------------------------------------------------------");
		for(int i = 0; i < getPatientList().size(); i++){
			//System.out.println((operations.getPatientList().get(i)).displayReport());
			pw.println((getPatientList().get(i)).displayReport());
		}
	}
	
	/**
	 * makeConsoleReport: make the second report that is displayed on the console
	 * note - This report contains the averages and the differences of the averages of the two groups: Control and Treatment
	 */
	public void makeConsoleReport(){
		ArrayList averageList = new ArrayList();
		PatientGroup control;
		PatientGroup treatment;
		
		control = new PatientGroup();
		treatment = new PatientGroup();
		
		int lines = getLines().size();
		
		// separate the patients into either a Control Group or a Treatment Group
		for(int index = 0; index < lines; index++){		
			Patient currentPatient = getPatientList().get(index);
			
			if(currentPatient.getGroup() == 'A'){	// control group
				control.addToGroup(currentPatient);
			}
			else if(currentPatient.getGroup() == 'B'){	// treatment group
				treatment.addToGroup(currentPatient);
			}
			else{	// neither group
				throw new IllegalArgumentException("Error! No group!");
			}
		}
		
		// ---------------------------------------------------------
		// make and display the second report
		PatientGroup diffGroup = new PatientGroup();
		control.makeGroupSums();
		treatment.makeGroupSums();
		
		System.out.println("Control Group \t\t" + control.displayAverageReport());
		System.out.println("Treatment Group \t" + treatment.displayAverageReport());
		
		int controlSize = control.getAverageList().size();
		int treatmentSize = treatment.getAverageList().size();
	
		// add the differences of the averages of the two groups and add to an ArrayList
		for(int index = 0; index < controlSize - 1; index++){
			averageList.add(diffGroup.getDifferences((int) control.getAverageList().get(index), (int) treatment.getAverageList().get(index)));
		}
		averageList.add(diffGroup.getDifferences((double) control.getAverageList().get(controlSize - 1), (double) treatment.getAverageList().get(treatmentSize - 1)));

		// print the differences of the averages of the two groups
		System.out.print("Difference \t\t");
		for(int index = 0; index < averageList.size(); index++){
			System.out.print(averageList.get(index) + "\t");
		}
	}
	
	/**
	 * createProfiles: perform the necessary operations to compose and display the two reports
	 * @param inputFilename
	 * @param outputFilename
	 * @throws IOException
	 */
	public void createProfiles(String inputFilename, String outputFilename) throws IOException{
		Reader in = new FileReader((inputFilename));
		Writer out = new FileWriter(outputFilename);
		Operations op = new Operations();
		op.readFromStream(in);
		op.createPatient();
		op.makeConsoleReport();
		
		op.writeToStream(out);
		in.close();
		out.close();
	}
}
