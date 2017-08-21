import java.util.*;
import java.io.*;
import java.util.Formatter.*;
class Person{
	private String occupation;
	private String name;
	private double age;
	private double weight;
	
	public void setOccupation(String o){
		this.occupation=o;
	}	
	public String getOccupation(){
		//System.out.print(occupation);
		return this.occupation;
	}
	
	public void setName(String n){
		this.name=n;
	}
	public String getName(){
		//System.out.print(name);
		return this.name;
	}
	
	public void setAge(double a){
		this.age=a;
	}
	public double getAge(){
		//System.out.print( age);
		return this.age;
	}
	
	public void setWeight(double w){
		this.weight=w;
	}
	
	public double getWeight(){
		//System.out.print(weight);
		return this.weight;
	}
	
	
	public Person(){
	//	this.name = name;
//		this.age=age;
	//	this.weight=weight;
	}
	
}

class Doctor extends Person{
	private double licenseNum;
	private String school;

	public Doctor(){
		
	}
	/*public Doctor(String occupation, String name, double age, double weight){
		
		super(String occupation, String name, double age, double weight);
		
	} */
	
	
	public void setLicenseNum(double num){
		this.licenseNum=num;
	}
	
	public double getLicenseNum(){
		System.out.print(licenseNum);
		return this.licenseNum;
	}
	
	public void setSchool(String s){
		this.school=s;
	}
	public String getSchool(){
		System.out.print(school);
		return this.school;
	}
}

class TennisPlayer extends Person{
	private double ranking;
	private double wins;
	public TennisPlayer(){
		
	}
	
	/*public TennisPlayer(String Name){
		
		super(Name);
		
	}*/
	
	public void setRanking(double r){
		this.ranking=r;
	}
	public double getRanking(){
		return this.ranking;
	}
	
	public void setWins(double win){
		this.wins=win;
	}
	public double getWins(){
		return this.wins;
	}
}

public class Assignment1{
	public static void main(String args[]){
		System.out.println("Doctors");
		System.out.println("__________________________________________________________________");
		System.out.format("%-10s%-10s%-10s%-20s%-15s","Name","Age","Weight","LicenseNo","Medical School");
		System.out.println();
		Doctor doctor1 = new Doctor();
		doctor1.setName("Betty Best");
		doctor1.setAge(40);
		doctor1.setOccupation("Doctor");
		doctor1.setWeight(160);
		doctor1.setLicenseNum(30034);
		doctor1.setSchool("UWIMONA");
		
		
		System.out.println(/*"%-10s%-10f%-10f%-20f%-15s",*/doctor1.getName()+doctor1.getAge()+doctor1.getWeight()+doctor1.getLicenseNum()+doctor1.getSchool());
		
	
		
		
		
	} 
}