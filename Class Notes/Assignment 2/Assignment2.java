import java.io.*;
import java.util.*;

public class Assignment2{
	public static void main(String []args){
		Scanner in = null;
		Scanner keyboard = new Scanner(System.in);
		
		String line;
		String[] parts;
		ProductCol productCol=new ProductCol();
		CustomerCol customerCol=new CustomerCol();
		OrderCol orderCol=new OrderCol();
		OrderDetailsCol orderDetailsCol=new OrderDetailsCol();
		InvoiceDetailsCol invoiceDetailsCol=new InvoiceDetailsCol();
		InvoiceCol invoiceCol= new InvoiceCol();
		
		//storing Product
		try {in = new Scanner (new FileReader("products.dat"));}
		catch (IOException err) {
			System.err.println(err.getMessage());
			System.exit(1);
		}
		in.nextLine();
		while(in.hasNext()){
            line=in.nextLine();
            parts = line.split(";");
            
            productCol.addProduct(parts[0],parts[1],Float.parseFloat(parts[2]));
        }
        

        //storing Customer
        try {in = new Scanner (new FileReader("customers.dat"));}
		catch (IOException err) {
			System.err.println(err.getMessage());
			System.exit(1);
		}
		in.nextLine();
		while(in.hasNext()){
            line=in.nextLine();
            parts = line.split(";");
            
            customerCol.addCustomer(Integer.parseInt(parts[0]),parts[1],parts[2]);
        }
		

		//storing orders
		try {in = new Scanner (new FileReader("orders.dat"));}
		catch (IOException err) {
			System.err.println(err.getMessage());
			System.exit(1);
		}
		in.nextLine();
		while(in.hasNext()){
            line=in.nextLine();
            parts = line.split(";");
            
            orderCol.addOrder(Integer.parseInt(parts[0]),customerCol.findCustomer(Integer.parseInt(parts[1])),parts[2]);
        }
        

		
		//storing orderItems
		try {in = new Scanner (new FileReader("orderitems.dat"));}
		catch (IOException err) {
			System.err.println(err.getMessage());
			System.exit(1);
		}
		in.nextLine();
		while(in.hasNext()){
            line=in.nextLine();
            parts = line.split(";");
            
            orderDetailsCol.addOrderDetails(orderCol.findOrder(Integer.parseInt(parts[0])),
            					productCol.findProduct(parts[1]),
            					Integer.parseInt(parts[2]),
            					Integer.parseInt(parts[3]));
        }
		
        //MENU
        
        int loop=1;
        
        while(loop==1){
        
	        System.out.printf("MENU: \n");
	        System.out.printf("1) Convert Order to an Invoice\n");
	        System.out.printf("2) Store invoice in a file system\n");
	        System.out.printf("3) Print an invoice \n");
	        System.out.printf("4) Print an order \n");
	        System.out.printf("5) Exit \n");
	        
			int opt = keyboard.nextInt();
	        
	        switch(opt){
	        	case 1:
	        		System.out.printf("Enter orderNo: ");
	        		opt = keyboard.nextInt();
	        		orderDetailsCol.toInvoice(invoiceCol,invoiceDetailsCol,orderCol,opt);
	        		System.out.printf("PROCESS SUCCESSFULL\n");
	        		System.out.printf("Choose another option?\n");
	        		System.out.printf("1) Yes?\n");
	        		System.out.printf("2) No?\n");
	        		opt = keyboard.nextInt();
	        		if(opt==2) loop=0;
	        		break;
	        	case 2:
	        		invoiceCol.saveAll();
	        		invoiceDetailsCol.saveAll();
	        		System.out.printf("PROCESS SUCCESSFULL\n");
	        		System.out.printf("Choose another option?\n");
	        		System.out.printf("1) Yes?\n");
	        		System.out.printf("2) No?\n");
	        		opt = keyboard.nextInt();
	        		if(opt==2) loop=0;
	        		break;
	        	case 3:
	        		System.out.printf("Please enter an invoice number to print: ");
	        		opt = keyboard.nextInt();
	        		System.out.printf(invoiceDetailsCol.toPrint(opt));
	        		System.out.printf("PROCESS SUCCESSFULL\n");
	        		System.out.printf("Choose another option?\n");
	        		System.out.printf("1) Yes?\n");
	        		System.out.printf("2) No?\n");
	        		opt = keyboard.nextInt();
	        		if(opt==2) loop=0;
	        		break;
	        	case 4:
	        		System.out.printf("Please enter an order number to print: ");
	        		opt = keyboard.nextInt();
	        		System.out.printf(orderDetailsCol.toPrint(opt));
	        		System.out.printf("PROCESS SUCCESSFULL\n");
	        		System.out.printf("Choose another option?\n");
	        		System.out.printf("1) Yes?\n");
	        		System.out.printf("2) No?\n");
	        		opt = keyboard.nextInt();
	        		if(opt==2) loop=0;
	        		break;
	        	case 5:
	        		System.exit(0);
	        	default:
	        		System.out.printf("INCORRECT OPTOION");
	        		System.out.printf("Choose another option?\n");
	        		System.out.printf("1) Yes?\n");
	        		System.out.printf("2) No?\n");
	        		opt = keyboard.nextInt();
	        		if(opt==2) loop=0;
	        		break;
	  		}
		}
	}
}

