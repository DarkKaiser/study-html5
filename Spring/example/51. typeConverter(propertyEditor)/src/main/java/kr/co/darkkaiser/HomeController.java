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

		logger.info("The Age is {}.", age);	// UserAgePropertyEditor ����ȵ�

		return "home";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		System.out.println("initBinder()");
		
		dataBinder.registerCustomEditor(Level.class, new LevelPropertyEditor());
		
		// @RequestParam int age�� UserAgePropertyEditor�� ����ϵ��� �� �� �ִ�.
//		dataBinder.registerCustomEditor(int.class, new UserAgePropertyEditor());

		// User ��ü���� ������Ƽ �̸��� ����
		// @ModelAttributes Ȥ�� Ŀ�ǵ� ������Ʈ�� ������ ��ü���� ����ȴ�.
		dataBinder.registerCustomEditor(int.class, "age", new UserAgePropertyEditor());
	}

}
/*
 * PropertyEditor ��Ϲ��
  - ���� ��Ʈ�ѷ��� ����
	@InitBinder
	public void initBinder(WebDataBinder binder) { 
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
	}
	
  - ��ü ��Ʈ�ѷ��� ����
	���ø����̼� ���ݿ��� ���� ���Ǵ� Custom PropertyEditor�� ��� WebBindingInitializer �̿�

	WebBindingInitializer�� ������ Ŭ���� ����
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

	AnnotationMethodHandlerAdapter�� webBindingInitializer �Ӽ��� �̿��ؼ� ����
	<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	    <property name="webBindingInitializer">
	        <bean class="org.springframework.samples.petclinic.web.ClinicBindingInitializer" />
	    </property>
	</bean>
	
  - ���� ���� PropertyEditor�� ���� ��Ʈ�ѷ��� ����
	�ټ��� ��Ʈ�ѷ����� ���� ���Ǵ� ���� ���� Custom PropertyEditor ��Ʈ�� ������ ��� PropertyEditorRegistrar �̿�

	PropertyEditorRegistrars�� ������ Ŭ���� ����

	package com.foo.editors.spring;
	public final class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
	    public void registerCustomEditors(PropertyEditorRegistry registry) {
	
	        // ���ο� PropertyEditor �ν��Ͻ� ����
	        registry.registerCustomEditor(ExoticType.class, new ExoticTypeEditor());
	
	        // �ʿ��� Custom PropertyEditor�� �߰�
	    }
	}
	
	������ Custom PropertyEditorRegistrar�� Bean���� ���
	<bean id="customPropertyEditorRegistrar" class="com.foo.editors.spring.CustomPropertyEditorRegistrar"/>

	@InitBinder�� �̿��Ͽ� Controller���� ���
	@Inject
	private final PropertyEditorRegistrar customPropertyEditorRegistrar;
						
	@InitBinder
	public void initBinder(WebDataBinder binder) { 
	    this.customPropertyEditorRegistrar.registerCustomEditors(binder);
	}
 */
