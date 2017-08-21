import java.io.* ;
import java.util.* ;

class A implements Serializable {
	public int i = 5 ;
	public String str = "Hi" ;
	public List<String> l = new ArrayList<String>() ;
}
public class ObjSerTest {
	public static void main(String[]args) {
		A a = new A() ;
		a.i = 10;
		a.str = "Hello";
		a.l.add("One");
		a.l.add("Two") ;
		serialize(a) ;
	}
	private static void serialize(A a) {
		System.out.println("Serializing...");
		try {
			FileOutputStream fos = new FileOutputStream("test.out") ;
			ObjectOutputStream oos = new ObjectOutputStream(fos) ;
			oos.writeObject(a) ;
		} catch (Exception e) {
			System.err.println("Problem: "+e) ;
		}
	}
}