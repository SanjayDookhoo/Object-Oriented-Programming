/* Name : Sachin Rajkumar
   ID: 814001729
   AssignmentNo: 2
   Course: COMP 2500
 */
import java.util.Scanner; 
import java.io.*;
import java.lang.*;

public class ZBakery{
    private Customer[] cArr = new Customer[20];
    private Product[] totalPArr = new Product[20];
    private Order[] oArr = new Order[20];
    private Invoice[] iArr = new Invoice[20];
    private int numCust;
    private int numOrd;
    private int numPro;
    private int numInv=1001;
    private int countInv=0;
    
    
    public int readCustomerFile(){
        int custNo;
        String company,addr;
        int count = 0;
        Scanner scan = null;
        try {scan = new Scanner(new FileReader("customers.dat"));}
        catch (IOException err) {
            System.out.println("Cannot open customers.dat");
            System.exit(1);
        }
        String rubbish = scan.nextLine();
        scan.useDelimiter("\\s*;\\s*|\\s*\n\\s*");
        
        while(scan.hasNext()){
            custNo=scan.nextInt();
            company=scan.next();
            addr=scan.next();
            this.cArr[count++]= new Customer(custNo,company,addr); 

        }
        scan.close();
        this.numCust=count;
        return count;
    }

    public int readProductFile(){
        double listP;
        String pNo,desc;
        int count = 0;
        Scanner scan = null;
        try {scan = new Scanner(new FileReader("products.dat"));}
        catch (IOException err) {
            System.out.println("Cannot open products.dat");
            System.exit(1);
        }
        String rubbish = scan.nextLine();
        scan.useDelimiter("\\s*;\\s*|\\s*\n\\s*");
        
        while(scan.hasNext()){
            pNo=scan.next();
            desc=scan.next();
            listP=scan.nextDouble();
            this.totalPArr[count++] = new Product(pNo,desc,listP);

        }
        scan.close();
        this.numPro=count;
        return count;
    }
    
    public int readOrderFile(){
        int oNo,cNo;
        String route;
        int count = 0;
        Scanner scan = null;
        try {scan = new Scanner(new FileReader("orders.dat"));}
        catch (IOException err) {
            System.out.println("Cannot open orders.dat");
            System.exit(1);
        }
        String rubbish = scan.nextLine();
        scan.useDelimiter("\\s*;\\s*|\\s*\n\\s*");
        
        while(scan.hasNext()){
            oNo=scan.nextInt();
            cNo=scan.nextInt();
            route=scan.next();
            Customer c = searchCArr(cNo,this.numCust);
            oArr[count++] = new Order(oNo,c,route);
        }
        scan.close();
        this.numOrd=count;
        return count;
    }
    
    public Customer searchCArr(int cNo, int n){
        for(int i=0;i<=n;i++){
            if (cArr[i].getCustNo() == cNo) return cArr[i];
        }
        Customer c = new Customer();
        System.out.println("Can't find CustomerNo");
        return c;
    }
    
    
    public void readOrderItemFile(){
        int oNo,packs,qty;
        String pNo;
        
        Scanner scan = null;
        try {scan = new Scanner(new FileReader("orderitems.dat"));}
        catch (IOException err) {
            System.out.println("Cannot open orders.dat");
            System.exit(1);
        }
        String rubbish = scan.nextLine();
        scan.useDelimiter("\\s*;\\s*|\\s*\n\\s*");
        
        while(scan.hasNext()){
            oNo=scan.nextInt();
            pNo=scan.next();
            packs=scan.nextInt();
            qty=scan.nextInt();
            
            Order o = searchOArr(oNo);
            Product tempP = searchPArr(pNo);
            tempP.setPacks(packs);
            tempP.setQty(qty);
            
            Product[] p = o.getProduct();
            
            int n = o.getNumProducts();
            p[n++]=tempP;
            o.setNumProducts(n);
            o.setProduct(p);
        
        }
        scan.close();
        
    }
    
    public Order searchOArr(int oNo){
        for(int i=0;i<this.numOrd;i++){
            if (oArr[i].getOrderNo() == oNo) return oArr[i];
        }
        Order o = new Order();
        System.out.println("Can't find orderNo");
        return o;
    }
    
    public Product searchPArr(String pNo){
        for(int i=0;i<this.numPro;i++){
            if (totalPArr[i].getProductNo().equals(pNo)) {
                Product np = new Product(totalPArr[i].getProductNo(),totalPArr[i].getDescription(),totalPArr[i].getListPrice());
                return np;
            }
        }
        Product p = new Product();
        System.out.println("Can't find productNo");
        return p;
    }
    
    public void printOrder(int n){
        for (int i=0; i<this.numOrd;i++){
            if  (oArr[i].getOrderNo() == n) oArr[i].printInfo();
        }
    }
    
    public Order searchAndDeleteOArr(int oNo){
        for(int i=0;i<this.numOrd;i++){
            if (oArr[i].getOrderNo() == oNo){
                Order or = oArr[i];
                oArr[i]=null;
                return or;
            } 
        }
        Order o = new Order();
        System.out.println("Can't find orderNo");
        return o;
    }
    
    public void postOrder(int n){
        Order o = searchAndDeleteOArr(n);
        Invoice in = o.postOrder();
        in.setInvoiceNo(this.numInv);
        this.numInv++;
        this.iArr[countInv++]=in;
    }
    
    public void printInvoice(int n){
        for (int i=0; i<this.countInv;i++){
            if  (iArr[i].getOrderNo() == n) iArr[i].printInfo();
        }
    }
    
    public void sumProductsInOrder(int n){
        int num=0;
        for (int i=0; i<this.numOrd;i++){
            if  (oArr[i].getOrderNo() == n) 
                num = oArr[i].sumProducts();
        }
        System.out.println(num);
    }
        
    public void sumProductsInInvoice(int n){
        int num=0;
        for (int i=0; i<this.countInv;i++){
            if  (iArr[i].getInvoiceNo() == n) 
                num = iArr[i].sumProducts();
        }
        System.out.println(num);
    }
    
    public void addOrder(int o,int c,String r,Product[] p,int n){
        Customer tempC = this.searchCArr(c,this.numCust);
        this.oArr[numOrd]= new Order(o,tempC,r);
        this.oArr[numOrd].setProduct(p);
        this.oArr[numOrd++].setNumProducts(n);
    }
}

