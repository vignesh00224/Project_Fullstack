package Proj1;

import java.util.*;
/*
 * Created by  : vignesh
 * Create Date : 23/09/2026
 * purpose 	   : Students detail add,update,delete,search and view. 
 * Object used :Studentobj
 */

public class Studentclass {

	static Scanner sc = new Scanner(System.in);
	static LinkedList<Studentobj> st = new LinkedList<>();
	
	public static void main(String[] args) {

		
		int options;
		
		do {
		
			    System.out.println("==Main Menu==");
			    System.out.println("1. Add Student");
	            System.out.println("2. View Students");
	            System.out.println("3. Search Student");
	            System.out.println("4. Update Student");
	            System.out.println("5. Delete Student");
	            System.out.println("6. Exit");
	            System.out.println("Please Choose the options : ");
			 options=sc.nextInt();
		
	try {
		switch(options) {
		case 1:addst();
               break;
		case 2:ViewSt();
        	   break;
		case 3:Searchst();
               break;
		case 4:updatest();
               break;
		case 5:deletest();
               break;
		case 6:System.out.println("Application is closed thank you");
        	  break;       
		default:
    		  System.out.println("Invalide option");       
		}
		
	  }catch(Exception e) {
			System.out.println("Error while Enter details please try agin");
			 sc.nextLine();
		  };
		  
	}while (options!=6);
	 
	}
	
	//add student details 
	static void addst() {
		System.out.println("==Student add Menu==");
		System.out.println("Enter Student Id.No");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Name : ");
		String name=sc.nextLine();
		System.out.println("Enter age : ");
		int  age=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter City : ");
		String city=sc.nextLine();
		
		
		Studentobj student = new Studentobj (id, name, age, city);
        st.add(student);
        
        System.out.println("Student added successfully");
		
	}
    static void ViewSt() {
    	System.out.println("==All students list Menu==");
    	if (st.isEmpty()) {
    	 System.out.println("There is no records.");
    	 return;
    	}
    	//System.out.println(st);
    	for(Studentobj vst: st) {
    		System.out.println(vst);
    	}
    	
	}
    
    //Search student details
    static void Searchst() {
    	System.out.println("==Search Menu==");
    	boolean f = false;
    	
    	System.out.println("1.id");
    	System.out.println("2.age");
    	System.out.println("3.name");
    	System.out.println("3.city");
    	System.out.println("Enter the search by option ");
    	int i;
    	int cnt;
    do {	
    	i=sc.nextInt();
    	sc.nextLine();
    	cnt=0;
      switch (i) {
      case 1:
    	  System.out.println("Enter the ID : ");
    	  int id=sc.nextInt();
    	  for (Studentobj sts :st) {
    		  if(sts.getid()== id) {
    			  System.out.println(sts);
    			  f = true;
    		  }
    	  }
    	  break;
      case 2:
      	  System.out.println("Enter the age : ");
      	  int age=sc.nextInt();
      	  for (Studentobj sts :st) {
      		  if(sts.getage()== age) {
      			  System.out.println(sts);
      			  f = true;
      		  }
      	  }
      	  break;
      case 3:
      	  System.out.println("Enter the name : ");
      	  String name=sc.nextLine();
      	  for (Studentobj sts :st) {
      		  if(sts.getname().equals(name)) {
      			  System.out.println(sts);
      			  f = true;
      		  }
      	  }
      	  break;
      case 4:
      	  System.out.println("Enter the city : ");
      	  String city=sc.nextLine();
      	  for (Studentobj sts :st) {
      		  if(sts.getcity().equals(city)) {
      			  System.out.println(sts);
      			  f = true;
      		  }
      	  }
      	  break;
      	  default:
      		  System.out.println("Please Enter the correct option");
      		  cnt=1;
      }
    
    } while(cnt == 1);
    
      if (!f) {
  		System.out.println("No data");
  		}
		
	}
    
    //update student details
    static void updatest() {
    	System.out.println("==Details update Menu==");
    	System.out.println("Enter the student id :");
    	int id=sc.nextInt();
    	sc.nextLine();
    	boolean f=false;

    	Studentobj up = st.stream()
    	        .filter(student -> student.getid() == id)
    	        .findFirst()
    	        .orElse(null);
    	
    	 System.out.println("Enter the updated name");
    	    String upname = sc.nextLine();

    	    System.out.println("Enter the updated city");
    	    String upcity = sc.nextLine();

    	    System.out.println("Enter the updated age");
    	    int upage = sc.nextInt();
    	    sc.nextLine();
    	    
    	up.updname(upname);
		up.updname(upcity);
		up.updname(upage);
		f=true;
    	
    	if(!f) {
    		System.out.println("No record found for thids id - "+id);
    	}else {
    		System.out.println("Update Successfully");
    	}
    		
	}
    static void deletest() {
    	System.out.println("==delete Menu==");
    	System.out.println("Enter the student id :");
    	int id=sc.nextInt();
    	sc.nextLine();
    	boolean f=false;
    	for (Studentobj up:st)
    	{
    		if(up.getid()==id) {
    			st.remove(up);
    			f=true;
    		}
    	}
    	if(!f) {
    		System.out.println("No record found for thids id - "+id);
    	}else {
    		System.out.println("deleted Successfully");
    	}
    	
    	
	}
}
