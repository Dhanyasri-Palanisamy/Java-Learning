package com.jdbclearning.jdbclearning;
import java.sql.*;
import java.util.*;
public class PreparedSatatementLearning {
   public static void main(String[] args) throws Exception{
	   Scanner sc= new Scanner(System.in);
	   System.out.println("------------------------------------welcome to Dhanya Sri's JDBC Project-----------------------------");
	   System.out.println("Press any key and Enter to continue ..... to the exciting project with java and sql");
	   char just= sc.next().charAt(0);
	   
	   System.out.println("In Any DataBase the basic thing the beginner must learn is to do CRUD operation ");
	   just= sc.next().charAt(0);
	   System.out.println("Where......");
	   System.out.println("C- Create, R-Read ,U- update and D- Delete");
	   just= sc.next().charAt(0);
	   System.out.println("Here I have create a Sample Database Named Practice now you are going to perform the CRUD operations in it ");
	   just= sc.next().charAt(0);
	   System.out.println("Now press the following keys to perform the CRUD operation 'r' to read the Database");
	   System.out.println(" 'i' to insert values into the Database");
	   System.out.println(" 'u' to update the Database");
	   System.out.println(" 'd' to delete the record in the Database");
	   System.out.println(" 'e' to exit the program ");
	   just= sc.next().charAt(0);
	   while(just!='e') {
		   if(just=='i') {
			   insertRecord(sc);
		   }
		   else if(just == 'r') {
			   System.out.println("Press v to view the full record ");
			   System.out.println("Press s to view the specific record ");
			    just= sc.next().charAt(0);
			    if(just=='v') {
			    	viewRecords();
			    	
			    }
			    else {
			    	viewParticularRecords(sc);
			    }
			   
		   }
		   else if(just == 'u') {
			   updateRecord(sc);
		   }
		   
		   else if(just == 'd') {
			   deleteRecord(sc);
		   }
		   else {
			   System.out.println("Invalid Input");
		   }
		  System.out.println("What do you want to do next?");
		  just= sc.next().charAt(0);
	   }
	  
	   
	   
	   
	   
  }
   
   
    public static void insertRecord(Scanner sc) throws Exception{
    	 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","*****");
    	 PreparedStatement  ps= con.prepareStatement("Insert into emp_details(emp_name,emp_desc,hire_date,salary,mail,aadhar) values(?,?,?,?,?,?)");
  	     System.out.println("Enter the number of datas going to be added :");
  	      int n= sc.nextInt();
  	      
  	      
  	   int j=0;
  	   
  	   
  	   while(j<n) {
  		   
  		   System.out.println("Enter the name :");
  		   String st= sc.next();
  		   ps.setString(1,st);
  		   
  		   System.out.println("Enter the designation: ");
  		    st= sc.next();
  		    ps.setString(2,st);
  		    
  		 
  		   System.out.println("Enter the hire_date: ");
  		   st= sc.next();
  		   ps.setDate(3,java.sql.Date.valueOf(st));
  		   
  		   
  		    System.out.println("Enter the Salary: ");
  		     double sal= sc.nextDouble();
  		     ps.setDouble(4,sal);
  		     
  		     System.out.println("Enter the Mail : ");
  		     String Mail= sc.next();
  		     ps.setString(5,Mail);
  		     
  		     System.out.println("Enter the Aadhar : ");
  		     int aadhar= sc.nextInt();
  		     ps.setInt(6,aadhar);
  		     
  		    int rows = ps.executeUpdate();
  		    if(rows>0) {
  		    	System.out.println("Datas updated");
  		    }
  		    j++;
  	   }
  	   //rs.close();
  	   ps.close();
  	   con.close();
  	   
    	
    }
    
    public static void viewRecords() throws Exception {
    	 {
       	  Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","*****");
       	  Statement st= con.createStatement();
       	  ResultSet rs= st.executeQuery("Select * from emp_details");
       	  while(rs.next()) {
       		  System.out.println(rs.getInt("emp_id"));
       		  System.out.print(" 	"+rs.getString("emp_name"));
       		  System.out.print(" 	"+rs.getString("emp_desc"));
       		  System.out.print(" 	"+rs.getDate("hire_date"));
       		  System.out.print(" 	"+rs.getInt("salary"));
       		  System.out.print(" 	"+rs.getString("Aadhar"));
       	  }
       	  rs.close();
       	  st.close();
       	  con.close();
       	  
         }
    }
    
