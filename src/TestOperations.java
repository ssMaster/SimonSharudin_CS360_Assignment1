import junit.framework.TestCase;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TestOperations extends TestCase{
	
	// checked
	private Patient makePatient(){
		Patient patient;
		
		String id = "1001A";
		char gender = 'm';
		int patientCholesterol = 150;
		int patientHDL = 60;
		int patientTriglycerides = 75;
		
		patient = new Patient(id, gender, patientCholesterol, patientHDL, patientTriglycerides);
		return patient;
	}
	
	// checked
	private String makeLine(){
		String line = "1001A m 150 60 75";
		return line;
	}
	
	// checked
	public void testCreatePatient(){
		Patient patient;
		Patient patient2 = makePatient();
		
		String id = "1001A";
		char gender = 'm';
		int patientCholesterol = 150;
		int patientHDL = 60;
		int patientTriglycerides = 75;
		
		patient = new Patient(id, gender, patientCholesterol, patientHDL, patientTriglycerides);
		
		assertEquals(patient, patient2);
	}
	
	// checked
	public void testReadFromStream() throws IOException{
		Reader in = new FileReader("inTest.txt");
		Operations operation = new Operations();
		operation.readFromStream(in);

		String str = operation.getLines().get(0);
		String line = makeLine();
		
		System.out.println(str);
		
		assertEquals(str, line);
	}
	
	/*
	// is this right?
	public void testWriteToStream() throws IOException{
		Operations operation1 = new Operations();
		
		// write a known line
		Patient p = makePatient();
		operation1.addToPatientList(p);
		Writer out = new FileWriter("test.txt");
		operation1.writeToStream(out);
		out.close();
		
		// read and compare
		Reader in = new FileReader("test.txt");
		Operations operation2 = new Operations();
		operation2.readFromStream(in);
		operation2.createPatient();	// create the patient from the information from the file
		
		assertEquals(operation1.getPatientList(), operation2.getPatientList());
	}
	*/
	
	/**
	 * Asserts that two objects are equal. If they are not
	 * an AssertionFailedError is thrown with the message "Not Equal"
	 */
	public boolean assertEquals(Patient expected, Patient actual) {
	    if (expected == null && actual == null) {
	        return true;
	    }
	    if (expected != null
	    		&& expected.getId().equals(actual.getId())
	    		&& expected.getGender() == actual.getGender()
	    		&& expected.getHDL() == actual.getHDL()
	    		&& expected.getTotalCholesterol() == actual.getTotalCholesterol()
	    		&& expected.getTriglycerides() == actual.getTriglycerides()) {
	        return true;
	    }
	    failNotEquals("Not Equal", expected, actual);
	    return false;
	}

}
