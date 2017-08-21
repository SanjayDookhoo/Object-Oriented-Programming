import java.io.*;
import java.util.*;

public class Assignment3{
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
		try {in = new Scanner (new FileReader("FileSystem/products.dat"));}
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
        try {in = new Scanner (new FileReader("FileSystem/customers.dat"));}
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
		try {in = new Scanner (new FileReader("FileSystem/orders.dat"));}
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
		try {in = new Scanner (new FileReader("FileSystem/orderitems.dat"));}
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
	        		orderCol.saveAll();
	        		orderDetailsCol.saveAll();
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
	List<Product> productCol = new ArrayList<Product>(); 

	public void addProduct(String productNo, String descr, float listPrice){
		productCol.add(new Product(productNo,descr,listPrice));
	}
	
	public Product findProduct(String productNo){
		for(Product x: productCol){
			if(productNo.compareTo(x.getProductNo())==0) return x;
		}
		System.out.printf("null was returned");
		return null;
	}
	
	public int getCount(){
		return productCol.size();
	}
	
	public Product getProduct(int x){
		return productCol.get(x);
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
	List<Customer> customerCol = new ArrayList<Customer>(); 
	
	public void addCustomer(int custNo,String company, String addr){
		customerCol.add(new Customer(custNo,company,addr));
	}
	
	public Customer findCustomer(int custNo){
		for(Customer c: customerCol){
			if(c.getCustomerNo()==custNo) return c;
		}
		return null;
	}
	
	public int getCount(){
		return customerCol.size();
	}
	
	public Customer getCustomer(int x){
		return customerCol.get(x);
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
	List<Order> orderCol = new ArrayList<Order>(); 
	private Order temp;
	
	public void addOrder(int orderNo,Customer cust,String routeNo){
		orderCol.add(new Order(orderNo,cust,routeNo));
	}

	public Order findOrder(int orderNo){
		for (Order x: orderCol)
			if(orderNo==x.getOrderNo()) return x;
		return null;
	}
	
	public int getCount(){
		return orderCol.size();
	}
	
	public void removeOrder(int orderNo){
		orderCol.remove(findOrder(orderNo));
	}
	
	public Order getOrder(int x){
		return orderCol.get(x);
	}
	
	public void saveAll(){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("FileSystem/orders.dat", false)));
			
			//print heading
			out.printf("orderno;custno;routeno\n");
			
			//print data
			for(Order x: orderCol){
				out.printf(x.getOrderNo()+";");
				out.printf(x.getCust().getCustomerNo()+";");
				out.printf(x.getRoute()+"\n");
			}
			out.close();
		}catch(IOException err){
			System.out.printf("Error");
		}
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
	List<Invoice> invoiceCol = new ArrayList<Invoice>(); 
	
	public Invoice addInvoice(int orderNo,Customer cust,String routeNo){
		invoiceCol.add(new Invoice(invoiceCol.size()+1,orderNo,cust,routeNo));
		
		return invoiceCol.get(invoiceCol.size()-1);
	}
	
	public Invoice findInvoice(int invoiceNo){
		for (Invoice i: invoiceCol)
			if(invoiceNo==i.getInvoiceNo()) return i;
		return null;
	}
	
	public int getCount(){
		return invoiceCol.size();
	}
	
	public Invoice getInvoice(int x){
		return invoiceCol.get(x);
	}
	
	public void saveAll(){
		try{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("FileSystem/invoices.dat", false)));
			
			//print heading
			out.printf("invoiceno;orderno;custno;routeno\n");
			