class Product{
	private String productNo;
	private String descr;
	private float listPrice;
	
	public Product(String productNo, String descr, float listPrice){
		this.productNo=productNo;
		this.descr=descr;
		this.listPrice=listPrice;
	}
	
	public String getProductNo(){
		return productNo;
	}
	
	public String getDescr(){
		return descr;
	}
	
	public float getListPrice(){
		return listPrice;
	}
	
	public String toString(){
		String description="Product No: "+productNo+"| Description: "+descr+"| listPrice: "+listPrice;
		return description;
	}
}

class ProductCol{
	private int count;
	private Product[] productCol;
	
	public ProductCol(){
		productCol=new Product[100];
		count=0;
	}
	
	public void addProduct(String productNo, String descr, float listPrice){
		if(count==productCol.length) increaseSize();
		
		productCol[count]=new Product(productNo,descr,listPrice);
		count++;
	}
	
	private void increaseSize ()   {
      Product[] temp = new Product[productCol.length * 2];
	  for (int x = 0; x < productCol.length; x++)
			temp[x] = productCol[x];
	  productCol = temp;
	}
	
	public Product findProduct(String productNo){
		for(int x=0;x<count;x++){
			if(productNo.compareTo(productCol[x].getProductNo())==0) return productCol[x];
		}
		System.out.printf("null was returned");
		return null;
	}
	
	public int getCount(){
		return count;
	}
	
	public Product getProduct(int x){
		return productCol[x];
	}
	
}

class Customer{
	private int custNo;
	private String company;
	private String addr;
	
	public Customer(int custNo,String company, String addr){
		this.custNo=custNo;
		this.company=company;
		this.addr=addr;
	}
	
	public int getCustomerNo(){
		return custNo;
	}
	
	public String getCompany(){
		return company;
	}
	
	public String getAddr(){
		return addr;
	}
	
	public String toString(){
		String description="Customer No: "+custNo+"| Company: "+company+"| address: "+addr;
		return description;
	}
}

class CustomerCol{
	private Customer[] customerCol;
	private int count;
	
	public CustomerCol(){
		customerCol=new Customer[100];
		count=0;
	}
	
	public void addCustomer(int custNo,String company, String addr){
		customerCol[count]=new Customer(custNo,company,addr);
		count++;
	}
	
	private void increaseSize (){
      Customer[] temp = new Customer[customerCol.length * 2];
	  for (int x = 0; x < customerCol.length; x++)
			temp[x] = customerCol[x];
	  customerCol = temp;
	}
	
	public Customer findCustomer(int custNo){
		for (int x = 0; x < customerCol.length; x++) 
			if(custNo==customerCol[x].getCustomerNo()) return customerCol[x];
		return null;
	}
	
	public int getCount(){
		return count;
	}
	
	public Customer getCustomer(int x){
		return customerCol[x];
	}
}

abstract class OrderInvoice{
	protected Customer cust;
	protected String routeNo;
	
	public OrderInvoice(Customer cust,String routeNo){
		this.cust=cust;
		this.routeNo=routeNo;
	}
}

class Order extends OrderInvoice{
	private int orderNo;
	
	public Order(int orderNo,Customer cust,String routeNo){
		super(cust,routeNo);
		this.orderNo=orderNo;
	}
	
	public int getOrderNo(){
		return orderNo;
	}
	
	
	public Customer getCust(){
		return cust;
	}
	
	public String getRoute(){
		return routeNo;
	}
	
	
	public  String toString(){
		String description="OrderNo: "+orderNo+"| customer NO: "+cust.getCustomerNo()+"|route No: "+routeNo;
		return description;
	}
	
}

