package com.talento.webdemo.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebServlet("/image")
public class ImageCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(ImageCtrl.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Obtiene el contexto de la aplicacion
		ServletContext context = request.getServletContext();

		// Inicializa los parámetros requeridos
		String imgDir = "/img/";
		String image = imgDir + request.getParameter("file");
		String path = context.getRealPath(image);
		String mime = context.getMimeType(path);
		File file = new File(path);
		int fileSize = (int) file.length();
		logger.info("Imagen requerida: " + path + "; " + mime + "; " + fileSize + " Kb");
		
		// Establece cabeceras de la respuesta
		response.setContentType(mime);
		response.setContentLength((int) file.length());
		
		// Define tamaño del buffer de lectura
		int bufferSize = 10240;
		
		try (InputStream in = context.getResourceAsStream(image);
			 OutputStream out = response.getOutputStream()) {
			// Escribe respuesta usando IO API
			//streamIO(in, out, bufferSize);
			// Escribe respuesta usando NIO API
			//streamNIO(in, out, bufferSize);
			// Escribe respuesta usando Buffered IO
			streamBufferedIO(in, out, fileSize);
		}

	}
	
	public void streamBufferedIO(InputStream input, OutputStream output, int fileSize) throws IOException {
		byte[] bArray = new byte[fileSize];
		try (DataInputStream dis = new DataInputStream(input)) {
			dis.readFully(bArray);
			output.write(bArray);
		}
	}

	public void streamIO(InputStream input, OutputStream output, int bufferSize) throws IOException {
		byte[] buffer = new byte[bufferSize];
		int count = 0;
		while ((count = input.read(buffer)) >= 0) {
			output.write(buffer, 0, count);
		}
	}

	public void streamNIO(InputStream input, OutputStream output, int bufferSize) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);
		try (ReadableByteChannel inputChannel = Channels.newChannel(input);
			 WritableByteChannel outputChannel = Channels.newChannel(output)) {
			while (inputChannel.read(buffer) != -1) {
				buffer.flip();
				outputChannel.write(buffer);
				buffer.clear();
			}
		}
	}

}
