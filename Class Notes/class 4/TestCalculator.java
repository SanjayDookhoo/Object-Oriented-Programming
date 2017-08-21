public class TestCalculator {
	public static void main (String []args) {
		//Original Calculator
		/*Calculator myCalc= new Calculator();

		myCalc.subtraction(10,11);
		System.out.println("subtraction: "+myCalc.getOutput());
		myCalc.addition(10,11);
		System.out.println("addition: "+myCalc.getOutput());*/

		//CalculatorModelTwo
		/*CalculatorModelTwo myCalc= new CalculatorModelTwo();
		myCalc.addition(10,11);
		System.out.println("addition: "+myCalc.getOutput());

		myCalc.subtraction(10,11);
		System.out.println("subtraction: "+myCalc.getOutput());

		myCalc.multiply(10,11);
		System.out.println("multiply: "+myCalc.getOutput());

		myCalc.divide(10,11);
		System.out.println("divide: "+myCalc.getOutput());*/

		//CalculatorModelMars
		CalculatorModelMars myCalc = new CalculatorModelMars();
		myCalc.addition(2,3);
		System.out.println("addition: "+myCalc.getOutput());

		myCalc.subtraction(10,11);
		System.out.println("Subtraction: "+myCalc.getOutput());
	}
}

class Calculator {
	private double output;

	public void addition(double x, double y) {
		this.output=x+y;
	}

	public void subtraction(double x, double y) {
		this.output=x-y;
	}

	public double getOutput() {
		return this.output;
	}
	public void setOutput(double O){
		this.output=O;
	}

}

class CalculatorModelTwo extends Calculator {//inheritance for extension(extension with multiply and divide)
	public void multiply(double x, double y) {
		setOutput(x*y);
	}
	public void divide(double x, double y) {
		setOutput(x/y);
	}
}

/*People on mars only do addition using digits 0 through 6.
 *Let x and y be an element of {0,1,2,3,4,5,6}
 *x+y=(x mod y) mod 6
 **/

class CalculatorModelMars extends Calculator {//Inheritance for specialization using overrides(simply the redefining of the function)
	public void addition(double x, double y) {
		setOutput((x + y) % 6);
	}
	public void subtraction(double x, double y) {//the function cannot be removed it must be inherited
		System.out.println("Cannot be performed on this model calculator");
		setOutput(0);
	}
}


/*By default for all variables, a setter and getter should be made for inheritance, to allow modifying of the variables
 *Only the functions are inherited, the variables are inherited if it is preotected, but that is usually not the case
 **/