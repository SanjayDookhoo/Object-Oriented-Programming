/*
 *ID:814000842
 *Name: Sanjay Dookhoo
 *Course: 2500
 **/
public class Assignment {
	public static void main (String []args){
		
		String doctorFormatHead="%-16s%-8s%-8s%-12s%-12s%s";
		String doctorFormatData="%-16s%-8d%-8d%-12d%-12s%s";
		Doctor doctor1=new Doctor("Better Best",40,160,30034,"UWI_MONA");
		Doctor doctor2=new Doctor("Anthony Smith",36,190,10892,"UWI_SA");
	
		System.out.printf("Doctors\n");
		for(int i=0;i<60;i++)System.out.printf("-");
		System.out.printf("\n");
		System.out.printf(doctorFormatHead,"Name","Age","Weight","LicenceNo","MedicalSchool","\n");
		for(int i=0;i<60;i++)System.out.printf("=");
		System.out.printf("\n");
		System.out.printf(doctorFormatData,doctor1.getName(),doctor1.getAge(),doctor1.getWeight(),doctor1.getLicenceNo(),doctor1.getMedicalSchool(),"\n");
		System.out.printf(doctorFormatData,doctor2.getName(),doctor2.getAge(),doctor2.getWeight(),doctor2.getLicenceNo(),doctor2.getMedicalSchool(),"\n");
		
		String tennisPlayerFormatHead="%-16s%-8s%-8s%-8s%-16s%s";
		String tennisPlayerFormatData="%-16s%-8d%-8d%-8d%-16d%s";
		TennisPlayer tennisPlayer=new TennisPlayer("Andrew Davis",23,170,41,13);
		
		System.out.printf("\nTennis Players\n");
		for(int i=0;i<60;i++)System.out.printf("-");
		System.out.printf("\n");
		System.out.printf(tennisPlayerFormatHead,"Name","Age","Weight","Ranking","Tournaments Won","\n");
		for(int i=0;i<60;i++)System.out.printf("=");
		System.out.printf("\n");
		System.out.printf(tennisPlayerFormatData,tennisPlayer.getName(),tennisPlayer.getAge(),tennisPlayer.getWeight(),tennisPlayer.getRanking(),tennisPlayer.getWon(),"\n");
		

		/*
		String prefix1 = "short text:";
		String prefix2 = "looooooooooooooong text:";
		String msg = "indented";
		
		 //The second string begins after 40 characters. The dash means that the
		 //first string is left-justified.
		 
		String format = "%40s%s%n";
		System.out.printf(format, prefix1, msg);
		System.out.printf(format, prefix2, msg);
		*/
	}
}

class Person {
	private String name;
	private int age,weight;
	
	Person(String name, int age, int weight){
		this.name=name;
		this.age=age;
		this.weight=weight;
	}
	
	//getters 
	public String getName(){
		return this.name;
	}
	public int getAge(){
		return this.age;
	}
	public int getWeight(){
		return this.weight;
	}
}

class Doctor extends Person {
	private int licenceNo;
	private String medicalSchool;
	
	//Constructor
	Doctor(String name, int age, int weight, int licenceNo, String medicalSchool){
		super(name,age,weight);
		
		this.licenceNo=licenceNo;
		this.medicalSchool=medicalSchool;
	}
	
	//getters
	/*public String getNameP(){
		return getName();
	}
	public int getAgeP(){
		return this.age;
	}
	public int getWeightP(){
		return this.weight;
	}*/
	
	public int getLicenceNo(){
		return this.licenceNo;
	}
	public String getMedicalSchool(){
		return this.medicalSchool;
	}
}

class TennisPlayer extends Person {
	private int ranking,won;
	
	//Constructor
	TennisPlayer(String name, int age, int weight, int ranking,int won){
		super(name,age,weight);
		
		this.ranking=ranking;
		this.won=won;
	}
	
	//getters
	/*public String getName(){
		return this.name;
	}
	public int getAge(){
		return this.age;
	}
	public int getWeight(){
		return this.weight;
	}*/
	
	public int getRanking(){
		return this.ranking;
	}
	public int getWon(){
		return this.won;
	}
}