package model.bean;

import java.sql.Timestamp;

public class comment {
	
	private int id;
	private user reader;
	private String content;
	private Timestamp time;
	private news News;
	
	public comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public comment(int id, user reader, String content, Timestamp time, news news) {
		super();
		this.id = id;
		this.reader = reader;
		this.content = content;
		this.time = time;
		News = news;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public user getReader() {
		return reader;
	}

	public void setReader(user reader) {
		this.reader = reader;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public news getNews() {
		return News;
	}

	public void setNews(news news) {
		News = news;
	}
	
	
}
