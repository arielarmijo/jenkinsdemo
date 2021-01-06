package com.talento.webdemo.controller;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.ServletContext;
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
		
		String avatarPrevio;
		String avatar = request.getParameter("avatar");
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		Stack<String> historial = (Stack<String>) session.getAttribute("historial");
		// Si el historial está vacío, el primer elemento es foca.jpg
		if (historial == null) {
			historial = new Stack<>();
			ServletContext context = request.getServletContext();
			avatarPrevio = (String) context.getAttribute("avatar");
		} else {
			avatarPrevio = (String) session.getAttribute("avatar");
		}
		
		historial.push(avatarPrevio);
	
		session.setAttribute("avatar", avatar);
		session.setAttribute("historial", historial);
		logger.info(String.format("Avatar cambiado de %s a %s", historial.peek(), avatar));
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		
	}

}
