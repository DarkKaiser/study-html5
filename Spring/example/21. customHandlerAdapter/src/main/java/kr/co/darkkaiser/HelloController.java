package kr.co.darkkaiser;

import java.util.Map;

public class HelloController implements SimpleController {
	
	public HelloController() {
		System.out.println("HelloController »ý¼ºÀÚ");
	}

	@Override
	@ViewName("home")
	@RequireParams({"name"})
	public void control(Map<String, String> params, Map<String, Object> model) {
		model.put("message", "Hello " + params.get("name"));
	}

}
