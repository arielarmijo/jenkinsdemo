package com.talento.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class IOUtil {
	
	public static void writeInput2Output(InputStream input, OutputStream output, int bufferSize) throws IOException {
		streamIO(input, output, bufferSize);
	}
	
	public static void streamIO(InputStream input, OutputStream output, int bufferSize) throws IOException {
		byte[] buffer = new byte[bufferSize];
		int count = 0;
		while ((count = input.read(buffer)) >= 0) {
			output.write(buffer, 0, count);
		}
	}
	
	public static void streamBufferedIO(InputStream input, OutputStream output, int fileSize) throws IOException {
		byte[] bArray = new byte[fileSize];
		try (DataInputStream dis = new DataInputStream(input)) {
			dis.readFully(bArray);
			output.write(bArray);
		}
	}

	public static void streamNIO(InputStream input, OutputStream output, int bufferSize) throws IOException {
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
