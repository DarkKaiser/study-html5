package egovframework.let.etc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxExampleController {

    /*
     * JSONView를 이용한 방법
     */
    @RequestMapping("/cop/example/ajaxAndJson.do")
    public ModelAndView ajaxAndJson3() throws Exception {
    	ModelAndView mav = new ModelAndView();
    	
        SocialPerson person = new SocialPerson();
        person.generateValue();
        mav.addObject("person", person);
        mav.setViewName("jsonView");
        return mav;
    }
}
