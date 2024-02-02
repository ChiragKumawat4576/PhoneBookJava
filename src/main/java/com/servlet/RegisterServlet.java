package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.UserDAO;
import com.entity.User;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String name=request.getParameter("name");
	    String email=request.getParameter("email");
	    String password=request.getParameter("password");
	    
	    User u=new User(name,email,password);
	    
	    UserDAO dao=new UserDAO(DBConnect.getConn());
	    boolean f=dao.userRegister(u);
	    
	    HttpSession session=request.getSession();
	    if(f){
	        session.setAttribute("sucssMsg","user Register successfully");
	        response.sendRedirect("register.jsp");
	       // System.out.println("user Register successfully");
	    }
	    else{
	             session.setAttribute("errorMsg","Something wrong server");
	              response.sendRedirect("register.jsp");
	           // System.out.println("Something wrong server");
	    }
	}

}
