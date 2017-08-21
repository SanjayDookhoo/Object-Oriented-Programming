public class Sample{
	public static void main(String[] args){
		Account object1 = new Account();
		object1.setAccountName("Wage");
		object1.setAccountBalance(200.00);
		System.out.println(object1.getAccountName());

		Account object2 = new Account("David",300.00);
		System.out.println(object2.getAccountName());

		Account object3 = new Account("David");
		System.out.println(object3.getAccountName());

		Account object4 = new Account(300.00);
		System.out.println(object4.getAccountBalance());
	}
}

class Account{
	private String accountName;
	private double accountBalance;
	public void setAccountName(String name){
		this.accountName = name;
	}
	public void setAccountBalance(double bal){
		this.accountBalance = bal;
	}
	public String getAccountName(){
		return this.accountName;
	}
	public double getAccountBalance(){
		return this.accountBalance;
	}

	//CONSTRUCTORS
	Account(String name, double bal){ //public is not necessary because it is assumed by default
		this.accountName=name;
		this.accountBalance=bal;
	}
	public Account(){//An empty constructor must be added when a first constructor is added

	}
	Account (String name){
		this.accountName=name;
	}
	Account(double bal){
		this.accountBalance=bal;
	}
}

/*
 *A constructor is a special method which is executed (invoked) at the point of creation of an object. Typically used to initialize variables of the class.
 *In Java a constructor has the same name as its class
 *In Java a class can have(not necessary) multiple constructios each supporting different ways to create an instance of the class
 *multple constructors uses polymorphism, because the constructors are recognized by different parameters
 **/

 /*INHERITANCE
  *New data types(classer) can be defined as extensions of previouslt defined types.
  *Sub class inherits properties/methods from parent class