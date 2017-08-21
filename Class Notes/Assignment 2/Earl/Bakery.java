import java.util.*;
import java.io.*;

public class Bakery{
	public static void main (String args[]){
		CustomerCollection custList=new CustomerCollection();
		OrderCollection OrdList=new OrderCollection();
		InvoiceCollection InvList=new InvoiceCollection();
		
		System.out.println("1-Orders\n2-Invoices\n3-Customers\n0-Exit");
		Scanner in = new Scanner(System.in); 
		int opt = in.nextInt();
		
		while(opt!=0){
			if(opt==1){
				System.out.println(OrdList);
				System.out.println("\nType Order Number to post or 0 to go back");
				opt = in.nextInt();
				if(opt!=0){
					OrdList.post(String.valueOf(opt),OrdList,InvList);
					OrdList.save();
					InvList.save();
				}
			}else if(opt==2){
				System.out.println(InvList);
			}else if(opt==3){
				System.out.println(custList);
			}
			
			System.out.println("1-Orders\n2-Invoices\n3-Customers\n0-Exit");
			opt = in.nextInt();
		}
	}	
}

interface Collection{ 
	public void add(String a, String b, String c);
	public void increaseSize();
	public Object[] getColl();
}

interface iFindnCalc{
	public ItemCollection searchItems(String onum);
	public Product searchProducts(String partno);
	public double toDouble(String doub);
	public double calcTotal(String orderNum);
}

abstract class DataHolder implements Collection{
	int count;
	
	public void load(String file){
		Scanner data=null;
		try{
			data=new Scanner(new FileReader("ordersystem_files/"+file));
		}catch(IOException err){
			System.err.println("Error opening file");
			System.exit(1);
		}
	
		String line=data.nextLine();
		String[] values=line.split(";",-1);
		int fields=values.length;//Number of fields
		while(data.hasNextLine()&&(values=data.nextLine().split(";",-1)).length>=fields)
			this.add(values[0],values[1],values[2]);	
	}
	
	public int getCount(){
		return this.count;
	}
}

class Order{
	private String orderno,custno,routeno;
	
	public Order(String orderno, String custno,String routeno){
		this.orderno=orderno;
		this.custno=custno;
		this.routeno=routeno;
	}
	
	public String getOrderNo(){
		return orderno;
	}
	
	public String toString(){
		String ordDetails;
		ordDetails=orderno+";"+custno+";"+routeno;
		return ordDetails;
	}
}

class OrderCollection extends DataHolder implements iFindnCalc{
	private Order[] collection;
	
	ProductCollection ProdList=new ProductCollection();
	ItemCollection ItemList=new ItemCollection();
	
	public OrderCollection(){
		collection=new Order[100];
		count=0;
		load("orders.dat");
	}
	
	public ItemCollection searchItems(String onum){
		int i=0;
		ItemCollection Result=new ItemCollection(0);
		Item[] IColl=ItemList.getColl();
		for(i=0;i<ItemList.getCount();i++)
			if(IColl[i].getOrderNo().equals(onum)){
				Result.add(IColl[i++]);
				break;
			}
		while(i<ItemList.getCount()&&IColl[i].getOrderNo().equals(onum))
				Result.add(IColl[i++]);
		return Result;
	}
	
	public Product searchProducts(String partno){
		Product[] PColl=ProdList.getColl();
		for(int i=0;i<ProdList.getCount();i++)
			if(PColl[i].getProdNum().equals(partno))return PColl[i];
		return null;
	}
	
	public int searchOrders(String ordno){
		for(int i=0;i<count;i++)
			if(collection[i].getOrderNo().equals(ordno))return i;
		return -1;
	}
	
	public void deleteOrder(String ordno){
		int i=searchOrders(ordno);
		if(i>=0){
			for(int j=i+1;j<=count;j++){
				collection[j-1]=collection[j];
			}
		}
		count--;
	}
	
	public double toDouble(String doub){
		return Double.parseDouble(doub);
	}
	
	public double calcTotal(String orderNum){
		double total=0;
		Product prod=new Product("0","0","0");
		ItemCollection items=searchItems(orderNum);
		for(int i=0;i<items.getCount();i++){
			if((prod=searchProducts(items.getColl()[i].getPartNum()))!=null){
				double price=toDouble(prod.getPrice());
				double packs=toDouble(items.getColl()[i].getPacks());
				total+=price*packs;
			}
		}
		return total;
	}
	
