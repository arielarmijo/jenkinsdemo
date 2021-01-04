package com.talento.webdemo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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

import com.talento.webdemo.utils.StopWatch;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(ImageServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Registra duración de la respuesta
		StopWatch sw = new StopWatch();
				
		// Obtiene el contexto de la aplicacion
		ServletContext context = request.getServletContext();

		// Inicializa los parámetros requeridos
		String imageDir = context.getInitParameter("imageDir");
		String image = imageDir + request.getParameter("file");
		String path = context.getRealPath(image);
		String mime = context.getMimeType(image);
		File file = new File(path);
		int fileSize = (int) file.length();
		
		// Establece cabeceras de la respuesta
		response.setContentType(mime);
		response.setContentLength((int) file.length());
		
		// Define tamaño del buffer de lectura/escritura
		int bufferSize = 1024*10; // 10 KB
		
		// Escribe respuesta
		try (InputStream in = new BufferedInputStream(new FileInputStream(file));
			 OutputStream out = response.getOutputStream()) {
			byte[] buffer = new byte[bufferSize];
			int bytesReaded;
			while ((bytesReaded = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesReaded);
				out.flush();
			}
		}
		
		logger.info("Imagen requerida " + file.getName() + " (" + fileSize + " Kb) entregada en " + sw.getElapsedTime() + " ms");
		
	}

}