class OrderCol{
	private Order[] orderCol;
	private int count;
	private Order temp;
	
	public OrderCol(){
		orderCol=new Order[100];
		count=0;
	}
	
	public void addOrder(int orderNo,Customer cust,String routeNo){
		orderCol[count]=new Order(orderNo,cust,routeNo);
		count++;
	}
	
	private void increaseSize (){
      Order[] temp = new Order[orderCol.length * 2];
	  for (int x = 0; x < orderCol.length; x++)
			temp[x] = orderCol[x];
	  orderCol = temp;
	}
	
	public Order findOrder(int orderNo){
		for (int x = 0; x < orderCol.length; x++)
			if(orderNo==orderCol[x].getOrderNo()) return orderCol[x];
		return null;
	}
	
	public int getCount(){
		return count;
	}
	
	public void removeOrder(int orderNo){
		Order order=this.findOrder(orderNo);
		for(int x=0;x<count;x++){
			if(order==orderCol[x]){
				for(int y=x;y<count-1;y++){
					temp=orderCol[y];
					orderCol[y]=orderCol[y+1];
					orderCol[y+1]=orderCol[y];
				}
				count--;
				break;
			}
		}
	}
	
	public Order getOrder(int x){
		return orderCol[x];
	}
}

class Invoice extends OrderInvoice{
	private int invoiceNo;
	private int orderNo;
	
	public Invoice(int invoiceNo, int orderNo,Customer cust,String routeNo){
		super(cust,routeNo);
		this.invoiceNo=invoiceNo;
		this.orderNo=orderNo;
	}
	
	public int getInvoiceNo(){
		return invoiceNo;
	}
	
	public int getOrderNo(){
		return orderNo;
	}
	
	public String getRoute(){
		return routeNo;
	}
	
	public Customer getCust(){
		return cust;
	}
	
	public String toString(){
		String description="InvoiceNo: "+invoiceNo+"| OrderNo: "+orderNo+"| customer NO: "+cust.getCustomerNo()+"|route No: "+routeNo;
		return description;
	}
}

class InvoiceCol{
	private Invoice[] invoiceCol;
	private int count;
	
	public InvoiceCol(){
		invoiceCol=new Invoice[100];
		count=0;
	}
	
	public Invoice addInvoice(int orderNo,Customer cust,String routeNo){
		invoiceCol[count]=new Invoice(count+1,orderNo,cust,routeNo);
		count++;
		
		return invoiceCol[count-1];
	}
	
	private void increaseSize (){
      Invoice[] temp = new Invoice[invoiceCol.length * 2];
	  for (int x = 0; x < invoiceCol.length; x++)
			temp[x] = invoiceCol[x];
	  invoiceCol = temp;
	}
	
	public Invoice findInvoice(int invoiceNo){
		for (int x = 0; x < invoiceCol.length; x++)
			if(invoiceNo==invoiceCol[x].getInvoiceNo()) return invoiceCol[x];
		return null;
	}
	
	public int getCount(){
		return count;
	}
	
	public Invoice getInvoice(int x){
		return invoiceCol[x];
	}
	
	public void saveAll(){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("invoices.dat", false)));
			
			//print heading
			out.printf("invoiceno;orderno;custno;routeno\n");
			
			//print data
			for(int x=0;x<count;x++){
				out.printf(invoiceCol[x].getInvoiceNo()+";");
				out.printf(invoiceCol[x].getOrderNo()+";");
				out.printf(invoiceCol[x].getCust().getCustomerNo()+";");
				out.printf(invoiceCol[x].getRoute()+"\n");
			}
			out.close();
		}catch(IOException err){
			System.out.printf("Error");
		}
	
		
		
	}
}

class InvoiceDetails{
	private Invoice invoice;
	private Product product;
	private int packs;
	private int qty;
	
	public InvoiceDetails(Invoice invoice, Product product, int packs,int qty){
		this.invoice=invoice;
		this.product=product;
		this.packs=packs;
		this.qty=qty;
	}
	
	public String toString(){
		String description="Invoice No: "+invoice.getInvoiceNo()+"| Product NO: "+product.getProductNo()+"| packs: "+packs+"| Qty: "+qty;
		return description;
	}
	
	public Invoice getInvoice(){
		return invoice;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public int getPacks(){
		return packs;
	}
	
	public int getQty(){
		return qty;
	}
}

class InvoiceDetailsCol{
	private InvoiceDetails[] invoiceDetailsCol;
	private int count;
	
