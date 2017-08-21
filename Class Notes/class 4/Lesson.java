public class Lesson {
	public static void main (String []args) {
		Circle myCircle = new Circle("myCircle",5);
		Rectangle myRect = new Rectangle("myRect",5,3);

		System.out.println(myCircle.Area());
		System.out.println(myRect.Area());
	}
}

class Shape {
	protected String shapeName;

	public void setName(String name){
		this.shapeName=name;
	}
	public String getName(){
		return this.shapeName;
	}

	//CONSTRUCTOR
	public Shape(String name) {
		//this.shapeName=name;
		this.setName(name);//this can be done to avoid repeating code when the setters have background tasks that are done (eg checking if the user has permission to set that particular attribute)
	}
}

class Circle extends Shape {
	private double radius;

	/*public void setRadius(double r) {
		this.radius=r;
	}
	public double getRadius(){
		return this.radius;
	}*/

	public Circle(String name){
		super(name);//this is done because name is in the parent class. The parameters are the parameters defined by the different constructor classes, in This case the constructor used was Shape(String name). Therefore there can be more than one parameter but super can only be included 0once and it MUST be at the beginning of the function. Only included once because java only supports single
		//inheritance, this is not the case for c++.
		this.radius=0;
	}
	public Circle(String name,double radius){
		super(name);
		this.radius=radius;//radius apparently can be the same name for the class and the parameter?
	}

	public double Area() {
		return 3.14 * radius * radius;
	}
}

class Rectangle extends Shape {
	private double height, width;

	public Rectangle(String name, double height,double width){
		super(name);
		this.height=height;
		this.width=width;
	}

	public double Area() {
		return height*width;
	}
}