package path;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class Demo1 {

	public static void main(String[] args) throws IOException {
		FileSystem fileSystem = FileSystems.getDefault();
		System.out.println(fileSystem);
		Iterable<FileStore> fileStores = fileSystem.getFileStores();
	    for (FileStore fileStore : fileStores) {
			System.out.println(fileStore);
			System.out.println(fileStore.getTotalSpace());
		}
	}
}
