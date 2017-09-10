package egovframework.vertx.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.impl.DefaultVertx;

public abstract class DefaultEmbeddableVerticle implements EmbeddableVerticle {

	@Autowired
	protected BeanFactory beanFactory;

	@PostConstruct
	public void runVerticle() {
		Vertx vertx = null;
		try {
			vertx = beanFactory.getBean(Vertx.class);
		} catch (NoSuchBeanDefinitionException e) {
			if (getHost() != null) {
				if (getPort() != -1) {
					vertx = new DefaultVertx(getPort(), getHost());
				} else {
					vertx = new DefaultVertx(getHost());
				}
			} else {
				vertx = new DefaultVertx();
			}
		}

		beanFactory.getBean(this.getClass()).start(vertx);
	}

	public String getHost() {
		return null;
	}

	public int getPort() {
		return -1;
	}

}
