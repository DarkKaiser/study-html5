package kr.co.darkkaiser;

public class SimpleServiceImpl implements SimpleService {
	
	private int id;

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String printNameId() {
		System.out.println("SimpleService : Method printNameId() : My name is " + name + " and my id is " + id);
		return "test";
	}

	@Override
	public void checkName() {
		if (name.length() < 20) {
            throw new IllegalArgumentException();
        }
	}

	@Override
	public String sayHello(String message) {
		System.out.println("SimpleService : Method sayHello() : Hello! " + message);
		return message;
	}

}
