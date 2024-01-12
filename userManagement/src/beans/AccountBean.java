package beans;

import java.io.Serializable;

public class AccountBean implements Serializable {
	private int id;
	private String pass;
	private String mail;
	private String name;
	private int age;
	
	public AccountBean() {}
	
	public AccountBean(int id, String pass, String mail, String name, int age) {
		this(pass, mail, name, age);
		this.id = id;
	}
	
	public AccountBean(String pass, String mail, String name, int age) {
		this.pass = pass;
		this.mail = mail;
		this.name = name;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
}
