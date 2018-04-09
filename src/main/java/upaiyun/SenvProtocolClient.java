package upaiyun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class SenvProtocolClient {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = null;

		URL url = new URL("http://127.0.0.1:8080/consign2?catId=9");

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

		// //设置连接属性
		httpConn.setDoOutput(true);// 使用 URL 连接进行输出
		httpConn.setDoInput(true);// 使用 URL 连接进行输入
		httpConn.setUseCaches(false);// 忽略缓存
		httpConn.setRequestMethod("POST");// 设置URL请求方法

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("goodsName", "张桑");
		params.put("imgUrl", "rrrrr");
		// 设置请求属性
		// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		httpConn.setRequestProperty("Content-length", String.valueOf(postDataBytes.length));
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencode");
		httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		httpConn.setRequestProperty("Charset", "UTF-8");
		//
		String name = URLEncoder.encode("黄武艺", "utf-8");
		httpConn.setRequestProperty("goodsName", name);

		// 建立输出流，并写入数据
		OutputStream outputStream = httpConn.getOutputStream();
		outputStream.write(postDataBytes);
		outputStream.close();
		// 获得响应状态
		int responseCode = httpConn.getResponseCode();

		if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
			// 当正确响应时处理数据
			StringBuffer sb = new StringBuffer();
			String readLine;
			BufferedReader responseReader;
			// 处理响应流，必须与服务器响应流输出的编码一致
			responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf8"));
		}

	}
}