			//print data
			for(Invoice x: invoiceCol){
				out.printf(x.getInvoiceNo()+";");
				out.printf(x.getOrderNo()+";");
				out.printf(x.getCust().getCustomerNo()+";");
				out.printf(x.getRoute()+"\n");
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
	List<InvoiceDetails> invoiceDetailsCol = new ArrayList<InvoiceDetails>(); 
	
	public void addInvoiceDetails(Invoice invoice, Product product, int packs, int qty){
		invoiceDetailsCol.add(new InvoiceDetails(invoice,product,packs,qty));
	}
	
	public int getCount(){
		return invoiceDetailsCol.size();
	}
	
	public InvoiceDetails getInvoiceDetails(int x){
		return invoiceDetailsCol.get(x);
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
		
		for(InvoiceDetails x: invoiceDetailsCol){
			invoice=x.getInvoice();
			if(x.getInvoice().getInvoiceNo()==invoiceNo){
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
				
				total2=x.getPacks() * x.getProduct().getListPrice();
				qty=x.getQty();
				finalTotal+=total2;
				
				description+=String.format(formatData,
										   x.getQty(),
										   x.getPacks(),
										   x.getProduct().getProductNo(),
										   x.getProduct().getDescr(),
										   x.getProduct().getListPrice(),
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
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("FileSystem/invoiceitems.dat", false)));
			
			//print heading
			out.printf("invoiceno;partno;packs;qty\n");
			
			//print data
			for(InvoiceDetails x: invoiceDetailsCol){
				out.printf(x.getInvoice().getInvoiceNo()+";");
				out.printf(x.getProduct().getProductNo()+";");
				out.printf(x.getPacks()+";");
				out.printf(x.getQty()+"\n");
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
	List<OrderDetails> orderDetailsCol = new ArrayList<OrderDetails>();
	private OrderDetails temp;

	public void addOrderDetails(Order order, Product product, int packs, int qty){
		orderDetailsCol.add(new OrderDetails(order,product,packs,qty));
	}
	
	public int findOrderIndex(int order){
		for(int x=0;x<orderDetailsCol.size();x++){
			if(orderDetailsCol.get(x).getOrder().getOrderNo()==order){
				return x;
			}
		}
		return 0;
	}
	
	public void removeOrderDetails(int orderNo){
		/*int i=findOrderIndex(orderNo);
		for(int x=i;x<count-1;x++){
			temp=orderDetailsCol[x+1];
			orderDetailsCol[x+1]=orderDetailsCol[x];
			orderDetailsCol[x]=temp;
		}		
		count--;*/
		orderDetailsCol.remove(findOrderIndex(orderNo));
	}
	
	public int getCount(){
		return  orderDetailsCol.size();
	}
	
	public void toInvoice(InvoiceCol invoiceCol, InvoiceDetailsCol invoiceDetailsCol, OrderCol orderCol, int orderNo){
		Order order;
		int state=0,removed=0;
		Invoice temp=null;
		for(int x=0;x<orderDetailsCol.size();x++){
			if(orderDetailsCol.get(x).getOrder().getOrderNo()==orderNo){
				order=orderDetailsCol.get(x).getOrder();
				
				if(state==0){
					//copy order to invoice
					temp=invoiceCol.addInvoice(orderNo,order.getCust(),order.getRoute());
					
					//remove order
					orderCol.removeOrder(orderNo);
					
					state=1;
				}
				
				int originalCount=orderDetailsCol.size();
				while(orderDetailsCol.get(x).getOrder().getOrderNo()==orderNo && x+removed<originalCount){
					//copy orderDetails to invoicDetails
					invoiceDetailsCol.addInvoiceDetails(temp,orderDetailsCol.get(x).getProduct(),orderDetailsCol.get(x).getPacks(),orderDetailsCol.get(x).getQty());
					
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
		for(OrderDetails x: orderDetailsCol){
			order=x.getOrder();
			if(x.getOrder().getOrderNo()==orderNo){
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
				
				total2=x.getPacks() * x.getProduct().getListPrice();
				qty=x.getQty();
				finalTotal+=total2;
				
				description+=String.format(formatData,
										   x.getQty(),
										   x.getPacks(),
										   x.getProduct().getProductNo(),
										   x.getProduct().getDescr(),
										   x.getProduct().getListPrice(),
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
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("FileSystem/orderitems.dat", false)));
			
			//print heading
			out.printf("orderno;partno;packs;qty\n");
			
			//print data
			for(OrderDetails x: orderDetailsCol){
				out.printf(x.getOrder().getOrderNo()+";");
				out.printf(x.getProduct().getProductNo()+";");
				out.printf(x.getPacks()+";");
				out.printf(x.getQty()+"\n");
			}
			
			out.close();
		}catch(IOException err){
			System.out.printf("Error");
		}
	}
	
	public OrderDetails getOrderDetails(int x){
		return orderDetailsCol.get(x);
	}
}
