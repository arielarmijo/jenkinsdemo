package com.talento.webdemo.service;

import java.nio.file.Path;
import java.util.List;

public interface ImageService {

	List<Path> getImagePaths(String path);
	List<String> getImageNames(String path);
	
}
