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

@WebServlet("/delete")
public class DeleteContact extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		
		ContactDAO dao=new ContactDAO(DBConnect.getConn());
		boolean f=dao.deleteContactById(cid);
		HttpSession session=request.getSession();
		if(f) {
			session.setAttribute("succMsg","contact Delete Succssfully...");
			response.sendRedirect("viewcontact.jsp");
		}
		else {
			session.setAttribute("failedMsg","Something wrong server....");
			response.sendRedirect("viewcontact.jsp");
		}
		
	}

}
