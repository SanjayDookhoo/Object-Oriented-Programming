

// File Name: Singleton.java
class Singleton {

   private static Singleton singleton = new Singleton( );
   
   /* A private Constructor prevents any other 
    * class from instantiating.
    */
   private Singleton(){ }
   
   /* Static 'instance' method */
   public static Singleton getInstance( ) {
      return singleton;
   }
   /* Other methods protected by singleton-ness */
   protected static void demoMethod( ) {
      System.out.println("demoMethod for singleton"); 
   }
}

// File Name: SingletonDemo.java
public class SingletonDemo {
   public static void main(String[] args) {
      Singleton tmp1 = Singleton.getInstance( );
      Singleton tmp2 = Singleton.getInstance( );
      //tmp.demoMethod( );
      if(tmp1==tmp2) System.out.print("one instance created");
   		else System.out.print("two different instances created");
   }
}