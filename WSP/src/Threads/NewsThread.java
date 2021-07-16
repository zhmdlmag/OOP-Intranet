package Threads;

import Intranet.DataBase;
import Intranet.News;

public class NewsThread extends Thread {
	private News news = null;
	private Integer sleepTime = 10000;
	
	public NewsThread(News news) {
		this.news = news;
	}
	
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DataBase.getInstance().removeNews(news);
	}
}