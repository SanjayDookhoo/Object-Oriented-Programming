/* Name : Sachin Rajkumar
   ID: 814001729
   AssignmentNo: 2
   Course: COMP 2500
 */
public class Customer{
    private String addr;
    private String company;
    private int  custNo;
    
    //constructor
    Customer(){}
    
    Customer(int cNo, String c, String a){
        this.setCustNo(cNo);
        this.setCompany(c);
        this.setAddr(a);
        
    }
    
    
    //setters
    public void setCustNo(int cNo){
        this.custNo = cNo;
    }
    
    public void setCompany(String c){
        this.company = c;
    }
    
    public void setAddr(String a){
        this.addr = a;
    }
    
    
    //getters
    public int getCustNo(){
        return this.custNo;
    }
    
    public String getCompany(){
        return this.company;
    }
    
    public String getAddr(){
        return this.addr;
    }

    
    // print everything
    public void printInfo(){
        System.out.print("Customer Info: "+this.custNo+" ");
        System.out.print(this.company+" ");
        System.out.println(this.addr);
       
    }
}