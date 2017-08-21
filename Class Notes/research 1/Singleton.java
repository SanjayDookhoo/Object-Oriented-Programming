// Generates random numbers.
class RandomGenerator {
	private static RandomGenerator gen = null;
	
	private RandomGenerator(){
	}
	
	public static synchronized RandomGenerator getInstance() {
		if (gen == null)
			gen = new RandomGenerator();
		return gen;
	}
	public double nextNumber() {
		return Math.random();
	}
}

public class Singleton {
   public static void main(String[] args) {
      RandomGenerator tmp1 = RandomGenerator.getInstance( );
      RandomGenerator tmp2 = RandomGenerator.getInstance( );
      System.out.print(tmp1.nextNumber( ));
      //if(tmp1==tmp2) System.out.print("one instance created");
   	  //else System.out.print("two different instances created");
   }
}