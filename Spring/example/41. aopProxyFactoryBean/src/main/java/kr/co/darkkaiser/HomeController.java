package kr.co.darkkaiser;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	ProxyFactoryBean bean; 
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		/////////////////////////////////////////////////////////////////////////////////////
		// 자바 코드를 이용한 AOP
		/////////////////////////////////////////////////////////////////////////////////////
		/*
		ProxyFactoryBean bean = new ProxyFactoryBean();
		bean.setTarget(new HelloTarget());
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("sayH*");
		bean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		
		Hello proxiedHello = (Hello)bean.getObject();
		
		System.out.println(proxiedHello.sayHello("Toby"));
		System.out.println(proxiedHello.sayThankYou("Toby"));
		*/
		
		/////////////////////////////////////////////////////////////////////////////////////
		// XML 설정 파일을 이용한 AOP
		/////////////////////////////////////////////////////////////////////////////////////
		Hello proxiedHello = (Hello)bean.getObject();
		
		System.out.println(proxiedHello.sayHello("Toby"));
		System.out.println(proxiedHello.sayThankYou("Toby"));
		
		return "home";
	}
	
}
