/* Name : Sachin Rajkumar
   ID: 814001729
   AssignmentNo: 2
   Course: COMP 2500
 */
public class Product{
    private String productNo;
    private String description;
    private double listPrice;
    private int packs;
    private int qty;
    
    // constructor
    
    Product(){}
    
    Product(String p, String d, double l){
        this.setProductNo(p);
        this.setDescription(d);
        this.setListPrice(l);
       
    }
    
    //setters
    public void setProductNo(String p){
        this.productNo = p;
    }
    
    public void setDescription(String d){
        this.description = d;
    }
    
    public void setListPrice(double l){
        this.listPrice = l;
    }
    
    public void setPacks(int pa){
        this.packs = pa;
    }
    
    public void setQty(int q){
        this.qty = q;
    }
    
    
    
    //getters
    public String getProductNo(){
        return this.productNo;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public double getListPrice(){
        return this.listPrice;
    }
    
    public int getPacks(){
        return this.packs;
    }
    
    public int getQty(){
        return this.qty;
    }
    
    // print everything
    public void printInfo(){
        System.out.print("Product Info: "+this.productNo+" ");
        System.out.print(this.description+" ");
        System.out.print("Cost: $"+this.listPrice+" ");
        System.out.print("Packs: "+this.packs+" ");
        System.out.println("Qty: "+this.qty);
        
    }

}