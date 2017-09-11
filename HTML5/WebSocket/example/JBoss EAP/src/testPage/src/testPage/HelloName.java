package testPage;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/chat")
public class HelloName {

	@OnMessage
	public String sayHello(String name) {
		System.out.println("### Say hello to '" + name + "'");
		return name;    
    }

	@OnOpen
	public void helloOnOpen(Session session) {
		System.out.println("### WebSocket opened: " + session.getId());
	}

	@OnClose
	public void helloOnClose(CloseReason reason) {
		System.out.println("### Closing a WebSocket due to " + reason.getReasonPhrase());
	}

}
