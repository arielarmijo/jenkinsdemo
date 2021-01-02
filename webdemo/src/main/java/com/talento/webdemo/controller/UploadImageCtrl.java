package com.talento.webdemo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.talento.webdemo.service.ImageService;
import com.talento.webdemo.service.ImageServiceLocal;

@WebServlet("/upload")
public class UploadImageCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(UploadImageCtrl.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext context = request.getServletContext();
		String imgDir = context.getInitParameter("imgDir");
		String path = context.getRealPath(imgDir);
		
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(maxMemSize);
				factory.setRepository(new File("/"));
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(maxFileSize);
				List<FileItem> fileItems = upload.parseRequest(request);
				for (FileItem fileItem : fileItems) {
					String fileName = fileItem.getName();
					File file = new File(path  + fileName);
					fileItem.write(file);
					logger.info("Imagen guardada en: " + file.getAbsolutePath());
				}
				Set<String> recursos = context.getResourcePaths(imgDir);
				List<String> imagenes = recursos.stream().map(s -> s.replaceFirst(imgDir, "")).sorted().collect(Collectors.toList());
				context.setAttribute("imagenes", imagenes);
				request.setAttribute("bsClass", "text-success");
				request.setAttribute("mensaje", "Imagen subida exitosamente");
			} catch (Exception e) {
				request.setAttribute("bsClass", "text-danger");
				request.setAttribute("mensaje", e.getMessage());
			}
		}
		request.getRequestDispatcher("webdemo.jsp").forward(request, response);
	}

}
