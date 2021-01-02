package com.talento.webdemo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.talento.webdemo.controller.HomeServlet;

public class ImageServiceLocal implements ImageService {

	private ServletContext context;
	private String imageDir;
	private static Log logger = LogFactory.getLog(HomeServlet.class);
	
	public ImageServiceLocal(ServletContext context) {
		this.context = context;
		imageDir = context.getInitParameter("imageDir");
	}

	@Override
	public List<String> getImagesNames() {
		Set<String> recursos = context.getResourcePaths(imageDir);
		recursos.stream().forEach(r -> logger.info("Imagen encontrada: " + r));
		return recursos.stream().map(s -> s.replaceFirst(imageDir, "")).sorted().collect(Collectors.toList());
	}

}
