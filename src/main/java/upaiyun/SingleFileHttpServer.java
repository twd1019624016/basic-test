package upaiyun;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class SingleFileHttpServer extends Thread {

	private byte[] content;
	private byte[] header;
	private int port = 8080;

	private SingleFileHttpServer(String data, String encoding, String MIMEType, int port)
			throws UnsupportedEncodingException {

		this(data.getBytes(encoding), encoding, MIMEType, port);
	}

	public SingleFileHttpServer(byte[] data, String encoding, String MIMEType, int port)
			throws UnsupportedEncodingException {

		this.content = data;
		this.port = port;
		String header = "HTTP/1.0 200 OK\r\n" + "Server: OneFile 1.0\r\n" + "Content-length: " + this.content.length
				+ "\r\n" + "Conten-type: " + MIMEType + "\r\n\r\n";
		this.header = header.getBytes("ASCII");
	}

	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(this.port);
			System.out.println("Accepting connections on port " + server.getLocalPort());
			System.out.println("Data to be sent");
			System.out.write(this.content);

			while (true) {
				Socket connection = null;

				try {

					connection = server.accept();
					OutputStream out = new BufferedOutputStream(connection.getOutputStream());
					InputStream in = new BufferedInputStream(connection.getInputStream());
					BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
					int available = bufferedInputStream.available();
					byte[] b = new byte[available];
					int read = bufferedInputStream.read(b);
					StringBuilder request = new StringBuilder();
					String string = new String(b, Charset.forName("utf8"));
					String string2 = new String(b, Charset.forName("ISO-8859-1"));
					System.out.println(string);
					System.out.println(string2);
					/*
					 * while(true) { int c = in.read(); if(c == '\r' || c ==
					 * '\n' || c == -1) { break; } request.append((char)c);
					 * 
					 * }
					 */

					if (request.toString().indexOf("HTTP/") != -1) {
						out.write(this.header);
					}

					out.write(this.content);
					out.flush();
				} catch (Exception e) {

					e.printStackTrace();
				} finally {
					if (connection != null) {
						connection.close();
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("could not start server. Port Occupied");
		}
	}

	public static void main(String[] args) throws IOException {
		String[] params = { "C:/Users/tian/Desktop/新建文本文档.html", "-1", "fffff" };
		args = params;

		String contentType = "text/plain";

		if (args[0].endsWith(".html") || args[0].endsWith(".htm")) {
			contentType = "text/html";
		}

		/* InputStream in = new FileInputStream(args[0]); */

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int b;
		/*
		 * while((b = in.read())!= -1) { out.write(b); } byte[] data =
		 * out.toByteArray();
		 */

		int port = 0;
		try {
			port = Integer.parseInt(args[1]);

			if (port < 1 || port > 65535) {
				port = 8080;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String encoding = "ASCII";
		if (args.length > 2) {
			encoding = args[2];
		}

		Thread t = new SingleFileHttpServer("", "utf8", contentType, port);

		t.start();

	}
}
