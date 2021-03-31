package thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.IpInfo;

public class IPCollectTask extends TimerTask {
	/**
	 * 根据网页Document解析出ip地址信息
	 * @param doc
	 */
	private List parseAgencyIpLists(Document doc) {
		Elements eleLists = doc.getElementsByTag("tbody");
		Element tbody = eleLists.get(0); // 获取tbody
		Elements trLists = tbody.children(); // 获取十条ip记录
		List ipList = new ArrayList<IpInfo>();
		for (Element tr : trLists) { 
			Elements tdElements = tr.children(); // tr中的td包含了具体的信息
			String ipAddress = tdElements.get(0).text();
			int port = Integer.parseInt(tdElements.get(1).text());
			String anonymousType = tdElements.get(2).text();
			String type = tdElements.get(3).text();
			String location = tdElements.get(4).text();
			String responseSpeed = tdElements.get(5).text();
			String confirmTime = tdElements.get(6).text();
			
			IpInfo ipInfo = new IpInfo(ipAddress, port, location,
					anonymousType, type, confirmTime,
					responseSpeed);
			System.out.println(ipInfo.toString());
			ipList.add(ipInfo);
		}
		return ipList;
	}
	
	/**
	 * 根据提供的url和host来获取页面信息
	 * @param url
	 * @param host
	 * @return
	 */
	private Document getDocumentByUrl(String url, String host) {
		Document doc = null;
		try {
			doc = Jsoup
					.connect(url)
					.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0")
					.header("Host", host)
					.timeout(5000)
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc;
	} 
	
	/**
	 * 获取http://www.kuaidaili.com/free/的ip
	 */
	private List getAgencyIpLists() {
		List ipList = new ArrayList<IpInfo>();
		// 第一次访问，需解析总共多少页
		String baseUrl = "http://www.kuaidaili.com/free/inha/";
		String host = "www.kuaidaili.com";
		Document doc = getDocumentByUrl(baseUrl, host);
		// 解析ip信息
		ipList.addAll(parseAgencyIpLists(doc));
		// 休眠3秒
		fallSleep(3);
		Element listNav = doc.getElementById("listnav");
		// 获取listnav下的li列表
		Elements liLists = listNav.children().get(0).children();
		// 获取含有多少页的子元素
		Element pageNumberEle = liLists.get(liLists.size() - 2);
		// 解析有多少页
		int pageNumber = Integer.parseInt(pageNumberEle.text());
		// 拼接成其他页的访问地址
		for (int index = 2; index <= 3; index++) {
		 	String url = baseUrl + index;
			doc = getDocumentByUrl(url, host);
			ipList.addAll(parseAgencyIpLists(doc));
			// 休眠3秒
			fallSleep(3);
		}
		return ipList;
	}
	
	/**
	 * 进行休眠
	 */
	private void fallSleep(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("IPCollect task is running");
		List ipList = getAgencyIpLists();
		System.out.println(ipList);
		
	}

	public static void main(String[] args) {
		IPCollectTask task = new IPCollectTask();
		Thread thread = new Thread(task);
		thread.start();
		try {
			Thread.sleep(30 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
