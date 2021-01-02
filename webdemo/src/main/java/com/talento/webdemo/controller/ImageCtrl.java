package com.talento.webdemo.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageCtrl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		@SuppressWarnings("unchecked")
		List<Path> paths = (List<Path>) context.getAttribute("paths");
		
		int index;
		Path path = null;
		try {
			index = Integer.parseInt(request.getParameter("id"));
			path = paths.get(index);
		} catch (NumberFormatException ex) {
			index = 0;
		} catch (IndexOutOfBoundsException e) {
			path = paths.get(0);
		}

		ServletOutputStream out = response.getOutputStream();
		File file = new File(path.toString());
		try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
			byte[] bArray = new byte[(int) file.length()];	 
			response.setContentType("image/jpg, image/png");
			dis.readFully(bArray);
			out.write(bArray);
		} catch (Exception e) {
			response.setContentType("text/xml");
			out.print("Error al cargar la imagen\n" + e.getMessage());
		}
		out.close();
	}

}
