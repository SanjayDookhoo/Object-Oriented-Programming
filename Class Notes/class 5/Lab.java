/*
 *An abstract class means an object cannot be created of that class. It is meerely used as a template.
 *An abstract method forces the children of that class to implement their own version of that method
 **/

 public class Lab {
 	public static void main (String []args) {


 	}
 }

 abstract class Shape {
 	private final int numSides;

 	Shape(int numSides) {
 		this.numSides = numSides;
 	}

 	public int getNumSides(){
 		return this.numSides;
 	}

 	public abstract double getArea();
 	public abstract double getPerimeter();

 }

 class Rectangle extends Shape{
 	private final double width,height;

	public Rectangle(double width, double height){
		this.width=width;
		this.height=height;
	}

	public Rectangle(){
		this(1,1);//initializing a rectangle of height and width 1
	}

 	public double getArea(){
 		return this.width*height;
 	}

 	public double getPerimeter(){
 		return width*2+height*2;
 	}
 }

 class Triangle extends Shape{
 	private final double width,height;

 	public double getArea(){
 		return this.width*height/2;
 	}

 	public double getPerimeter(){
 		return width+height+ Math.sqrt(width*width +height*height);
 	}
 }

 class Circle extends Shape{
 	private double radius;

 	public double getArea(){
 		return 3.14*radius*2;
 	}

 	public double getPerimeter(){
 		return this.radius*2*3.14;
 	}
 }