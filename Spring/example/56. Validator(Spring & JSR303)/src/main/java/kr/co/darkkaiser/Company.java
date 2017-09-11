package kr.co.darkkaiser;

import javax.validation.constraints.Size;

public class Company {

	@Size(min=5)
	private String name = "";
	
	Company() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
