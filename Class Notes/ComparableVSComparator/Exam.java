import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

class Employee implements Comparable<Employee> {
  private String name;
  private double salary;
  private String department;
  private final double TAXRATE = 25;
  private final double NISRATE = 5;
  

  Employee(String name, double salary, String department) {
    this.name = name;
    this.salary = salary;
    this.department = department;	
  }

  public String getName() {
    return name;
  }

  public String getDepartment() {
    return department;
  }

  public double getSalary() {
    return salary;
  }

  public double getTax() {
    return TAXRATE * getSalary() * 0.01;
  }
  
  public double getNIS() {
    return NISRATE * getSalary() * 0.01;
  }

  public double getNET() {
    return getSalary() - getTax() - getNIS();
  }
  
  public String toString() {
    return "Name = " + getName() + ", Salary = " + getSalary();
  }

  public int compareTo(Employee e) {
    return name.compareTo(e.getName());
  }
  
}



class SalaryComparator implements Comparator<Employee>{
    public int compare(Employee e1, Employee e2) {
     
      return (int) (e1.getSalary() - e2.getSalary()) * -1;
    }
}
 
class DepartmentComparator implements Comparator<Employee>{
    public int compare(Employee e1, Employee e2) {
       return (e1.getDepartment()).compareTo(e2.getDepartment());
	}
}

  
class Exam {
  public static void main(String[] args) {
		String[] names = { "Adam", "Betty", "Charles", "David" };
		double[] salaries = { 2000.40, 578.00, 600.0, 489.0 };
		String[] departments = {"DCIT", "DCIT", "MATHS", "MATHS" };
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < names.length; i++)
      		employees.add(new Employee(names[i], salaries[i], departments[i]));
      		
    	Collections.sort(employees, new SalaryComparator());
        printEmployees(employees, "Employee Payroll Sorted By Salary");
        
    	Collections.sort(employees);
        printEmployees(employees, "Employee Payroll Sorted By Name");    
        	
    	Collections.sort(employees, new DepartmentComparator());
        printEmployees(employees, "Employee Payroll Sorted By Department");

   }
   
   static void printEmployees(List<Employee> employees, String Title){
    ListIterator<Employee> liter = employees.listIterator();
	
	
    System.out.println("\n");
    System.out.println("\t\t" + Title);	
    System.out.println("\t===============================================");
	System.out.format("%-15s %-15s %-10s %-10s %-10s %-10s\n","Name","Department","Salary","Tax","NIS","NET");
	 
    while (liter.hasNext()) {
        Employee value = liter.next();
		//System.out.println(value.getName());
		System.out.format("%-15s %-15s %-10.2f %-10.2f %-6.2f %-6.2f\n",value.getName(),value.getDepartment(),value.getSalary(),value.getTax(),value.getNIS(),value.getNET());
 
 		 
	}   
   }
   
   
}