package kr.co.darkkaiser;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private UserValidator userValidator;

	@Autowired
    private CompanyValidator companyValidator;

	/**
	 * Spring Validation 이용한 방법
	 * initBinder()에 Validator을 따로 등록하지 않고 Validator을 생성하여 검증을 수행한다.
	 */
	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String home1(Locale locale, @ModelAttribute User user, BindingResult resultUser, Model model) {
		logger.info("home1 call");

		logger.info("User Object Name is {}", user.getName());

		userValidator.validate(user, resultUser);
		if (resultUser.hasErrors()) {
            logger.info("User Validation 실패");
        } else {
        	logger.info("User Validation 성공");
        }

		return "home";
	}

	/**
	 * Spring Validation 이용한 방법
	 * initBinder()에 Validator을 따로 등록하여 Controller내의 모든 메서드에 Validator를 적용한다.
	 * BindingResult 객체는 @Valid 바로 뒤에 나와야 한다. 그렇지 않으면 BindingException이 발생한다.
	 */
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home2(Locale locale, @ModelAttribute @Valid User user, BindingResult resultUser, Model model) {
		logger.info("home2 call");

		logger.info("User Object Name is {}", user.getName());

		if (resultUser.hasErrors()) {
            logger.info("User Validation 실패");
        } else {
        	logger.info("User Validation 성공");
        }

		return "home";
	}

	/**
	 * JSR 303 Validator을 이용한 방법
	 * LocalValidatorFactoryBean 를 이용해 검증을 수행한다. -> JSR-303의 검증 기능을 스프링의 Validator처럼 사용할 수 있게 해주는 일종의 어댑터
	 * initBinder()에 Validator을 따로 등록하지 않고 자동검증을 수행한다.
	 * @Telephone라는 커스텀 어노테이션을 추가하여 JSR 303을 수행하도록 함!
	 */
	@RequestMapping(value = "/home3", method = RequestMethod.GET)
	public String home3(Locale locale, @ModelAttribute(value="user") @Valid UserJSR303 user, BindingResult resultUser, Model model) {
		logger.info("home3 call");

		logger.info("User Object Name is {}", user.getName());
		
		if (resultUser.hasErrors()) {
            logger.info("User Validation 실패");
        } else {
        	logger.info("User Validation 성공");
        }

		return "home3";
	}

	/**
	 * JSR 303 Validator을 이용한 방법
	 * initBinder()에 Validator을 따로 등록하지 않고 자동검증을 수행한다.
	 */
	@RequestMapping(value = "/home4", method = RequestMethod.GET)
	public String home4(Locale locale, @ModelAttribute @Valid UserJSR303 user, BindingResult resultUser, Model model) {
		logger.info("home4 call");

		logger.info("User Object Name is {}", user.getName());
		
		if (resultUser.hasErrors()) {
            logger.info("User Validation 실패");
        } else {
        	logger.info("User Validation 성공");
        }

		return "home4";
	}
	
	/**
	 * Spring Validation 이용한 방법(2개)
	 * initBinder()에 Validator을 따로 등록하지 않고 Validator을 생성하여 검증을 수행한다.
	 */
	@RequestMapping(value = "/home5", method = RequestMethod.GET)
	public String home5(Locale locale, @ModelAttribute User user, BindingResult resultUser, @ModelAttribute Company company, BindingResult resultCompany, Model model) {
		logger.info("home5 call");

		logger.info("User Object Name is {}", user.getName());
		logger.info("Company Object Name is {}", company.getName());

		userValidator.validate(user, resultUser);
		companyValidator.validate(company, resultCompany);
		if (resultUser.hasErrors()) {
            logger.info("User Validation 실패");
        } else {
        	logger.info("User Validation 성공");
        }

		if (resultCompany.hasErrors()) {
            logger.info("Company Validation 실패");
        } else {
        	logger.info("Company Validation 성공");
        }

		return "home5";
	}
	
	/**
	 * Spring Validation 이용한 방법(2개)
	 * initBinder()에 Validator을 따로 등록하여 Controller내의 모든 메서드에 Validator를 적용한다.
	 * BindingResult 객체는 @Valid 바로 뒤에 나와야 한다. 그렇지 않으면 BindingException이 발생한다.
	 */
	@RequestMapping(value = "/home6", method = RequestMethod.GET)
	public String home6(Locale locale, @ModelAttribute @Valid User user, BindingResult resultUser, @ModelAttribute @Valid Company company, BindingResult resultCompany, Model model) {
		logger.info("home5 call");

		logger.info("User Object Name is {}", user.getName());
		logger.info("Company Object Name is {}", company.getName());

		if (resultUser.hasErrors()) {
            logger.info("User Validation 실패");
        } else {
        	logger.info("User Validation 성공");
        }

		if (resultCompany.hasErrors()) {
            logger.info("Company Validation 실패");
        } else {
        	logger.info("Company Validation 성공");
        }

		return "home5";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		System.out.println("InitBinder~" + dataBinder.getTarget());
		
		// WebDataBinder에는 LocalValidatorFactoryBean Validator가 기본으로 등록되어 있다. 
	
		
		
		// home2 호출된때 사용
//		dataBinder.setValidator(userValidator);

		// home6 호출된때 사용
		// 바인딩되는 시점의 해당 파라메터를 구하여 해당 Validator를 등록한다.
		// 동시에 2개 이상은 등록할 수 없다.
//		Object obj = dataBinder.getTarget();
//		if (obj instanceof User)
//			dataBinder.setValidator(userValidator);
//		if (obj instanceof Company)
//			dataBinder.setValidator(companyValidator);
	}
	
}
