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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		
		System.out.println(email+" "+pass);
		UserDAO dao=new UserDAO(DBConnect.getConn());
		User u=dao.loginUser(email,pass);
		HttpSession session=request.getSession();
		
		if(u!=null) {
			session.setAttribute("user",u);
			response.sendRedirect("index.jsp");
			//System.out.println("user is Avaiable"+u);
			
		}
		else {
			session.setAttribute("invalidMsg","Invalid Email & Password");
			response.sendRedirect("login.jsp");
			//System.out.println("user not Avaiable"+u);
		}
		
	}

}
