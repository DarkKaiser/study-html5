package egovframework.let.etc.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AjaxExampleController {

	/*
	 * ObjectMapper 객체를 사용하는 방법
	 * ObjectMapper 객체를 사용하여도 HttpServletResponse 객체를 사용합니다. 하지만 ObjectMapper객체를 사용하게되면 문자열을 개발자가 작성할 필요없이 해당 객체를 Json형식의 문자열로 바꾸어주게 되어 개발자가 좀 더 간단하게 구현할 수 있습니다.
	 * ObjectMapper 객체를 사용 하기위해서는 구현에 앞서 jackson-databind 라이브러리를 프로젝트에 추가해야 합니다.
	 */
    @RequestMapping("/cop/example/ajaxAndJson1.do")
    public void ajaxAndJson1(HttpServletResponse response) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
        
        SocialPerson person = new SocialPerson();
        person.generateValue();
        
        try {
            response.getWriter().print(mapper.writeValueAsString(person));
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }

    /*
     * @ResponseBody 어노테이션을 사용하는 방법, 해당 객체가 자동으로 JSON 객체로 변환되어 반환된다.
     * jackson-mapper-asl 라이브러리가 필요함
     */
    @RequestMapping("/cop/example/ajaxAndJson2.do")
    public @ResponseBody HashMap<String, Object> ajaxAndJson2() throws Exception {
        SocialPerson person1 = new SocialPerson();
        person1.generateValue();
        
        SocialPerson person2 = new SocialPerson();
        person2.generateValue();
        
        HashMap<String, Object> m = new HashMap();
        m.put("person1", person1);
        m.put("person2", person2);
        
        return m;
    }
    
    @RequestMapping("/cop/example/ajaxAndJson3.do")
    public @ResponseBody SocialPerson ajaxAndJson3() throws Exception {
        SocialPerson person1 = new SocialPerson();
        person1.generateValue();
        
        /**
         * XML로 반환될때 배열같은 경우는 바로 반환될 수 없고, 배열을 래핑하는 클래스를 하나 만들어서 해당 클래스를 반환하여야 한다.
         * public class XmlData {
         *     @XmlElement(name = "items")
         *     private List<xmlchilddata> items;
         * 
         *     public List<xmlchilddata> getItems() {
         *         return items;
         *     }
         *  
         *     public void setItems(List<xmlchilddata> items) {
         *         this.items = items;
         *     }
         * }
         */
        
        return person1;
    }
}

/*
 * 스프링 3.1에서 @ResponseBody로 리턴시 한글이 깨질때 처리
<mvc:annotation-driven>
    <mvc:message-converters>
        <!-- @ResponseBody로 String 처리할때 한글처리 -->
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <property name="supportedMediaTypes">
                <list>
                    <value>text/html;charset=UTF-8</value>
                </list>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
 */