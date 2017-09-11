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
	 * Spring Validation �̿��� ���
	 * initBinder()�� Validator�� ���� ������� �ʰ� Validator�� �����Ͽ� ������ �����Ѵ�.
	 */
	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String home1(Locale locale, @ModelAttribute User user, BindingResult resultUser, Model model) {
		logger.info("home1 call");

		logger.info("User Object Name is {}", user.getName());

		userValidator.validate(user, resultUser);
		if (resultUser.hasErrors()) {
            logger.info("User Validation ����");
        } else {
        	logger.info("User Validation ����");
        }

		return "home";
	}

	/**
	 * Spring Validation �̿��� ���
	 * initBinder()�� Validator�� ���� ����Ͽ� Controller���� ��� �޼��忡 Validator�� �����Ѵ�.
	 * BindingResult ��ü�� @Valid �ٷ� �ڿ� ���;� �Ѵ�. �׷��� ������ BindingException�� �߻��Ѵ�.
	 */
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home2(Locale locale, @ModelAttribute @Valid User user, BindingResult resultUser, Model model) {
		logger.info("home2 call");

		logger.info("User Object Name is {}", user.getName());

		if (resultUser.hasErrors()) {
            logger.info("User Validation ����");
        } else {
        	logger.info("User Validation ����");
        }

		return "home";
	}

	/**
	 * JSR 303 Validator�� �̿��� ���
	 * LocalValidatorFactoryBean �� �̿��� ������ �����Ѵ�. -> JSR-303�� ���� ����� �������� Validatoró�� ����� �� �ְ� ���ִ� ������ �����
	 * initBinder()�� Validator�� ���� ������� �ʰ� �ڵ������� �����Ѵ�.
	 * @Telephone��� Ŀ���� ������̼��� �߰��Ͽ� JSR 303�� �����ϵ��� ��!
	 */
	@RequestMapping(value = "/home3", method = RequestMethod.GET)
	public String home3(Locale locale, @ModelAttribute(value="user") @Valid UserJSR303 user, BindingResult resultUser, Model model) {
		logger.info("home3 call");

		logger.info("User Object Name is {}", user.getName());
		
		if (resultUser.hasErrors()) {
            logger.info("User Validation ����");
        } else {
        	logger.info("User Validation ����");
        }

		return "home3";
	}

	/**
	 * JSR 303 Validator�� �̿��� ���
	 * initBinder()�� Validator�� ���� ������� �ʰ� �ڵ������� �����Ѵ�.
	 */
	@RequestMapping(value = "/home4", method = RequestMethod.GET)
	public String home4(Locale locale, @ModelAttribute @Valid UserJSR303 user, BindingResult resultUser, Model model) {
		logger.info("home4 call");

		logger.info("User Object Name is {}", user.getName());
		
		if (resultUser.hasErrors()) {
            logger.info("User Validation ����");
        } else {
        	logger.info("User Validation ����");
        }

		return "home4";
	}
	
	/**
	 * Spring Validation �̿��� ���(2��)
	 * initBinder()�� Validator�� ���� ������� �ʰ� Validator�� �����Ͽ� ������ �����Ѵ�.
	 */
	@RequestMapping(value = "/home5", method = RequestMethod.GET)
	public String home5(Locale locale, @ModelAttribute User user, BindingResult resultUser, @ModelAttribute Company company, BindingResult resultCompany, Model model) {
		logger.info("home5 call");

		logger.info("User Object Name is {}", user.getName());
		logger.info("Company Object Name is {}", company.getName());

		userValidator.validate(user, resultUser);
		companyValidator.validate(company, resultCompany);
		if (resultUser.hasErrors()) {
            logger.info("User Validation ����");
        } else {
        	logger.info("User Validation ����");
        }

		if (resultCompany.hasErrors()) {
            logger.info("Company Validation ����");
        } else {
        	logger.info("Company Validation ����");
        }

		return "home5";
	}
	
	/**
	 * Spring Validation �̿��� ���(2��)
	 * initBinder()�� Validator�� ���� ����Ͽ� Controller���� ��� �޼��忡 Validator�� �����Ѵ�.
	 * BindingResult ��ü�� @Valid �ٷ� �ڿ� ���;� �Ѵ�. �׷��� ������ BindingException�� �߻��Ѵ�.
	 */
	@RequestMapping(value = "/home6", method = RequestMethod.GET)
	public String home6(Locale locale, @ModelAttribute @Valid User user, BindingResult resultUser, @ModelAttribute @Valid Company company, BindingResult resultCompany, Model model) {
		logger.info("home5 call");

		logger.info("User Object Name is {}", user.getName());
		logger.info("Company Object Name is {}", company.getName());

		if (resultUser.hasErrors()) {
            logger.info("User Validation ����");
        } else {
        	logger.info("User Validation ����");
        }

		if (resultCompany.hasErrors()) {
            logger.info("Company Validation ����");
        } else {
        	logger.info("Company Validation ����");
        }

		return "home5";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		System.out.println("InitBinder~" + dataBinder.getTarget());
		
		// WebDataBinder���� LocalValidatorFactoryBean Validator�� �⺻���� ��ϵǾ� �ִ�. 
	
		
		
		// home2 ȣ��ȶ� ���
//		dataBinder.setValidator(userValidator);

		// home6 ȣ��ȶ� ���
		// ���ε��Ǵ� ������ �ش� �Ķ���͸� ���Ͽ� �ش� Validator�� ����Ѵ�.
		// ���ÿ� 2�� �̻��� ����� �� ����.
//		Object obj = dataBinder.getTarget();
//		if (obj instanceof User)
//			dataBinder.setValidator(userValidator);
//		if (obj instanceof Company)
//			dataBinder.setValidator(companyValidator);
	}
	
}
