package thread;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class VoteThread{
	
	public static void main(String[] args) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse response = null;
		HttpPost httpPost = null;
		HttpEntity entity = null;
		HttpHost proxy = null;
		// 设置代理IP，有代理IP池的话可以循环调用
		proxy = new HttpHost("119.57.156.90",53281,"HTTP");
		httpPost = new HttpPost("http://vote.e23.cn/zhuanti/mingpai2020/vote.jsp");
		httpPost.addHeader("Host", "vote.e23.cn");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.addHeader("Referer","http://vote.e23.cn/zhuanti/mingpai2020/index.html?spm=0.0.0.0.JjvVPS");
		httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36 Edg/86.0.622.51");
		try {
			// 投票请求参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("UserNo", "1089"));
			nvps.add(new BasicNameValuePair("ServiceId", "yxjnmp2020"));
			nvps.add(new BasicNameValuePair("act", "do"));
			nvps.add(new BasicNameValuePair("groupname", "1"));
			nvps.add(new BasicNameValuePair("x", "75"));
			nvps.add(new BasicNameValuePair("y", "22"));
			nvps.add(new BasicNameValuePair("ipname", ""));
			
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
			
			response = client.execute(proxy,httpPost);
			
			entity = response.getEntity();
			byte[] bytes = EntityUtils.toByteArray(entity);
			String temp = new String(bytes, "gbk");
			byte[] contentData = temp.getBytes("utf-8");
			EntityUtils.consume(entity);
			System.out.println(new String(contentData));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
