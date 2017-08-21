 public class referencingVSinitialization {
 	public static void main (String []args) {
		Shape shape1= new Shape(10);
		Shape shape2=shape1;
		
		//change data in shape1
		//shape1.setNumSides(15);
		
		//System.out.println(shape1.getNumSides());
		//System.out.println(shape2.getNumSides());
		
		//NOTE THE OUTPUT OF BOTH CHANGES BECAUSE SHAPE2 REFERENCES WHAT SHAPE1 REFERENCES
		
		//CLONING
		try{
			shape2=shape1.clone();
		}catch(CloneNotSupportedException e){
			System.out.println(e);
		}
		shape1.setNumSides(15);
		System.out.println(shape1.getNumSides());
		System.out.println(shape2.getNumSides());
 	}
 }

 class Shape implements Cloneable {
 	private int numSides;

 	Shape(int numSides) {
 		this.numSides = numSides;
 	}

 	public int getNumSides(){
 		return this.numSides;
 	}
	
	public void setNumSides(int numSides){
 		this.numSides=numSides;
 	}
 	
    public Shape clone() throws CloneNotSupportedException {
    	return (Shape) super.clone();
    }

 }