	public void add(String productno,String descr, String price){
		if(count==collection.length)
			increaseSize();
		collection[count]=new Order(productno,descr,price);
		count++;
	}
	
	public void increaseSize(){
		Order[] temp =new Order[collection.length*2];
		for (int i=0;i<collection.length;i++)temp[i]=collection[i];
		collection = temp;
	}
	
	public Order[] getColl(){
		return collection;
	}
		
	public void post(String ordNum,OrderCollection OrdList, InvoiceCollection InvList){
		int index=OrdList.searchOrders(ordNum);
		Order Ord=OrdList.getColl()[index];
		InvList.add(InvList.getCount(),Ord);
		OrdList.deleteOrder(ordNum);
	}
	
	public String toString(){
		String allOrders="Orders List\n";
		for(int i=0;i<count;i++)allOrders+=collection[i].toString()+" "+this.calcTotal(collection[i].getOrderNo())+"\n";
		allOrders+=count+" Orders\n";
		return allOrders;
	}
	
	public void save(){
		PrintWriter data=new PrintWriter(System.out, true);
		try{
			data=new PrintWriter(new FileWriter("ordersystem_files/orders.dat"));
		}catch(IOException err){
			System.err.println("Error creating file");
			System.exit(1);
		}
		
		String allCust="";
		for(int i=0;i<count;i++)allCust+=collection[i].toString()+"\n";
		data.printf("%s",allCust);
		data.close();
	}
}

class Invoice{
	private String invoiceNo;
	private Order order;
	
	public Invoice(int invoiceNo, Order order){
		this.invoiceNo="INV"+String.valueOf(invoiceNo);
		this.order=order;
	}
	
	public String getInvoiceNo(){
		return invoiceNo;
	}
	
	public String getOrderNo(){
		return order.getOrderNo();
	}
	
	public String toString(){
		String ordDetails;
		ordDetails=invoiceNo+";"+order.toString();
		return ordDetails;
	}
}

class InvoiceCollection implements iFindnCalc{
	private Invoice[] collection;
	int count;
	
	ProductCollection ProdList=new ProductCollection();
	ItemCollection ItemList=new ItemCollection();
	
	public InvoiceCollection(){
		collection=new Invoice[100];
		count=0;
	}
	
	public void add(int invoiceNo,Order ord){
		if(count==collection.length)
			increaseSize();
		collection[count]=new Invoice(invoiceNo,ord);
		count++;
	}
	
	public void increaseSize(){
		Invoice[] temp =new Invoice[collection.length*2];
		for (int i=0;i<collection.length;i++)temp[i] = collection[i];
		collection = temp;
	}
	
	public Invoice[] getColl(){
		return collection;
	}
	
	public int getCount(){
		return this.count;
	}
		
	public ItemCollection searchItems(String onum){
		int i=0;
		ItemCollection Result=new ItemCollection(0);
		Item[] IColl=ItemList.getColl();
		for(i=0;i<ItemList.getCount();i++)
			if(IColl[i].getOrderNo().equals(onum)){
				Result.add(IColl[i++]);
				break;
			}
		while(i<ItemList.getCount()&&IColl[i].getOrderNo().equals(onum))
				Result.add(IColl[i++]);
		return Result;
	}
	
	public Product searchProducts(String partno){
		Product[] PColl=ProdList.getColl();
		for(int i=0;i<ProdList.getCount();i++)
			if(PColl[i].getProdNum().equals(partno))return PColl[i];
		return null;
	}
	
	public double toDouble(String doub){
		return Double.parseDouble(doub);
	}
	
	public double calcTotal(String orderNum){
		double total=0;
		Product prod=new Product("0","0","0");
		ItemCollection items=searchItems(orderNum);
		for(int i=0;i<items.getCount();i++){
			if((prod=searchProducts(items.getColl()[i].getPartNum()))!=null){
				double price=toDouble(prod.getPrice());
				double packs=toDouble(items.getColl()[i].getPacks());
				total+=price*packs;
			}
		}
		return total;
	}
	
	public String toString(){
		String allCust="Invoice List\n";
		for(int i=0;i<count;i++)allCust+=collection[i].toString()+" "+this.calcTotal(collection[i].getOrderNo())+"\n";
		allCust+=count+" Invoice\n";
		return allCust;
	}
	
