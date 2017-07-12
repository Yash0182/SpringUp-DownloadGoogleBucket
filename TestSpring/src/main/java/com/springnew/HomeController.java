package com.springnew;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
		@RequestMapping("/Home")
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			String name = request.getParameter("name");
			response.getWriter().println("Welcome  " +name);
			
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html>");
			out.println("<body>");
			
			out.println("<h3>Select the action you wanted to perform</h3>");
			out.println("<br/>");
			
			
			
			out.println("<a href='down.jsp'>TestDownload</a>");
			out.println("<a href='add2.jsp'>TestUpload</a>");
			out.println("</body>");
			out.println("</html>");
			
		}

	}


