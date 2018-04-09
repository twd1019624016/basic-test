package io.demo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

import java.nio.file.attribute.BasicFileAttributes;

public class NIOCopy {

	public static void main(String[] args) throws IOException {

		Path target = Paths.get("C://Users//tian//Desktop//sqldest.txt");
		/*
		 * Path source = Paths.get("C://Users//tian//Desktop//sql.txt");
		 * 
		 * 
		 * try { Files.copy(source,
		 * target,StandardCopyOption.COPY_ATTRIBUTES,StandardCopyOption.
		 * REPLACE_EXISTING); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }
		 */

		BasicFileAttributes readAttributes = Files.readAttributes(target, BasicFileAttributes.class);
		System.out.println(readAttributes.creationTime());
		try (DirectoryStream<Path> newDirectoryStream = Files
				.newDirectoryStream(Paths.get("C://Users//tian//Desktop//资料"),"*.pdf")) {
			for (Path path : newDirectoryStream) {
				BasicFileAttributes readAttributes2 = Files.readAttributes(path, BasicFileAttributes.class);
				if (readAttributes2.isDirectory()) {
					System.out.println("<DIR> ");
				} else {
					System.out.println("    ");
				}

				System.out.println(path.getFileName());
			}
		} catch (Exception e) {
			
		}
		
		Files.walkFileTree(Paths.get("C://Users//tian//Desktop//资料"), new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				System.out.println(path);
				return super.visitFile(path, attrs);
			}

			
		
		});
		
		
		Runtime runtime = Runtime.getRuntime();
		
		try {
			Process process = runtime.exec("notepad");
			process.waitFor();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("end");
	}
}
