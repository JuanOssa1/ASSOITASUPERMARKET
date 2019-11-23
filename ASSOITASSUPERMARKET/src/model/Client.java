package model;

public abstract class Client {
	private String id;
	private String name;
	private String age;
	private String string;
	public Client(String id, String name, String age, String string) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.string = string;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
}
