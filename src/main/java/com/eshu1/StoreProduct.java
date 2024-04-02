package com.eshu1;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreProduct extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		//response can be take html-tags also & text also
//setContenetType - usage - to understand html-tags ,html tag type output we will get
		res.setContentType("text/html");
		
		try
		{
//1st-step Extract & Save Form inputs into store in Variable
			String prodid = req.getParameter("pid");
			String prodname = req.getParameter("pname");
			String prodcat = req.getParameter("cat");
            //Conversion of String-value to Integer
			int prodqty = Integer.parseInt(req.getParameter("qty"));
			
//2nd- implement DB-Connectivity
			
			//step-1 : Register the driver
            Class.forName("com.mysql.jdbc.Driver");
			
            //step-2 : get the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","root");
		
			//step-3 : create the statement object
			String query = "insert into product values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, prodid);
			stmt.setString(2, prodname);
			stmt.setString(3, prodcat);
			stmt.setInt(4, prodqty);
			
			//step-4 : execute the queries
			stmt.executeUpdate();

			//step-5 : close the connection
			stmt.close();
			con.close();
			
//3rd - Send Success response to user
			PrintWriter out = res.getWriter();
			out.println("Product Id : "+prodid);
			out.println("</br>Product Name : "+prodname);
			out.println("</br>Product Category : "+prodcat);
			out.println("</br>Product Quantity : "+prodqty);
			
			out.println("</br><h1>Product inserted Successfully!!!</h1>");

			//we creating a hyper-link to go the main-page
			out.println("</br><a href=\"./Product.html\">Add-Another-Product</a>");
			
	
		}
		catch(Exception e) {System.out.println(e);}
	}

}
