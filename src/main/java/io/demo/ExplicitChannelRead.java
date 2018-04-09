package io.demo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExplicitChannelRead {

	public static void main(String[] args) {
		
		int count;
		Path filePath = null;
		
		//获得关联文件的path对象
		filePath = Paths.get("text.txt");
		
		System.out.println(filePath.toUri());
		// 在try-with-resources块中 获得文件的channel
		try(SeekableByteChannel newByteChannel = Files.newByteChannel(filePath);
) {
			ByteBuffer mBuf = ByteBuffer.allocate(128);
				//分配buffer
			do{
				//读入buffer
				count = newByteChannel.read(mBuf);
				//读到文件末尾的时候停止读取
				if(count != -1) {
					 //调用buffer 的 rewind 方法 使其可以读
					mBuf.rewind();
					//从buffer 中读取文件，并显示
					for(int i=0;i<count;i++) {
						System.out.println((char)mBuf.get());
					}
				}
			}while(count != -1);
		        
			if(newByteChannel instanceof FileChannel) {
				FileChannel channel = (FileChannel) newByteChannel;
				MappedByteBuffer mapBuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
				for(int i=0;i<channel.size();i++) {
					System.out.println((char)mapBuf.get());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
