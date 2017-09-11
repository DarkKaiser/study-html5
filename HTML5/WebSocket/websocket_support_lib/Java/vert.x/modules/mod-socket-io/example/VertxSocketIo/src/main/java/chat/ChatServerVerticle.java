package chat;

import java.io.File;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.Configurer;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;

public class ChatServerVerticle extends Verticle {

	@Override
	public void start() {
		int port = 8080;

		// 1) HTTP Server
		RouteMatcher httpRouteMatcher = new RouteMatcher();
		httpRouteMatcher.get("/", new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest request) {
				request.response().sendFile("web/index.html");
			}
		});
		httpRouteMatcher.get(".*\\.(css|js|html)$", new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest request) {
				request.response().sendFile("web/" + new File(request.path()));
			}
		});

		HttpServer server = vertx.createHttpServer().requestHandler(httpRouteMatcher);

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

}
