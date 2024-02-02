package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.ContactDAO;
import com.entity.Contact;


@WebServlet("/update")
public class EditContact extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
         int cid=Integer.parseInt(request.getParameter("cid"));
		 String name=request.getParameter("name");
		 String email=request.getParameter("email");
		 String phno=request.getParameter("phno");
		 String about=request.getParameter("about");
		 
		 Contact c=new Contact();
		 c.setId(cid);
		 c.setName(name);
		 c.setEmail(email);
		 c.setPhno(phno);
		 c.setAbout(about);
		 
		 ContactDAO dao=new ContactDAO(DBConnect.getConn());
		 HttpSession session=request.getSession(); 
		 boolean f=dao.UpdateContact(c);
		 if(f) {
			   session.setAttribute("succMsg","your Contact Updated...");
			   response.sendRedirect("viewcontact.jsp");
		 }
		 else {
			 session.setAttribute("failedMsg","Something wrong on Server...");
   		     response.sendRedirect("editcontact.jsp?cid="+cid);
		 }
		 

	}

}
