package io.soap;

/**
 * @author tian
 * 命令行发布服务 -Djava.ext.dirs=  指定引用的jar包路径
 * java -Djava.ext.dirs=./ org.apache.axis.client.AdminClient deploy.wsdd
 */
public class HelloService {

	public String sayHello(String username){
		return "Hello:"+username;
	}
	
	public String sayGoodBy(String username){
		return "GoodBy:"+username;
	}
}
