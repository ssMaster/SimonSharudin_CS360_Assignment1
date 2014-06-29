/*
 * Simon Sharudin
 * CS360 Software Engineering - Assignment1
 * January 28, 2014
 */

import java.util.Scanner;
import java.io.*;

public class PatientApplication {
	public static void main(String[] args) throws IOException{
		// local variables
		String inputFilename;
		String outputFilename = "report.out";
		
		// Operations object instance
		Operations operations = new Operations();
		
		// Scanner object
		Scanner keyboard = new Scanner(System.in);
		
		// Greeting message
		System.out.println("Welcome to the Longview Lipid Profile Study");
		System.out.println("Please enter the file name containing study data:");
		
		inputFilename = keyboard.next();
		
		// create the profiles
		operations.createProfiles(inputFilename, outputFilename);
	}
}
