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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Level level, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		logger.info("The Level is {}.", level);
		
		return "home";
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// ConversionService를 이용할때는 initBinder 함수에 작업할 필요가 없음!
		System.out.println("initBinder()");
	}
	
}
/*
  - Converter
	Spring 3 Type Conversion 시스템에서 타입 변환을 실제 담당하는 객체는 Converter이다. Converter를 작성하려면 Spring에서 제공하는 org.springframework.core.convert.converter.Converter<S, T> 인터페이스를 구현하면 된다. Generics를 이용해서 Converter를 정의하므로 Run-time Type-Safety를 보장해준다.
	
	package org.springframework.core.convert.converter;
					
	public interface Converter<S, T> {
	    T convert(S source);    
	}
	Converter 인터페이스에서 구현해야 할 메소드는 convert() 메소드 하나이다. 즉 PropertyEditor와는 달리 단방향 타입 변환만 제공한다. 'S'에는 변환 전인 Source 타입을 명시하고, 'T'에는 변환 할 Target 타입을 명시한다. Converter 객체가 변환과 관련된 상태 값을 저장하지 않기 때문에 Converter를 Singlton Bean으로 등록하여 Multi-thread 환경에서도 안전하게 사용할 수 있다.
	
	다음은 Converter를 구현한 예제 코드이다.
	
	final class StringToInteger implements Converter<String, Integer> {
	
	    public Integer convert(String source) {
	        return Integer.valueOf(source);
	    }   
	}

  - ConverterFactory
	클래스 계층으로 묶을 수 있는 java.lang.Number나 java.lang.Enum과 같은 타입 변환 로직을 한 곳에서 관리하고자 하는 경우, 아래의 ConverterFactory 인터페이스의 구현클래스를 작성하면 된다..
	
	package org.springframework.core.convert.converter;
					
	public interface ConverterFactory<S, R> {
	    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
	}
	여기서 'S'에는 변환 전인 Source 타입을 명시하고, 'R'에는 변환할 클래스들의 상위 베이스 클래스를 명시한다. 그리고 getConverter() 메소드를 구현하는데, 이 때, 'T'는 'R'의 하위 클래스 타입이 될 것이다.
	다음은 ConverterFactory의 구현클래스 예이다. (Spring에서 제공하는 StringToNumberConverterFactory이다.)
	
	final class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
	
	    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
	        return new StringToNumber<T>(targetType);
	    }
	
	    private static final class StringToNumber<T extends Number> implements Converter<String, T> {
	        private final Class<T> targetType;
	
	        public StringToNumber(Class<T> targetType) {
	            this.targetType = targetType;
	        }
	
	        public T convert(String source) {
	            if (source.length() == 0) {
	                return null;
	            }
	            return NumberUtils.parseNumber(source, this.targetType);
	        }
	    }
	}
	
  - GenericConverter
	또한, 두 가지 이상의 타입 변환을 수행하는 Converter를 개발하고자 하는 경우에는 GenericConverter 인터페이스를 구현하면 된다. 여러개의 Source/Target 타입을 지정할 수 있고, Source나 Target 객체의 Field Context(Field에 적용된 Annotation이나 Generics 등을 포함한 Field와 관련된 모든 정보)를 사용할 수 있기 때문에 유연한 Converter이긴 하지만, 그만큼 구현하기가 어렵고 복잡하다. 일반적으로 Converter나 ConverterFactory만으로 커버할 수 있는 기본적인 변환에는 사용하지 않는 것이 좋다.
	
	package org.springframework.core.convert.converter;
						
	public interface GenericConverter {
	    public Set<ConvertiblePair> getConvertibleTypes();
	    
	    Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType);
	}
	실제 GenericConverter 구현 모습을 보고 싶다면, Spring에서 제공하는 Built-in Converter 중 하나인 org.springframework.core.convert.support.ArrayToCollectionConverter 코드에서 확인할 수 있다.

  - ConditionalGenericConverter
	만약 어떤 조건을 만족하는 경우에만 변환을 수행하는 Converter를 개발할 경우는 ConditionalGenericConverter 인터페이스 구현클래스를 작성한다. 참조할 수 있는 구현 예는 Spring의 org.springframework.core.convert.support.IdToEntityConverter 이다.
 */
