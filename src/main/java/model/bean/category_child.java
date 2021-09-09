package model.bean;

public class category_child {
	
	private int id;
	private String name;
	private category Category;
	
	public category_child(int id, String name, category category) {
		super();
		this.id = id;
		this.name = name;
		Category = category;
	}

	public category_child() {
		super();
		// TODO Auto-generated constructor stub
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

	public category getCategory() {
		return Category;
	}

	public void setCategory(category category) {
		Category = category;
	}

	
	
	
}
