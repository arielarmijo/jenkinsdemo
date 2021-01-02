package com.talento.webdemo.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet("/change")
public class ChangeAvatarCtrl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(ChangeAvatarCtrl.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String avatar = request.getParameter("avatar");
		context.setAttribute("avatar", avatar);
		request.getRequestDispatcher("webdemo.jsp").forward(request, response);
		logger.info("Avatar: " + avatar);
	}

}
