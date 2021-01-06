package com.talento.webdemo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

@WebServlet("/delete")
public class DeleteAvatarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(ChangeAvatarServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imagen = request.getParameter("imagen");
		String avatar = imagen;
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		String imageDir = context.getInitParameter("imageDir");
		String imageDirPath = context.getRealPath(imageDir);
		File file = new File(imageDirPath + imagen);
		
		
		@SuppressWarnings("unchecked")
		List<String> imagenes = (List<String>) context.getAttribute("imagenes");
		
		if (imagenes.size() > 1) {
			imagenes.remove(imagen);
			avatar = imagenes.get(0);
			file.delete();
			logger.info(String.format("Imagen %s borrada", file.getName()));
		} else {
			request.setAttribute("mensaje", "Es la última imagen. No te puedes quedar sin avatar!!!");
			request.setAttribute("bsClass", "alert-danger");
		}
		
		
//		@SuppressWarnings("unchecked")
//		Stack<String> historial = (Stack<String>) session.getAttribute("historial");
//		historial.pop();
		
		
		
		context.setAttribute("imagenes", imagenes);
		session.setAttribute("avatar", avatar);
		logger.info(String.format("Avatar cambiado de %s a %s", imagen, avatar));
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

}
