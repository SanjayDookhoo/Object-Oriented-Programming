/*
 *Write a program called grades average which prompts the user for the number of students,
 *reads it from the keyboard, and saves it in an int variable called numStudents. It then
 *prompts the user for the grades of each student and saves them in an int array called grades.
 *Your program should check that the grade is between 1 and 100. A sample session is as follows:
 *
 *Enter the number of students: 3
 *Enter the grade for student 1: 55
 *Enter the grade for student 2: 108
 *Invalid grade, try again...
 *Enter the grade for student 2: 56
 *Enter the grade for student 3: 57
 *The average is 56.0
 *
 *
 **/

 import java.util.Scanner;

 public class GradesAverage{
    public static void main(String[] args){


        System.out.print("Enter the number of students");

	    Scanner keyboard = new Scanner(System.in);
        int sum=0, temp, avg;
        int numStudents = keyboard.nextInt();
        int[] array = new int[numStudents];



        for (int i=0; i<numStudents; i++){
        	System.out.println("Enter the grades of student "+i);
        	temp= keyboard.nextInt();
        	while (temp>100 || temp <0){
        		System.out.println("Invalid grade, try again...");
        		temp = keyboard.nextInt();
        	}
        	array[i] = temp;
        	sum +=temp;
        }
        avg = sum/numStudents;
        System.out.println("The average is " +avg);
    }
 }