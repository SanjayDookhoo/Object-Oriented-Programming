public class Inheritance {
	public static void main (string []args) {

	}
}

class Person {
	private String name,address,eMail;
	private int phoneNum;

}

class Student extends Person {
	private final status;

	public Person (String status){
		if(status=="freshman" || status=="sophomore" status=="junior" status=="senior"){
			this.status=status;
		}
	}

	public getRoom(String rm) {
		if(rm=="COMP2500") return
	}
}

class Lecturer extends Person {
	private String office;
	private double salary;
	private String dateHired;

	public Lecturer (String office, double salary, String dateHired){
		this.office=office;
		this.salary=salary;
		this.dateHired=dateHired;
	}
}