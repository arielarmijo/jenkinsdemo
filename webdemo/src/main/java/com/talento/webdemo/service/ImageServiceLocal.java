package com.talento.webdemo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImageServiceLocal implements ImageService {

	@Override
	public List<Path> getImagePaths(String path) {
		List<Path> imagenes = null;
		try {
			imagenes = Files.list(Paths.get(path))
					   		.filter(p -> !Files.isDirectory(p))
					   		.map(p -> p.toAbsolutePath())
					   		.sorted().collect(Collectors.toList());
			System.out.println("\nImagenes encontradas:");
			for (Path imagen : imagenes) {
				System.out.println(imagen);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagenes;
	}

	@Override
	public List<String> getImageNames(String path) {
		return getImagePaths(path).stream().map(p -> p.getFileName().toString()).collect(Collectors.toList());
	}

}
