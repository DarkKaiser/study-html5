package egovframework.vertx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

	@Autowired
	private ChatVerticle chatVerticle;

	public ChatController() {
		System.out.println("## ChatController 생성자 호출");
	}

	@RequestMapping(value="/chat/chat.do")
	public String start(){
		return "chat/chat";
	}

	@RequestMapping("/chat/chatSend.do")
	public @ResponseBody String send(){
		// chatVerticle.getIo().sockets().emit("echo", new JsonObject().putString("data", "hello spring"));
		return "ok";
	}

}
