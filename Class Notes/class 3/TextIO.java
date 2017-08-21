/*
 *All input and output streams should be closed. Especially important for outputting to a file, if it is not closed the data is not committed to the file
 **/

import java.io.*;
import java.util.*;

public class TextIO{
	public static void main(String []args){
		PrintWriter out;
		Scanner in;
		String line, word;
		int ival;
		double dval;
		
		//read from file
		in = null;
		try {in = new Scanner (new FileReader("INFILE.TXT"));}
		catch (IOException err) {
			System.err.println("Cannot open INFILE.TXT");
			System.exit(1);
		}
		
		//output to console or system
		out = new PrintWriter(System.out,true);
		try{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("textfile.txt", true)));
			pw.printf("*");
			pw.close();
			System.out.printf("Success");
		}catch(IOException err){
			System.out.printf("Error");
		}
 		
 		
		line = in.nextLine();
		word = in.next();
		ival = in.nextInt();
		dval = in.nextDouble();

		out.printf("Line: %s%n",line);
		out.printf("Word: %s	Int:%d	Double: %f%n",word,ival,dval);
		in.close();
		//out.close();
		/*
		//create a print writier to write to a disk file
		PrintWriter out2=null;
		
		try{out2=new PrintWriter(new FileWriter("REPORT.TXT"),false);}
		catch(IOException err){
			System.err.println("Canot create REPORT.TXt.");
			System.exit(1);
		}

		out2.printf("Line: %s%n",line);
		out2.printf("Word: %s	Int:%d	Double: %f%n",word,ival,dval);
		//out.printf("*");
		System.out.print("*");
		*/
		
		//read from CSV file
		System.out.print("*\n");
		//out.printf("*");
		in = null;
		try {in = new Scanner (new FileReader("INFILE.csv"));}
		catch (IOException err) {
			System.err.println("Cannot open INFILE.TXT");
			System.exit(1);
		}
		in.useDelimiter(",");
		while(in.hasNext()){
            System.out.print(in.next()+"|");
        }
		in.close();
	}
}

/*WAYS TO DECLARE AN OBJECT
 *car c=new car();
 *
 *or
 *
 *c=null;
 *c=new car();
 **/