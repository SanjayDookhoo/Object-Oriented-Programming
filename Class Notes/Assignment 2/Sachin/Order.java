/* Name : Sachin Rajkumar
   ID: 814001729
   AssignmentNo: 2
   Course: COMP 2500
 */  
public class Order{
        
    private int orderNo;
    private Customer c = new Customer();
    private Product[] p = new Product[20];
    private String routeNo;
    private int numProducts=0;
    private double totalCost = 0;
    
    //constructor 
    Order(){}

    Order(int o, Customer cus, String route){
        this.setOrderNo(o);
        this.setCustomer(cus);
        this.setRouteNo(route);
    }
    
    
    //setters
    public void setOrderNo(int o){
        this.orderNo = o;
    }
    
    public void setCustomer(Customer cus){
        this.c = cus;
    }
    
    public void setProduct(Product[] pr){
        this.p = pr;
    }
    
    public void setRouteNo(String r){
        this.routeNo = r;
    }
    
    public void setNumProducts(int n){
        this.numProducts = n;
    }
    
    //getters
    
    public int getOrderNo(){
        return this.orderNo;
    }
    
    public Customer getCustomer(){
        return this.c;
    }
    
    public Product[] getProduct(){
        return this.p;
    }
    
    public String getRouteNo(){
        return this.routeNo;
    }
    
    public int getNumProducts(){
        return this.numProducts;
    }

    // print everything
    public void printInfo(){
        
        System.out.println("Order Number: "+this.orderNo);
        this.c.printInfo();
        for (int i=0; i<this.numProducts; i++){
            this.p[i].printInfo();
            this.totalCost = this.totalCost + (this.p[i].getPacks() * this.p[i].getListPrice());
        }
        System.out.println("Route Number: "+this.routeNo);
        System.out.println("Total Cost: "+this.totalCost);
        System.out.println("Number of Products: "+this.numProducts);
        System.out.println("Number of Packs of Products: "+sumProducts());
        System.out.println("Total Cost: $"+this.totalCost);
    }
    
    //post an order
    public Invoice postOrder(){
        
        Invoice in = new Invoice(this.orderNo,this.c,this.p,this.routeNo,this.numProducts,this.totalCost);
            
        return in;
     
    }
    
    // sum of products in order
    public int sumProducts(){
        int num=0;
      
        for (int i=0;i<this.numProducts;i++){
            num = num + this.p[i].getPacks();
        }
        
        return num;
    }
    
    
    
}