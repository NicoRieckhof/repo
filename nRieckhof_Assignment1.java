/*
   Class: CMSC203 CRN23011
   Program: Assignment 1
   Instructor: Prof. Huseyin Aygun
   Description: This program is a grade calculator that uses the grading configuration set
   				from a file to provide a student with a grade based on their performance.
   Due: 09/14/2026
   Integrity Pledge: I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   Student Name: Nicolas Rieckhof
 */

import java.util.Scanner;
import java.io.*;

public class GradeCalculator {
	public static void main(String[] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		
		String className;
		int numberOfCats;
		String cat1, cat2, cat3;
		double cat1Weight, cat2Weight, cat3Weight;
		
		String firstName, lastName;
		String inputCat1, inputCat2, inputCat3;
		int numOfScoresCat1, numOfScoresCat2, numOfScoresCat3;
		
		boolean defaultConfiguration = false;
		double totalWeight;
		double cat1Total = 0, cat2Total = 0, cat3Total = 0;
		double cat1Average = 0, cat2Average = 0, cat3Average = 0;
		double overallAverage;
		String letterGrade, userAnswer;
		double remainder;
		
		// Configuration File
		try (Scanner inputFile = new Scanner(new File("gradeconfig.txt"))) {
			className = inputFile.nextLine();
			numberOfCats = inputFile.nextInt();
			
			cat1 = inputFile.next();
			cat1Weight = inputFile.nextDouble();
			
			cat2 = inputFile.next();
			cat2Weight = inputFile.nextDouble();
			
			cat3 = inputFile.next();
			cat3Weight = inputFile.nextDouble();
			
		}
		
		// Calculates total weight of the three categories
		totalWeight = cat1Weight + cat2Weight + cat3Weight;
		if (totalWeight != 100) {
			defaultConfiguration = true;
			cat1Weight = 40;
			cat2Weight = 30;
			cat3Weight = 30;
			System.out.print("The total weight of the categories does "
							  + "not match up\nto 100, so the default "
							  + "configuration will be used.\n");
		}
		
		// Student Scores
		try (Scanner inputFile = new Scanner(new File("grades_input.txt"))) {
			firstName = inputFile.nextLine();
			lastName = inputFile.nextLine();
			
			// Checks category 1 and stores data if valid
			inputCat1 = inputFile.next();
			if (!inputCat1.equals(cat1)) {
				System.out.println("Error for \"" + cat1 + "\" so it will be skipped.");
				for (int line = 1; line <= 3; line++) {
					inputFile.nextLine();
				}
			}
			else {
				numOfScoresCat1 = inputFile.nextInt();
				for (int score = 0; score < numOfScoresCat1; score++) {
					cat1Total += inputFile.nextDouble();
				}
				cat1Average = cat1Total / numOfScoresCat1;
			}
			
			// Checks category 2 and stores data if valid
			inputCat2 = inputFile.next();
			if (!inputCat2.equals(cat2)) {
				System.out.println("Error for \"" + cat2 + "\" so it will be skipped.");
				for (int line = 1; line <= 3; line++) {
					inputFile.nextLine();
				}
			}
			else {
				numOfScoresCat2 = inputFile.nextInt();
				for (int score = 0; score < numOfScoresCat2; score++) {
					cat2Total += inputFile.nextDouble();
				}
				cat2Average = cat2Total / numOfScoresCat2;
			}
			
			// Checks category 3 and stores data if valid
			inputCat3 = inputFile.next();
			if (!inputCat3.equals(cat3)) {
				System.out.println("Error for \"" + cat3 + "\" so it will be skipped.");
			}
			else {
				numOfScoresCat3 = inputFile.nextInt();
				for (int score = 0; score < numOfScoresCat3; score++) {
					cat3Total += inputFile.nextDouble();
				}
				cat3Average = cat3Total / numOfScoresCat3;
			}
		}
		
		// Calculates the student's overall grade
		overallAverage = (cat1Average * cat1Weight / 100) + (cat2Average * cat2Weight / 100) + (cat3Average * cat3Weight / 100);
		
		// Assign's the student's letter grade
		if (overallAverage >= 90) {
			letterGrade = "A";
		}
		else if (overallAverage >= 80) {
			letterGrade = "B";
		}
		else if (overallAverage >= 70) {
			letterGrade = "C";
		}
		else if (overallAverage >= 60) {
			letterGrade = "D";
		}
		else {
			letterGrade = "F";
		}
		
		// Applies +/- letter grading if the user wishes
		do {
			System.out.print("Apply +/- grading? (Y/N): ");
			userAnswer = keyboard.next();
		} while (!userAnswer.equalsIgnoreCase("Y") && !userAnswer.equalsIgnoreCase("N"));
		if (userAnswer.equalsIgnoreCase("Y")) {
			remainder = overallAverage % 10;
			if (remainder >= 8) {
				letterGrade += "+";
			}
			else if (remainder <= 2) {
				letterGrade += "-";
			}
		}
		
		// Summary of the student and their grade
		System.out.println("\n\nCMSC203 Project 1");
		System.out.println("------------------");
		System.out.println("Course: " + className);
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Grade Results:");
		System.out.printf("%s (%.0f%%): Average = %.2f\n", cat1, cat1Weight, cat1Average);
		System.out.printf("%s (%.0f%%): Average = %.2f\n", cat2, cat2Weight, cat2Average);
		System.out.printf("%s (%.0f%%): Average = %.2f\n", cat3, cat3Weight, cat3Average);
		System.out.printf("Overall Average: %.2f\n", overallAverage);
		System.out.println("Final letter grade: " + letterGrade + '\n');
		
		if (defaultConfiguration) {
			System.out.println("The default configurations were used.");
		}
		System.out.println("\nProgrammer: Nicolas Rieckhof");
		
		// Stores the exact summary in grades_report.txt
		try (PrintWriter outputFile = new PrintWriter(new File("grades_report.txt"))) {
			outputFile.println("CMSC203 Project 1");
			outputFile.println("------------------");
			outputFile.println("Course: " + className);
			outputFile.println("Name: " + firstName + " " + lastName);
			outputFile.println("Grade Results:");
			outputFile.printf("%s (%.0f%%): Average = %.2f\n", cat1, cat1Weight, cat1Average);
			outputFile.printf("%s (%.0f%%): Average = %.2f\n", cat2, cat2Weight, cat2Average);
			outputFile.printf("%s (%.0f%%): Average = %.2f\n", cat3, cat3Weight, cat3Average);
			outputFile.printf("Overall Average: %.2f\n", overallAverage);
			outputFile.println("Final letter grade: " + letterGrade + '\n');
			
			if (defaultConfiguration) {
				outputFile.println("The default configurations were used.");
			}
			outputFile.println("\nProgrammer: Nicolas Rieckhof");
		}
		
		
	}
}