	public InvoiceDetailsCol(){
		invoiceDetailsCol=new InvoiceDetails[100];
		count=0;
	}
	
	public void addInvoiceDetails(Invoice invoice, Product product, int packs, int qty){
		invoiceDetailsCol[count]=new InvoiceDetails(invoice,product,packs,qty);
		count++;
	}
	
	private void increaseSize (){
      InvoiceDetails[] temp = new InvoiceDetails[invoiceDetailsCol.length * 2];
	  for (int x = 0; x < invoiceDetailsCol.length; x++)
			temp[x] = invoiceDetailsCol[x];
	  invoiceDetailsCol = temp;
	}
	
	public int getCount(){
		return count;
	}
	
	public InvoiceDetails getInvoiceDetails(int x){
		return invoiceDetailsCol[x];
	}
	
	public String toPrint(int invoiceNo){
		float total2=0;
		int qty;
		float finalTotal=0;
		int state=0;
		Invoice invoice;
		
		String formatHead="\n%-8s%-8s%-8s%-30s%-8s%-8s";
		String formatData="\n%-8d%-8d%-8s%-30s%-8.2f%-8.2f";
		
		String description="";
		description+="\nInvoiceNo: "+invoiceNo;
		
		for(int x=0;x<count;x++){
			invoice=invoiceDetailsCol[x].getInvoice();
			if(invoiceDetailsCol[x].getInvoice().getInvoiceNo()==invoiceNo){
				if(state==0){//when a row is found that corresponds with the orderNO, that row will possess a reference to that Order which is used to print related Order data
					description+="\nOrderNo: "+invoice.getOrderNo();
					description+="\nRoute No: "+invoice.getRoute();
					
					//customer related data from Order
					description+="\nCustomer No: "+invoice.getCust().getCustomerNo();
					description+="\nCustomer Company: "+invoice.getCust().getCompany();
					description+="\nCustomer Address: "+invoice.getCust().getAddr();
					
					//heading
					description+=String.format(formatHead,"Qty","Packs","Item","Description","Price","Total");
					
					state=1;//print this set of data only once despite mroe than one orderDetails possibly existing
				}
				
				total2=invoiceDetailsCol[x].getPacks() * invoiceDetailsCol[x].getProduct().getListPrice();
				//total2=orderDetailsCol[x].get ;
				qty=invoiceDetailsCol[x].getQty();
				finalTotal+=total2;
				
				description+=String.format(formatData,
										   invoiceDetailsCol[x].getQty(),
										   invoiceDetailsCol[x].getPacks(),
										   invoiceDetailsCol[x].getProduct().getProductNo(),
										    invoiceDetailsCol[x].getProduct().getDescr(),
										   invoiceDetailsCol[x].getProduct().getListPrice(),
										    total2);
			}
		}
		description+="\n";
		for(int x=0;x<70;x++)description+="-";
		description+="\n\nFinal Total: "+finalTotal+"\n";
		
		description+="\n";
		for(int x=0;x<70;x++)description+="=";
		description+="\n";
		
		return description;
		
	}
	
	public void saveAll(){
		
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("invoiceitems.dat", false)));
			
			//print heading
			out.printf("invoiceno;partno;packs;qty\n");
			
			//print data
			for(int x=0;x<count;x++){
				out.printf(invoiceDetailsCol[x].getInvoice().getInvoiceNo()+";");
				out.printf(invoiceDetailsCol[x].getProduct().getProductNo()+";");
				out.printf(invoiceDetailsCol[x].getPacks()+";");
				out.printf(invoiceDetailsCol[x].getQty()+"\n");
			}
			
			out.close();
		}catch(IOException err){
			System.out.printf("Error");
		}
	}
}

class OrderDetails{
	private Order order;
	private Product product;
	private int packs;
	private int qty;
	
	public OrderDetails(Order order, Product product, int packs, int qty){
		this.order=order;
		this.product=product;
		this.packs=packs;
		this.qty=qty;
	}
	
	public Order getOrder(){
		return order;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public int getPacks(){
		return packs;
	}
	
	public int getQty(){
		return qty;
	}
	
	public String toString(){
		String description="Order No: "+order.getOrderNo()+"| Product NO: "+product.getProductNo()+"| packs: "+packs+"| Qty: "+qty;
		return description;
	}
	
}

class OrderDetailsCol{
	private OrderDetails[] orderDetailsCol;
	private OrderDetails temp;
	private int count;
	
