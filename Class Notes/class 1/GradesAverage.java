/**
 * @(#)GradesAverage.java
 *
 *
 * @author
 * @version 1.00 2016/1/18
 */


public class GradesAverage {

    public static void main(String[] args) {

		int numStudents;
		int grade;
		boolean repeat=true;

		System.out.println("Enter the number of students");
		System.in.readln(numStudents);

		int[] arr=new int[numStudents];

		for(int e1 :arr){
			repeat=true;
			while(repeat==true){

				System.out.printtln("Enter the grade of student: "+e1);
				System.in.readln(grade);

				if(grade>=1 && grade<=100){
					arr[e1]=grade;
					repeat=false;
				}
			}
		}


    }


}