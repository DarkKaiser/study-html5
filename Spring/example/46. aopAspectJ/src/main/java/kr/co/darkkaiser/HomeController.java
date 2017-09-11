package kr.co.darkkaiser;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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
	SimpleService simpleService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		simpleService.printNameId();
		
	    System.out.println("---------------");
	    try{
	        simpleService.checkName();
	    } catch(Exception e){
	        System.out.println("SimpleService checkName() : Exception thrown..");
	    }
	    System.out.println("---------------");
	    
		return "home";
	}
	
}
