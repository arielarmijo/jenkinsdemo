package com.talento.webdemo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talento.webdemo.service.ImageService;
import com.talento.webdemo.service.ImageServiceLocal;

@WebServlet("/")
public class HomeCtrl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		ServletContext context = config.getServletContext();
		String path = context.getRealPath("/img");
		ImageService service = new ImageServiceLocal();
		List<Path> paths = service.getImagePaths(path);
		context.setAttribute("path", path);
		context.setAttribute("paths", paths);
		context.setAttribute("fileNames", service.getImageNames(path));
		context.setAttribute("avatarIndex", 0);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("webdemo.jsp").forward(request, response);
	}

}
