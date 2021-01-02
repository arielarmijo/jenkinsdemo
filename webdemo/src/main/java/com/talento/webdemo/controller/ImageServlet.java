package com.talento.webdemo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.talento.utils.IOUtil;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(ImageServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene el contexto de la aplicacion
		ServletContext context = request.getServletContext();

		// Inicializa los parámetros requeridos
		String imageDir = context.getInitParameter("imageDir");
		String image = imageDir + request.getParameter("file");
		String path = context.getRealPath(image);
		String mime = context.getMimeType(image);
		File file = new File(path);
		int fileSize = (int) file.length();
		logger.info("Imagen requerida: " + path + "; " + mime + "; " + fileSize + " Kb");
		
		// Establece cabeceras de la respuesta
		response.setContentType(mime);
		response.setContentLength((int) file.length());
		
		// Define tamaño del buffer de lectura
		int bufferSize = 1024*10; // 10 KB
		
		// Escribe respuesta
		try (InputStream in = context.getResourceAsStream(image);
			 OutputStream out = response.getOutputStream()) {
			IOUtil.writeInput2Output(in, out, bufferSize);
		}

	}

}
