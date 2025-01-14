package com.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.ProductController;

public class ProductView {
	
	public static void main(String[] args) throws SQLException, Exception {
		ProductController pc=new ProductController();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("_____________________________________________________________________________");
			System.out.println("PRODUCT MANAGEMENT SYSTEM>>>>");
			System.out.println("->");
			System.out.println("MENU");
			System.out.println("\t 1: Add Product \n"+"\t 2: View All Products \n"+"\t 3: Update a Product \n"+"\t 4: Delete a Product \n"+"\t 5:Exit \n");
			System.out.println("_______________________________________________________________________________");
			System.out.print("Enter your Choice: ");
			int choice=sc.nextInt();
		switch(choice) {
		
		case 1:
			 
			pc.AddProduct();
			break;
		case 2:
			
			pc.ViewProduct();
			break;
		case 3:
			
			pc.UpdateProduct();
			break;
		
		case 4:
			
			pc.DeleteProduct();
			break;
			
		case 5:
			System.exit(0);
			
		}
		}
	}

}
