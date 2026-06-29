package com.jdbclearning.jdbclearning;
import java.util.Scanner;
import java.sql.*;


public class Transaction {
   public static void main(String[] args) throws Exception {
	   Scanner sc= new Scanner(System.in);
	   String url="jdbc:mysql://localhost:3306/bank";
	   String root="root";
	   String pass= "******";
	   
	   Connection con= DriverManager.getConnection(url,root,pass);
	   PreparedStatement ps=null;
	   PreparedStatement cs=null;
	   con.setAutoCommit(false);
	   try {
		   System.out.println("Enter the Sender account Number : ");
		   int num1= sc.nextInt();
		   System.out.println("Enter the Receivers Account Number : ");
		   int num2 = sc.nextInt();
		   System.out.println("Enter the amount to be tranfered :");
		   int amt= sc.nextInt();
		   ps= con.prepareStatement("update account set balance= balance-? where acc_no=?");
		   ps.setInt(1, amt);
		   ps.setInt(2, num1);
		  
		   int rows = ps.executeUpdate();

		   if(rows == 0){
		       throw new Exception("Sender account not found");
		   }
		   cs= con.prepareStatement("update account set balance= balance+? where acc_no=?");
		   cs.setInt(1, amt);
		   cs.setInt(2, num2);
		
		  int row = cs.executeUpdate();

		   if(row == 0){
		       throw new Exception("Receivers account not found");
		   }
		   con.commit();
		   System.out.println("Transaction Successful");
	   }
	   catch(Exception e) {
		   con.rollback();
		   System.out.println("Transaction Failed");
		   System.out.println(e.getMessage());
	   }
	   finally {
		   if(ps!=null) {
		   ps.close();
		   }
		   con.close();
	   }
   }
}
