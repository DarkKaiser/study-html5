package kr.co.darkkaiser.annotationConfigApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("createUserDao", UserDao.class);
        
        dao.say();
    }

}
