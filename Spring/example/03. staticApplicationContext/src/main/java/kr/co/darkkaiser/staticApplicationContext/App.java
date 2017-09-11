package kr.co.darkkaiser.staticApplicationContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class App {
	
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	StaticApplicationContext context = new StaticApplicationContext();
    	
    	
    	// Class 오브젝트를 이용한 등록 
    	context.registerSingleton("hello1", Hello.class);
    	Hello hello1 = context.getBean("hello1", Hello.class);
    	hello1.say();
    	
    	
    	// BeanDefinition을 이용한 등록
    	BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
    	helloDef.getPropertyValues().addPropertyValue("name", "Spring");
    	context.registerBeanDefinition("hello2", helloDef);
    	Hello hello2 = context.getBean("hello2", Hello.class);
    	hello2.say();
    }

}