	public void save(){
		PrintWriter data=new PrintWriter(System.out, true);
		try{
			data=new PrintWriter(new FileWriter("ordersystem_files/invoice.dat"));
		}catch(IOException err){
			System.err.println("Error creating file");
			System.exit(1);
		}
		
		String allCust="invoiceno;orderno;custno;routeno;totalprice\n";
		for(int i=0;i<count;i++)allCust+=collection[i].toString()+";"+this.calcTotal(collection[i].getOrderNo())+"\n";
		data.printf("%s",allCust);
		data.close();
	}
}

class Customer{
	private String custno,company,addr1;
	
	public Customer(String custno, String company,String addr1){
		this.custno=custno;
		this.company=company;
		this.addr1=addr1;
	}
	
	public String toString(){
		String custDetails;
		custDetails=custno+" "+company+" "+addr1;
		return custDetails;
	}
}

class CustomerCollection extends DataHolder{
	private Customer[] collection;
	
	public CustomerCollection(){
		collection=new Customer[100];
		count=0;
		this.load("customers.dat");
	}
	
	public void add(String custno,String comp, String addr1){
		if(count==collection.length)
			increaseSize();
		collection[count]=new Customer(custno,comp,addr1);
		count++;
	}
	
	public void increaseSize(){
		Customer[] temp =new Customer[collection.length*2];
		for (int i=0;i<collection.length;i++)temp[i] = collection[i];
		collection = temp;
	}
	
	public Customer[] getColl(){
		return collection;
	}
	
	public String toString(){
		String allCust="Customer List\n";
		for(int i=0;i<count;i++)allCust+=collection[i].toString()+"\n";
		allCust+=count+" Customers\n";
		return allCust;
	}
}

class Product{
	private String productno,descr,price;
	
	public Product(String productno, String descr,String price){
		this.productno=productno;
		this.descr=descr;
		this.price=price;
	}
	
	public String getProdNum(){
		return productno;
	}
	
	public String getPrice(){
		return price;
	}
	
	public String toString(){
		String prodDetails;
		prodDetails=productno+" "+descr+" "+price;
		return prodDetails;
	}
}

class ProductCollection extends DataHolder{
	private Product[] collection;
	
	public ProductCollection(){
		collection=new Product[100];
		count=0;
		load("products.dat");
	}
	
	public void add(String productno,String descr, String price){
		if(count==collection.length)
			increaseSize();
		collection[count]=new Product(productno,descr,price);
		count++;
	}
	
	public void increaseSize(){
		Product[] temp =new Product[collection.length*2];
		for (int i=0;i<collection.length;i++)temp[i] = collection[i];
		collection = temp;
	}
	
	public Product[] getColl(){
		return collection;
	}
	
	public String toString(){
		String allProd="Product List\n";
		for(int i=0;i<count;i++)allProd+=collection[i].toString()+"\n";
		allProd+=count+" Products\n";
		return allProd;
	}
}

class Item{
	private String orderno,partno,packs;
	
	public Item(String orderno, String partno,String packs){
		this.orderno=orderno;
		this.partno=partno;
		this.packs=packs;
	}
	
	public Item(Item newItem){
		this.orderno=newItem.orderno;
		this.partno=newItem.partno;
		this.packs=newItem.packs;
	}
	
	public String getOrderNo(){
		return orderno;
	}
	
	public String getPartNum(){
		return partno;
	}
	
	public String getPacks(){
		return packs;
	}
	
	public String toString(){
		String itemDetails;
		itemDetails=orderno+" "+partno+" "+packs;
		return itemDetails;
	}
}

class ItemCollection extends DataHolder{
	private Item[] collection;
	
	public ItemCollection(){
		collection=new Item[100];
		count=0;
		load("items.dat");
	}
	
	public ItemCollection(int x){
		collection=new Item[100];
		count=0;
	}
	
	public void add(String orderno,String partno, String packs){
		if(count==collection.length)
			increaseSize();
		collection[count]=new Item(orderno,partno,packs);
		count++;
	}
	
	public void add(Item newItem){
		if(count==collection.length)
			increaseSize();
		collection[count]=new Item(newItem);
		count++;
	}
	
	public void increaseSize(){
		Item[] temp =new Item[collection.length*2];
		for (int i=0;i<collection.length;i++)temp[i] = collection[i];
		collection = temp;
	}
	
	public Item[] getColl(){
		return collection;
	}
	
	public String toString(){
		String allItem="Items List\n";
		for(int i=0;i<count;i++)allItem+=collection[i].toString()+"\n";
		allItem+=count+" Items\n";
		return allItem;
	}
}
