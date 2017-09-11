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
	 * Simply selects the home view to render by returning its name.
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