	public OrderDetailsCol(){
		orderDetailsCol=new OrderDetails[100];
		count=0;
	}
	
	public void addOrderDetails(Order order, Product product, int packs, int qty){
		orderDetailsCol[count]=new OrderDetails(order,product,packs,qty);
		count++;
	}
	
	public int findOrderIndex(int order){
		for(int x=0;x<count;x++){
			if(orderDetailsCol[x].getOrder().getOrderNo()==order){
				return x;
			}
		}
		return 0;
	}
	
	public void removeOrderDetails(int orderNo){
		int i=findOrderIndex(orderNo);
		for(int x=i;x<count-1;x++){
			temp=orderDetailsCol[x+1];
			orderDetailsCol[x+1]=orderDetailsCol[x];
			orderDetailsCol[x]=temp;
		}		
		count--;
	}
	
	private void increaseSize (){
      OrderDetails[] temp = new OrderDetails[orderDetailsCol.length * 2];
	  for (int x = 0; x < orderDetailsCol.length; x++)
			temp[x] = orderDetailsCol[x];
	  orderDetailsCol = temp;
	}
	
	public int getCount(){
		return count;
	}
	
	public void toInvoice(InvoiceCol invoiceCol, InvoiceDetailsCol invoiceDetailsCol, OrderCol orderCol, int orderNo){
		Order order;
		int state=0,removed=0;
		Invoice temp=null;
		for(int x=0;x<count;x++){
			if(orderDetailsCol[x].getOrder().getOrderNo()==orderNo){
				order=orderDetailsCol[x].getOrder();
				
				if(state==0){
					//copy order to invoice
					temp=invoiceCol.addInvoice(orderNo,order.getCust(),order.getRoute());
					
					//remove order
					orderCol.removeOrder(orderNo);
					
					state=1;
				}
				
				int originalCount=count;
				while(orderDetailsCol[x].getOrder().getOrderNo()==orderNo && x+removed<originalCount){
					//copy orderDetails to invoicDetails
					invoiceDetailsCol.addInvoiceDetails(temp,orderDetailsCol[x].getProduct(),orderDetailsCol[x].getPacks(),orderDetailsCol[x].getQty());
					
					//remove orderDetails
					this.removeOrderDetails(orderNo);
					
					removed++;
				}
				break;
				
			}
		}
	}
	
	public String toPrint(int orderNo){
		float total2=0;
		int qty;
		float finalTotal=0;
		int state=0;
		Order order;
		
		String formatHead="\n%-8s%-8s%-8s%-30s%-8s%-8s";
		String formatData="\n%-8d%-8d%-8s%-30s%-8.2f%-8.2f";
		
		String description="";
		description+="\nOrderNo: "+orderNo;
		for(int x=0;x<count;x++){
			order=orderDetailsCol[x].getOrder();
			if(orderDetailsCol[x].getOrder().getOrderNo()==orderNo){
				if(state==0){//when a row is found that corresponds with the orderNO, that row will possess a reference to that Order which is used to print related Order data
					description+="\nRoute No: "+order.getRoute();
					
					//customer related data from Order
					description+="\nCustomer No: "+order.getCust().getCustomerNo();
					description+="\nCustomer Company: "+order.getCust().getCompany();
					description+="\nCustomer Address: "+order.getCust().getAddr();
					
					//heading
					description+=String.format(formatHead,"Qty","Packs","Item","Description","Price","Total");
					
					state=1;//print this set of data only once despite mroe than one orderDetails possibly existing
				}
				
				total2=orderDetailsCol[x].getPacks() * orderDetailsCol[x].getProduct().getListPrice();
				//total2=orderDetailsCol[x].get ;
				qty=orderDetailsCol[x].getQty();
				finalTotal+=total2;
				
				description+=String.format(formatData,
										   orderDetailsCol[x].getQty(),
										   orderDetailsCol[x].getPacks(),
										   orderDetailsCol[x].getProduct().getProductNo(),
										    orderDetailsCol[x].getProduct().getDescr(),
										   orderDetailsCol[x].getProduct().getListPrice(),
										    total2);
			}
		}
		description+="\n";
		for(int x=0;x<70;x++)description+="-";
		description+="\n\nFinal Total: "+finalTotal+"\n";
		
		description+="\n";
		for(int x=0;x<70;x++)description+="=";
		description+="\n";
		
		return description;
	}
	
	public OrderDetails getOrderDetails(int x){
		return orderDetailsCol[x];
	}
}
