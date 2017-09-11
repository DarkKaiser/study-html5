package kr.co.darkkaiser;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HomeController() {
		System.out.println("HomeController");
	}
	
	/**
	 * http://localhost:8090/darkkaiser/test?age=1
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, @RequestParam int age, Level level, User user, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		logger.info("The Level is {}.", level);
		logger.info("The User Id is {}.", user.getId());
		logger.info("The User age is {}.", user.getAge());

		logger.info("The Age is {}.", age);	// UserAgePropertyEditor 적용안됨

		return "home";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		System.out.println("initBinder()");
		
		dataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
		
		// @RequestParam int age에 UserAgePropertyEditor를 사용하도록 할 수 있다.
//		dataBinder.registerCustomEditor(int.class, new UserAgePropertyEditor());

		// User 객체내의 프로퍼티 이름을 지정
		// @ModelAttributes 혹은 커맨드 오브젝트로 생성된 객체에만 적용된다.
		dataBinder.registerCustomEditor(int.class, "age", new UserAgePropertyEditor());
	}

}
/*
 * PropertyEditor 등록방법
  - 개별 컨트롤러에 적용
	@InitBinder
	public void initBinder(WebDataBinder binder) { 
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
	}
	
  - 전체 컨트롤러에 적용
	어플리케이션 전반에서 많이 사용되는 Custom PropertyEditor의 경우 WebBindingInitializer 이용

	WebBindingInitializer를 구현한 클래스 생성
	public class ClinicBindingInitializer implements WebBindingInitializer {
	    @Autowired
	    private Clinic clinic;
	
	    public void initBinder(WebDataBinder binder, WebRequest request) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	        binder.registerCustomEditor(PetType.class, new PetTypeEditor(this.clinic));
	    }
	}

	AnnotationMethodHandlerAdapter에 webBindingInitializer 속성을 이용해서 설정
	<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	    <property name="webBindingInitializer">
	        <bean class="org.springframework.samples.petclinic.web.ClinicBindingInitializer" />
	    </property>
	</bean>
	
  - 여러 개의 PropertyEditor를 여러 컨트롤러에 적용
	다수의 컨트롤러에서 자주 사용되는 여러 개의 Custom PropertyEditor 셋트로 관리할 경우 PropertyEditorRegistrar 이용

	PropertyEditorRegistrars를 구현한 클래스 생성

	package com.foo.editors.spring;
	public final class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
	    public void registerCustomEditors(PropertyEditorRegistry registry) {
	
	        // 새로운 PropertyEditor 인스턴스 생성
	        registry.registerCustomEditor(ExoticType.class, new ExoticTypeEditor());
	
	        // 필요한 Custom PropertyEditor들 추가
	    }
	}
	
	구현한 Custom PropertyEditorRegistrar를 Bean으로 등록
	<bean id="customPropertyEditorRegistrar" class="com.foo.editors.spring.CustomPropertyEditorRegistrar"/>

	@InitBinder를 이용하여 Controller에서 사용
	@Inject
	private final PropertyEditorRegistrar customPropertyEditorRegistrar;
						
	@InitBinder
	public void initBinder(WebDataBinder binder) { 
	    this.customPropertyEditorRegistrar.registerCustomEditors(binder);
	}
 */
