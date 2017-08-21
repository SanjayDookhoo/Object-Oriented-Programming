/* Name : Sachin Rajkumar
   ID: 814001729
   AssignmentNo: 2
   Course: COMP 2500
 */

import java.util.Scanner; 
import java.io.*;
import java.lang.*;

public class assignment2 {  
    
    
    public static void main(String[] args){
        
        int oNo,cNo,packs,qty;
        String route;
        int num=0;
        int num1=0;
        String num2;
        int check=0;
        Scanner in = new Scanner(System.in);
        
        // intialise bakery 
        ZBakery bakery = new ZBakery();
    
        
        while (num!=6) {
            
            System.out.println();
            System.out.println();
            System.out.println("MENU:");
            System.out.println("1.Read files");
            System.out.println("2.Post an order");
            System.out.println("3.Add an order");
            System.out.println("4.Print an order");
            System.out.println("5.Print an Invoice");
            System.out.println("6.Exit");
            System.out.print("Enter respective number for the above options: ");
            num = in.nextInt();
            System.out.println();
            System.out.println();
            
            if (num==1){
                bakery.readCustomerFile();
                bakery.readProductFile();
                bakery.readOrderFile();
                bakery.readOrderItemFile();
                check = 1;
                System.out.println("Files read!!!");
                
            }
            else
                if(num==2){
                    if (check==0){
                        System.out.println("Need to read files first");
                    }
                    else{
                        System.out.print("Enter Order Number to be posted: ");
                        num1 = in.nextInt();
                        System.out.println();
                        bakery.postOrder(num1);
                        System.out.println("Invoice has been created and stored");
                        System.out.println();
                    }
                }
            else
                if(num==3){
                    if (check==0){
                        System.out.println("Need to read files first");
                    }
                    else{
                        System.out.print("Enter new Order Number: ");
                        oNo=in.nextInt();
                        System.out.println();
                        
                        System.out.print("Enter existing Customer Number: ");
                        cNo=in.nextInt();
                        System.out.println();
                        
                        System.out.print("Enter Route Number: ");
                        route=in.next();
                        System.out.println();
                        
                        System.out.print("Enter number of Products to be ordered: ");
                        num1 = in.nextInt();
                        System.out.println();
                        
                        Product[] p = new Product[20];
                        
                        for (int i=0; i<num1; i++){
                            System.out.print("Enter existing Product Number: ");
                            num2 = in.next();
                            System.out.println();
                            
                            System.out.print("Enter number of packs to be ordered: ");
                            packs = in.nextInt();
                            System.out.println();
                            
                            System.out.print("Enter qty to be ordered: ");
                            qty = in.nextInt();
                            System.out.println();
                            
                            Product temp1 = bakery.searchPArr(num2);
                            Product temp2 = new Product(temp1.getProductNo(),temp1.getDescription(),temp1.getListPrice());
                            temp2.setPacks(packs);
                            temp2.setQty(qty);
                            p[i]=temp2;
                        }
                        
                     
                    
                        
                        bakery.addOrder(oNo,cNo,route,p,num1);
                        
                    }
                }
            else 
                if(num==4){
                    if (check==0){
                        System.out.println("Need to read files first");
                    }
                    else{
                        System.out.print("Enter Order Number: ");
                        num1 = in.nextInt();
                        System.out.println();
                        bakery.printOrder(num1);
                        
                    }
                    
                }
            else
                if(num==5){
                    if (check==0){
                        System.out.println("Need to read files first");
                    }
                    else{
                        System.out.print("Enter Order Number for respective Invoice: ");
                        num1 = in.nextInt();
                        System.out.println();
                        bakery.printInvoice(num1);
                    }
                    
                }
            else
                if (num==6){
                    System.out.println("GoodBye");
                }
            else
                System.out.println("Invalid Entry");
            
            
            
            
        }
      
     
    }
    
           
        
        
}


