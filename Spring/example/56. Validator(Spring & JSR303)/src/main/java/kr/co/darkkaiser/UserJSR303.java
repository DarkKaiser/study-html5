package kr.co.darkkaiser;

import javax.validation.constraints.Size;

public class UserJSR303 {

	@Size(min=1)
	private String name = "";
	
	@Telephone
    private String telephone;

	UserJSR303() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
