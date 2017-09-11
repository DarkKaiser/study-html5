package egovframework.let.etc.web;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
public class SocialPerson {

	private String name;
	private String telno;
	
	@XmlElement
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement
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
	
	@Override
	public String toString() {
		return "SocialPersion";
	}
	
}
