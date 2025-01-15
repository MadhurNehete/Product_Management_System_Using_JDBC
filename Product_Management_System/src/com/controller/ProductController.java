package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.model.Product;

public class ProductController {
	
	Scanner sc=new Scanner(System.in);
	public static Connection connection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/product_management","root","root");
		return con;
	}

	
	
	
	
	public void AddProduct() throws SQLException, Exception {
		//object creation of POJO Class
		Product p =new Product();
		
		//Setting values to the object
		System.out.println("Enter name , price, quantity, Category:-");
	//	p.setId(sc.nextInt());
		p.setName(sc.next());
		p.setPrice(sc.nextDouble());
		p.setQty(sc.nextInt());
		p.setCategory(sc.next());
	
		//getting values from the object
	//	int id=p.getId();
		String name=p.getName();
		double price=p.getPrice();
		int quantity=p.getQty();
		String category=p.getCategory();
		
		
	
		ProductController.connection();

		Statement stm=connection().createStatement();
		String getMaxIdQuery = "select MAX(id) FROM products"; //query to get max id
        ResultSet rs=stm.executeQuery(getMaxIdQuery);
        
        int nextId =1;// default id is 1 when table is empty
        if(rs.next()) {
        	nextId = rs.getInt(1)+1;
        }
    	//getting connection
		//String query="insert into products(id,name,price,quantity,category) values('"+id+"','"+name+"','"+price+"','"+quantity+"','"+category+"')";
		String query="insert into products(id,name,price,quantity,category) values('"+nextId+"','"+name+"','"+price+"','"+quantity+"','"+category+"')";
		
		stm.execute(query);
		stm.close();
		connection().close();
		
		
		System.out.println("Data Added Succesfully");
		
		}
	
	public void ViewProduct() throws SQLException, Exception {
		Product p =new Product();

        String query="select * from products";
		
		Statement stm=connection().createStatement();
		
		//fetching data from database
		ResultSet rs=stm.executeQuery(query);
		System.out.println("");	
		System.out.println("All Available products>>>>>>");
		System.out.println("");
		while(rs.next()) {
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getInt(4)+" "+rs.getString(5));
		}
		rs.close();
		stm.close();
		connection().close();
		
		
		
		
	}
	
	public void UpdateProduct() throws SQLException, Exception{
		ProductController.connection();
		System.out.println(" ");
		ViewProduct();
		System.out.println(" ");
		System.out.println("_______________________________________");
		Product p =new Product();
      	 String query="Select * from products ";

		Statement stm=connection().createStatement();
         System.out.println("Enter ID: ");
         int id=sc.nextInt();
         ResultSet rs=stm.executeQuery(query);
		 boolean idExists=false;
		 boolean b=true;
         while(rs.next()) {
        	 if(rs.getInt(1)==id) {
        		idExists=true;
        	    b=true;
            	 while(b) {
            		 System.out.println("______________________________________________________________________");
            		 System.out.println("\t 1: Update Name \n "+"\t 2: Update Price \n"+"\t 3: Update Quantity \n"+"\t 4: Update Category \n"+"\t 5:Exit \n");
            		 System.out.println("______________________________________________________________________");
            		 System.out.println("Select option You Want to upadate>>>> ");
            		 int select=sc.nextInt();
            		 
            		// boolean b = true;
            	switch(select) {
            	
            	 case 1:
            		 System.out.println("Enter new Product Name :");
            		 String name=sc.next();
            		 		
            		 //updating name 
            		 String query1 = "UPDATE products SET name = '" + name + "' WHERE id = '" + id + "'";            
            			stm.execute(query1);
            			
            		 System.out.println("Name Updated Succesfully!!!");
            		 break;
            		 
            	 case 2:
            		 System.out.println("Enter new price:");
            		 double price=sc.nextDouble();
            		 
            		 //updating price
            		 String query2 = "UPDATE products SET price = '" + price + "' WHERE id = '" + id + "'";            
            			stm.execute(query2);
            			
            		 System.out.println("Price Updated Succesfully!!!");
            		 break;
            		 
            	 case 3:
                	 System.out.println("Enter Updated Quantity :");
                	 int qty=sc.nextInt();
                	 
            		 //updating quantity
                	 String query3 = "UPDATE products SET Quantity = '" + qty + "' WHERE id = '" + id + "'";            
            			stm.execute(query3);
            			
                	 System.out.println("Quantity Updated Succesfully!!!");
                	 break;
                	 
                 case 4: 
                     System.out.println("Enter new Category :");
                     String category=sc.next();
                     
            		 //updating category
                     String query4 = "UPDATE products SET category = '" + category + "' WHERE id = '" + id + "'";            
            			stm.execute(query4);
            			
                     System.out.println("Category Updated Succesfully!!!");
                     break;
                     
                 case 5:
                	 b = false;
                	 System.out.println("Update menu closed successfully!!!");
                	 break;
                    		 
                    			
            	
            	}	 
            	 }
            	 stm.close();
     			connection().close();
            	 break;
        		 }
        	 
        		 
         }
         if(!idExists) {
        	 System.out.println("ID DOES NOT EXIST!!!!");
         }
         
        
		
	  
		
	}
	
	          public void DeleteProduct() throws SQLException, Exception{
	      		ProductController.connection();
      			Statement stm=connection().createStatement();

	         	
      			 // Take the product ID input from the user
      		    Scanner sc = new Scanner(System.in);
      		    System.out.println("Enter Product ID to delete: ");
      		    int id = sc.nextInt();

      		    // Construct the DELETE SQL query
      		    String query = "DELETE FROM products WHERE id = " + id;

      		    // Execute the DELETE query
      		    int rowsAffected = stm.executeUpdate(query);

      		    // Check if the product was deleted successfully
      		    if (rowsAffected > 0) {
      		        System.out.println("Product with ID " + id + "  deleted successfully.");
      		    } else {
      		        System.out.println("No product present with ID " + id );
      		    }

      		    // Close resources
      		    stm.close();
      		    connection().close();


	          }
}
