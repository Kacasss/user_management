package beans;

import java.io.Serializable;

public class AdminLoginBean implements Serializable {
	private int id;
	private String pass;
	private String mail;
	private String name;
	
	public AdminLoginBean(int id, String pass, String mail, String name) {
		this.id = id;
		this.pass = pass;
		this.mail = mail;
		this.name = name;
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

}
