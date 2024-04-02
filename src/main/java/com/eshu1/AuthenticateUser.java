package com.eshu1;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticateUser extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)
	{
		res.setContentType("text/html");
		try
		{
			//extract values and save in variables
		
		String email =	req.getParameter("email");
		String password =	req.getParameter("pwd");
			
		//database code to store in users table 
		//step-1 : Register the driver
        Class.forName("com.mysql.jdbc.Driver");
		
        //step-2 : get the connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","root");
	
		//step-3 : create the statement object
//Actually users-table have 6 coloumns but id-column is auto-increment.so we noneed to mention that coloumn.DB will take care of that
		
		String query = " select * from users where email=?";
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setString(1, email);
	
		PrintWriter out = res.getWriter();
		
		//step-4 : execute the queries
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next())
		{
			//compare DB password with the password entered by the user
			//here w can give comparing coloumn number Ex:6 or comparing column-name
			String dbPwd = rs.getString("password");
			
			if(password.equals(dbPwd))
			{
				//forward the user to the product page
				res.sendRedirect("./Product.html");
			}
			else
				out.println("<h1>User Credentials not correct</h1>");
		}
		
		    else
			out.println("<h1>User does not exist</h1>");
			

		//step-5 : close the connection
		stmt.close();
		con.close();
		
		

		}
		
		catch(Exception e)
		{
		System.out.println(e);	
		}
	}

	

}
