/*package upaiyun;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Demo1 {

	public static void main(String[] args) {
		HttpPost post = new HttpPost("http://127.0.0.1:8080/consign2?catId=9");

		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("goodsName", "你好 HttpClient_Post"));

			HttpEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
			Header contentEncoding = httpEntity.getContentEncoding();
			post.setEntity(httpEntity);

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse execute = httpClient.execute(post);

			HttpEntity entity = execute.getEntity();
		} catch (UnsupportedEncodingException e) {
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void httpPost() {
		String decode = URLDecoder.decode(
				"catId%3D9%24goodsNam%3D123123%26mobileDesc%3D1231%E4%B8%AD%E5%9B%BD%26imgUrl=http%3A%2F%2Fhuifeideyu2.b0.upaiyun.com%2F%2F20170223%2F1487815915593.jpg&districtid=1&expectPrice=12312&originalPrice=23123&goodsDegree=9");

		System.out.println(decode);
	}

	@Test
	public void httpfs() {
		String f = "！@#%&&***（）））JNC78899！@#%&&***（）））JNC78899！@#%&&***（）））JNC78899！@#%&&***（）））JNC78899！@#%&&***（）））JNC78899！@#%&&***（）））JNC78899！@#%&&***（）））JNC78899！@#";
		System.out.println(f.length());
	}

}
*/