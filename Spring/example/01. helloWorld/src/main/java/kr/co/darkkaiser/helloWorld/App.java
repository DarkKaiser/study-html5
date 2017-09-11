package kr.co.darkkaiser.helloWorld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	// BeanFactory를 이용한 방법
//    	BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/main/resources/hello.xml"));
//    	GreetingService greetingService = (GreetingService)factory.getBean("greetingService");

    	// ApplicationContext를 이용한 방법
    	ApplicationContext context = new ClassPathXmlApplicationContext("hello.xml");
    	GreetingService greetingService = (GreetingService)context.getBean("greetingService");
    	
    	greetingService.sayGreeting();
    }

}
