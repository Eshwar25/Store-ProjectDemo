package com.eshu1;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

public class RegisterUser extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		res.setContentType("text/html");
		try
		{
			//extract values and save in variables
		String name = req.getParameter("name");
		String contact =	req.getParameter("contact");
		String email =	req.getParameter("email");
		String address =	req.getParameter("addr");
		String password =	req.getParameter("pwd");
			
		//database code to store in users table 
		//step-1 : Register the driver
        Class.forName("com.mysql.jdbc.Driver");
		
        //step-2 : get the connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","root");
	
		//step-3 : create the statement object
//Actually users-table have 6 coloumns but id-column is auto-increment.so we noneed to mention that coloumn.DB will take care of that
		
		String query = "insert into users(name,contact,email,address,password) values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setString(1, name);
		stmt.setString(2, contact);
		stmt.setString(3, email);
		stmt.setString(4, address);
		stmt.setString(5, password);
		
		
		//step-4 : execute the queries
		stmt.executeUpdate();

		//step-5 : close the connection
		stmt.close();
		con.close();
		
		//display response
		PrintWriter out = res.getWriter();
		out.println("User-Registered Successfully");
		out.println("<h1><a href=\"./LoginForm.html\">Login Now</a></h1>");
		}
		
		catch(Exception e)
		{
		System.out.println(e);	
		}
	}

}
