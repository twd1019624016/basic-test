package upaiyun;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import net.sf.json.JSONObject;

public class Demo3 {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Object Object = null;
		map.put("success", false);
		map.put("msg", "jjjjjjj");
		map.put("piId", Object);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(out, JsonEncoding.UTF8);

		objectMapper.writeValue(jsonGenerator, map);

		byte[] byteArray = out.toByteArray();

		String bytes = new String(byteArray);

		System.out.println(bytes);

		JSONObject jsonObject = JSONObject.fromObject(bytes);
		Map<String, Object> map2 = new HashMap<String, Object>();
		for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map2.put(key, jsonObject.get(key));
		}

		String writeValueAsString = objectMapper.writeValueAsString(map2);
		/* Result readValue = objectMapper.readValue(bytes, Result.class); */
		/* Map readValue = objectMapper.readValue(bytes, Map.class); */

		System.out.println(writeValueAsString);
		/*
		 * String writeValueAsString = objectMapper.writeValueAsString(map);
		 * 
		 * System.out.println(writeValueAsString);
		 */
	}

	@Test
	public void doubleToJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Object Object = null;
		map.put("success", false);
		map.put("msg", "jjjjjjj");
		map.put("piId", Object);

		String writeValueAsString = objectMapper.writeValueAsString(map);
		String writeValueAsString2 = objectMapper.writeValueAsString(writeValueAsString);

		System.out.println(writeValueAsString);
		System.out.println(writeValueAsString2);
	}

}
