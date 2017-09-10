package egovframework.vertx.common;

import org.vertx.java.core.Vertx;

public interface EmbeddableVerticle {

	void start(Vertx vertx);

    String getHost();

    int getPort();

}
