package com.talento.webdemo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.talento.webdemo.service.ImageService;
import com.talento.webdemo.service.ImageServiceLocal;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(HomeServlet.class);
	private ServletContext context;
	private ImageService service;
	private String avatar;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext();
		service = new ImageServiceLocal(context);
		avatar = context.getInitParameter("avatar");
		List<String> imagenes = service.getImagesNames();
		context.setAttribute("avatar", avatar);
		context.setAttribute("imagenes", imagenes);
	    logger.info(String.format("Configuración establecida: avatar=%s, imágenes=%d", avatar, imagenes.size()));
	    super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

}
