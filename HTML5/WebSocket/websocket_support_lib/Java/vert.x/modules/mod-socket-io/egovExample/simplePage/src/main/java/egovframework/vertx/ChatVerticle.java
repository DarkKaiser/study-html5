package egovframework.vertx;

import javax.annotation.PreDestroy;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.Configurer;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;

import egovframework.vertx.common.DefaultEmbeddableVerticle;

public class ChatVerticle extends DefaultEmbeddableVerticle {

	private HttpServer server;

	private SocketIOServer io;
	
	public ChatVerticle() {
		System.out.println("## ChatVerticle 생성자 호출");
	}

	@Override
	public void start(Vertx vertx) {
    	int port = 19999;

		// 1) HTTP Server
		server = vertx.createHttpServer();

		// 2) mod-socket-io Chat Server
		final SocketIOServer io = new DefaultSocketIOServer(vertx, server);
		io.configure(new Configurer() {
	        public void configure(JsonObject config) {
	            config.putString("transports", "websocket,xhr-polling,jsonp-polling");
	            // config.putString("transports", "websocket,flashsocket,htmlfile,xhr-polling,jsonp-polling");
	        }
	    });

		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			public void handle(final SocketIOSocket socket) {
				System.out.println("## New client connected![socket:" + socket + "]");

				JsonObject jsonObject = new JsonObject();
                jsonObject.putString("msg", "Welcome !");
                socket.emit("toClient", jsonObject);
                
                jsonObject.putString("msg", "새로운 클라이언트가 접속하였습니다.");
				socket.broadcast().emit("toClient", jsonObject);

                socket.on("fromClient", new Handler<JsonObject>() {
                    public void handle(JsonObject data) {
                    	System.out.println("## Message from client : " + data);

                    	io.sockets().emit("toClient", data);
//                    	socket.emit("toClient", data); 자기 자신에게 전송
//                    	socket.broadcast().emit("toClient", data); 자기 자신을 제외한 모든 사용자에게 전송
                    }
                });
                socket.onDisconnect(new Handler<JsonObject>() {
					public void handle(JsonObject data) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.putString("msg", "클라이언트 접속이 해제되었습니다.");
						socket.broadcast().emit("toClient", jsonObject);
					}
				});
			}
		});

		System.out.println("Server is running on http://localhoot:" + port);
		server.listen(port, "localhost");
	}

	@PreDestroy
	public void test() {
		if (server != null) {
			server.close();
		}
		
		server = null;
	}

	public SocketIOServer getIo() {
		return io;
	}
	
}
