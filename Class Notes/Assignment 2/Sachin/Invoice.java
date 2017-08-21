/* Name : Sachin Rajkumar
   ID: 814001729
   AssignmentNo: 2
   Course: COMP 2500
 */
public class Invoice{
        
    private int invoiceNo;
    private int orderNo;
    private Customer c = new Customer();
    private Product[] p = new Product[20];
    private String routeNo;
    private int numProducts;
    private double totalCost = 0;
    
    
    // constructor
    Invoice(){}
    
    Invoice(int o, Customer cus, Product[] pro, String route, int n, double cost){
        this.setOrderNo(o);
        this.setCustomer(cus);
        this.setProduct(pro);
        this.setRouteNo(route);
        this.setNumProducts(n);  
        this.setTotalCost(cost);
    }
    
    
    
    //setters
    public void setInvoiceNo(int i){
        this.invoiceNo = i;
    }
    
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
    
    public void setTotalCost(double c){
        this.totalCost = c;
    }
    
    //getters
    public int getInvoiceNo(){
        return this.invoiceNo;
    }
    
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
        System.out.println("Invoice Number: "+this.invoiceNo);
        System.out.println("Order Number: "+this.orderNo);
        this.c.printInfo();
        for (int i=0; i<this.numProducts; i++){
            this.p[i].printInfo();
        }
        System.out.println("Route Number: "+this.routeNo);
        
        System.out.println("Number of Products: "+this.numProducts);
        System.out.println("Number of Packs of Products: "+sumProducts());
        System.out.println("Total Cost: $"+this.totalCost);
    }
    
    // sum of products in order
    public int sumProducts(){
        int num=0;
        for (int i=0;i<this.numProducts;i++){
            num = num + p[i].getPacks();
        }
        
        return num;
    }
}