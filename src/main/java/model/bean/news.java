package model.bean;

import java.sql.Timestamp;

public class news {

	private int id;
	private String name;
	private int view;
	private Timestamp date_create;
	private String picture;
	private String detail;
	private category_child cat;
	private user author;
	
	public news() {
		super();
		// TODO Auto-generated constructor stub
	}

	public news(int id, String name, int view, Timestamp date_create, String picture, String detail, category_child cat,
			user author) {
		super();
		this.id = id;
		this.name = name;
		this.view = view;
		this.date_create = date_create;
		this.picture = picture;
		this.detail = detail;
		this.cat = cat;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public category_child getCat() {
		return cat;
	}

	public void setCat(category_child cat) {
		this.cat = cat;
	}

	public user getAuthor() {
		return author;
	}

	public void setAuthor(user author) {
		this.author = author;
	}
	
	
}
