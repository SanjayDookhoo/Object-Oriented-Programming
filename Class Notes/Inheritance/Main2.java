import java.lang.Exception;


interface Comparables{
	public boolean lessThan(Object x) throws IncompatibleTypeException;
	public boolean greaterThan(Object x) throws IncompatibleTypeException;
}

public class Main2{
	public static void main(String []args){
		Rectangle r1 = new Rectangle("r1", 4,10);
		Rectangle r2 = new Rectangle("r2", 40,10);
		Circle c1 = new Circle("c1", 3);
		try {
				if (r1.lessThan(r2)) {
					System.out.println("Nice");
				} else {
					System.out.println("Not Nice");		
				}
				
				if (r1.lessThan(c1)) {
					System.out.println("Nice");
				} else {
					System.out.println("Not Nice");		
				}
				
				
		} catch (IncompatibleTypeException e)   {
				System.out.println(e);
		}	
		Shape s=r1;
		System.out.printf(s.toString());
		
	}
}

class IncompatibleTypeException extends Exception {

        public IncompatibleTypeException (String msg)
        {
            super (msg);
        }
		
		public IncompatibleTypeException (Throwable thr)
        {
            super (thr);
        }
		
        public IncompatibleTypeException (String msg, Throwable thr)
        {
            super (msg, thr);
        }
}

abstract class Shape{
	protected String shapeName; //denoted by #
	Shape(String name){ //constructor
		this.setName(name);
	}
	public void setName(String name){
		this.shapeName = name;
	}
	public String getName(){
		return this.shapeName;
	}
	
	public abstract double area();
	
	public String toString(){
		String desc="shape";
		return desc;
	}
}


class Circle extends Shape{
	private double radius;
	Circle(String name){
		super(name); //sets name from function in parent class. MUST be the first thing done in the function
		this.radius = 0;
	}
	Circle(String name, double radius){
		super(name);
		this.radius = radius;
	}
	public double area(){
		return this.radius * this.radius * 3.1415;
	}
}

class Rectangle extends Shape implements Comparables{
	private double height;
	private double width;
	Rectangle(String name){
		super(name);
		this.height = this.width = 0;
	}
	Rectangle(String name, double height, double width){
		super(name);
		this.height = height;
		this.width = width;
	}
	public double area(){
		return this.height * this.width;
	}
	public boolean lessThan(Object x) throws IncompatibleTypeException{
		if(x instanceof Rectangle) {
			Rectangle rect = (Rectangle) x;
			return area() < rect.area();			
		}	
		else throw new IncompatibleTypeException("Object is not a Rectangle");
	}
	public boolean greaterThan(Object x) throws IncompatibleTypeException{
		if(x instanceof Rectangle) {
			Rectangle rect = (Rectangle) x;
			return area() > rect.area();
		}
		else throw new IncompatibleTypeException("Object is not a Rectangle");
	}
	public String toString(){
		String desc="rect";
		return desc;
	}
}