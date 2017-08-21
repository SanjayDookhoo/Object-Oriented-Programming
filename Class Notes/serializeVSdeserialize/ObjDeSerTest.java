import java.io.* ;
import java.util.* ;
class A implements Serializable {
	public int i = 5 ;
	public String str = "Hi" ;
	public List<String> l = new ArrayList<String>() ;
}
public class ObjDeSerTest {
	public static void main(String[]args) {
		A a = deserialize() ;
		System.out.println(a.i) ;
		System.out.println(a.str) ;
		System.out.println(a.l) ;
	}
	private static A deserialize() {
		System.out.println("DeSerializing...");
		try {
			FileInputStream fis = new FileInputStream("test.out") ;
			ObjectInputStream iis = new ObjectInputStream(fis) ;
			return (A) iis.readObject() ;
		} catch (Exception e) {
			System.err.println("Problem: "+e) ;
		}
		return null ;
	}
}
