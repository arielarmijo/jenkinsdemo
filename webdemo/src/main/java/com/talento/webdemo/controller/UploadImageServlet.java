package com.talento.webdemo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.talento.webdemo.service.ImageService;
import com.talento.webdemo.service.ImageServiceLocal;

@WebServlet("/upload")
// 1024 bytes = 1 KB
@MultipartConfig(fileSizeThreshold = 1024*500,	// 500 KB
				 maxFileSize = 1024*1024*1,		// 1 MB
				 maxRequestSize = 1024*1024*5)	// 5 MB
public class UploadImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(UploadImageServlet.class);
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Construye la ruta del directorio donde se guardarán las imágenes
		ServletContext context = request.getServletContext();
		String imageDir = context.getInitParameter("imageDir");
		String imageDirPath = context.getRealPath(imageDir);
		
		try {
			// Guarda archivo en directorio /img
			for (Part part : request.getParts()) {
				String fileName = part.getSubmittedFileName();
				part.write(imageDirPath + fileName);
				File file = new File(imageDirPath + fileName);
				logger.info("Imagen guardada en: " + file.getAbsolutePath());
			}
			// Actualiza lista de imágenes
			ImageService service = new ImageServiceLocal(context);
			context.setAttribute("imagenes", service.getImagesNames());
			// Envía retroalimentación al usuario
			request.setAttribute("bsClass", "alert-success");
			request.setAttribute("mensaje", "Imagen guardada.");
		} catch (Exception e) {
			request.setAttribute("bsClass", "alert-danger");
			request.setAttribute("mensaje", e.getMessage());
			logger.error(e.getMessage());
		}
		// Redirige a página de inicio
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

}
