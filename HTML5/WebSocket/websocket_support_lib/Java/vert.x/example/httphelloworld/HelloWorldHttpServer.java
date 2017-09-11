package httphelloworld;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.deploy.Verticle;

public class HelloWorldHttpServer extends Verticle {

	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
		      public void handle(HttpServerRequest req) {
		        req.response.headers().put("Content-Type", "text/plain");
		        req.response.end("Hello World");
		      }
		    }).listen(8080);
	}

}
