package com.talento.webdemo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/change")
public class ChangeAvatarCtrl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		@SuppressWarnings("unchecked")
		List<String> imagenes = (List<String>) context.getAttribute("imagenes");
		int avatarIndex = Integer.parseInt(request.getParameter("index"));
		context.setAttribute("avatarIndex", avatarIndex);
		request.getRequestDispatcher("webdemo.jsp").forward(request, response);
	}

}