    public static  void viewParticularRecords(Scanner sc) throws Exception
    {
       
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","********");    	 
  	    PreparedStatement ps= con.prepareStatement("select * from emp_details where emp_id=?");
  	    System.out.println("Enter the id you need to search : ");
  	    int i=sc.nextInt();
  	   
  	   ps.setInt(1,i);
  	   
  	   
  	   ResultSet rs= ps.executeQuery();
  	   if(rs.next()) {
  		 System.out.println(rs.getInt("emp_id"));
  		  System.out.print(" 	"+rs.getString("emp_name"));
  		  System.out.print(" 	"+rs.getString("emp_desc"));
  		  System.out.print(" 	"+rs.getDate("hire_date"));
  		  System.out.print(" 	"+rs.getInt("salary"));
  		  
  		  System.out.print(" 	"+rs.getString("Aadhar"));
  		 
  	   }
  	   ps.close();
  	   rs.close();
  	   con.close();
  	   }
    
    public static void updateRecord(Scanner sc) throws Exception {
    	
    	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","********");
    	
    	System.out.println("Enter which record you are going to change \n a.emp_name \n b.emp_desc \n c.hire_date \n d.salary \n e. mail \n f. Aadhar");
    	char input= sc.next().charAt(0);
    	PreparedStatement ps;
    	if(input=='a') {
    		ps=con.prepareStatement("update emp_details set emp_name= ? where emp_id=?");
    		System.out.println("Enter the id of the employee");
    		int id= sc.nextInt();
    		System.out.println("Enter the name to change : ");
    		String name= sc.next();
    		ps.setString(1, name);
    		ps.setInt(2, id);
    		int rows=ps.executeUpdate();
    			if(rows!=0) {
    				System.out.println("updated Successfully");
    			}
    		}
    		
    		
    	
    	else if(input=='b') {
    		ps=con.prepareStatement("update emp_details set emp_desc= ? where emp_id=?");
    		System.out.println("Enter the id of the employee");
    		int id= sc.nextInt();
    		System.out.println("Enter the desecnation to change : ");
    		String name= sc.next();
    		ps.setString(1, name);
    		ps.setInt(2, id);
    		int rows=ps.executeUpdate();
			if(rows!=0) {
				System.out.println("updated Successfully");
			}
    	}
    	else if(input=='c') {
    		ps=con.prepareStatement("update emp_details set hire_date = ? where emp_id=?");
    		System.out.println("Enter the id of the employee");
    		int id= sc.nextInt();
    		System.out.println("Enter the date to change : ");
    		String date= sc.next();
    		ps.setDate(1, java.sql.Date.valueOf(date));
    		ps.setInt(2, id);
    		int rows=ps.executeUpdate();
			if(rows!=0) {
				System.out.println("updated Successfully");
    		
    	        }
    	}
    	else if(input=='d') {
    		ps=con.prepareStatement("update emp_details set salary = ? where emp_id=?");
    		System.out.println("Enter the id of the employee");
    		int id= sc.nextInt();
    		System.out.println("Enter the Salary to change : ");
    		double date= sc.nextDouble();
    		ps.setDouble(1, date);
    		ps.setInt(2, id);
    		int rows=ps.executeUpdate();
			if(rows!=0) {
				System.out.println("updated Successfully");
    		
    	        }
    	}
    	else if(input=='e'){
    		ps=con.prepareStatement("update emp_details set mail = ? where emp_id=?");
    		System.out.println("Enter the id of the employee");
    		int id= sc.nextInt();
    		System.out.println("Enter the mail to change : ");
    		String date= sc.next();
    		ps.setString(1, date);
    		ps.setInt(2, id);
    		int rows=ps.executeUpdate();
			if(rows!=0) {
				System.out.println("updated Successfully");
    		
    	        }
    	}
    	else if(input=='f') {
    		ps=con.prepareStatement("update emp_details set Aadhar=? where emp_id=?");
    		System.out.println("Enter the id of the employee");
    		int id= sc.nextInt();
    		System.out.println("Enter the Aadhar number:" );
    		String aad= sc.next();
    		ps.setString(1, aad);
    		ps.setInt(2, id);
    		int rows= ps.executeUpdate();
    	    if(rows!=0) {
    	    	System.out.println("Updated Successfully!!");
    	    }
    	}
    	else {
    		System.out.println("Invalid Input");
    	}
    	
    }
   public static void deleteRecord(Scanner sc) throws Exception {
	   
	   Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","******");
	   System.out.println("Enter the id you need to delete");
	    int id= sc.nextInt();
	    
	    PreparedStatement ps= con. prepareStatement("Delete From emp_details where emp_id=?");
	    ps.setInt(1, id);
	    int rows=ps.executeUpdate();
	    if(rows!=0) {
	    	System.out.println("Deleted Successfully");
	    }
	    ps.close();

	    con.close();
	    
   }
}
