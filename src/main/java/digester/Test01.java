package digester;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

public class Test01 {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
	 
		/*Properties properties = System.getProperties();
		Set<Entry<Object, Object>> entrySet = properties.entrySet();*/
		Map<String, String> getenv = System.getenv();
		Set<Entry<String, String>> entrySet = getenv.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
	
		String string = System.getProperties().toString();
		System.out.println(string);
		//注册关闭钩子
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		
		
		
		String path = System.getProperty("user.dir") + File.separator + "src/main/java/digester";

		File file = new File(path, "employee.xml");

		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());

		SAXParserFactory factory = SAXParserFactory.newInstance();

		Digester digester = new Digester(factory.newSAXParser());

		// 添加rules
		digester.addObjectCreate("employee", Employee.class);
		digester.addSetProperties("employee");
		digester.addCallMethod("employee", "printName");

		try {
			Employee employee = digester.parse(file);
			System.out.println(employee.getFirstName());
			System.out.println(employee.getLastName());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		}
	}
}

class ShutdownHook extends Thread {

	@Override
	public void run() {
		System.out.println("shutdown");
	}
	
}
