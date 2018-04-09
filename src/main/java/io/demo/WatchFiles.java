package io.demo;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchFiles {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Path path = Paths.get("C:/Users/tian/Desktop/path");
		String probeContentType = Files.probeContentType(path);
		System.out.println(probeContentType);
		
		System.out.println(path.toFile().exists());
		FileSystem fileSystem = FileSystems.getDefault();
		WatchService newWatchService = fileSystem.newWatchService();
		
		path.register(newWatchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
		WatchKey watchKey;
		do {
			watchKey = newWatchService.take();
			if(watchKey != null) {
				watchKey.pollEvents().parallelStream().forEach(
						event->{
							System.out.println(event.context());
							System.out.println(event.kind().name());
						}
						);
			}
		}while(watchKey.reset());
	}
}
