package com.talento.webdemo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

@WebServlet(urlPatterns="/", loadOnStartup=1)
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(HomeServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		ServletContext context = config.getServletContext();
		ImageService service = new ImageServiceLocal(context);
		String file = context.getInitParameter("file");
		List<String> imagenes = service.getImagesNames();
		context.setAttribute("avatar", file);
		context.setAttribute("imagenes", imagenes);
	    logger.info(String.format("HomeCtrl configurado: avatar=%s, imágenes=%d", file, imagenes.size()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("webdemo.jsp").forward(request, response);
	}

}
