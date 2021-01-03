package com.talento.webdemo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet("/change")
public class ChangeAvatarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(ChangeAvatarServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String avatar = request.getParameter("avatar");
		session.setAttribute("avatar", avatar);
		logger.info("Avatar cambiado a: " + avatar);
		request.getRequestDispatcher("webdemo.jsp").forward(request, response);
	}

}
