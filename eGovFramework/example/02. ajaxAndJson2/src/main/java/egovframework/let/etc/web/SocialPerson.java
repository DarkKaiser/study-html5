package egovframework.let.etc.web;

public class SocialPerson {

	private String name;
	private String telno;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelno() {
		return telno;
	}
	
	public void setTelno(String telno) {
		this.telno = telno;
	}

	public void generateValue() {
		this.name = "Dadadada";
		this.telno = "한글";
	}
	
}
