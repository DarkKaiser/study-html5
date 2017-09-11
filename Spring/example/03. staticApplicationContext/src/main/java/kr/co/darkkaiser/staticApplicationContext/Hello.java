package kr.co.darkkaiser.staticApplicationContext;

public class Hello {
	
	private String name = "default name";

	public void say() {
		System.out.println("hello.say()~ " + this.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
