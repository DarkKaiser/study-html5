package sockjs;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.core.sockjs.SockJSSocket;
import org.vertx.java.deploy.Verticle;

public class SockJSExample extends Verticle {

	@Override
	public void start() throws Exception {
		HttpServer server = vertx.createHttpServer();

		server.requestHandler(new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest req) {
				if (req.path.equals("/"))
					req.response.sendFile("index.html"); // Serve the
																// html
			}
		});

		SockJSServer sockServer = vertx.createSockJSServer(server);

		sockServer.installApp(new JsonObject().putString("prefix", "/testapp"), new Handler<SockJSSocket>() {
			public void handle(final SockJSSocket sock) {
				sock.dataHandler(new Handler<Buffer>() {
					public void handle(Buffer data) {
						sock.writeBuffer(data);
					}
				});
			}
		});

		server.listen(8080);
	}

}
