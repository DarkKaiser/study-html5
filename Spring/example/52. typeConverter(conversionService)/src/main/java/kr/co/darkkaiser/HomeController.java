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
		// ConversionService�� �̿��Ҷ��� initBinder �Լ��� �۾��� �ʿ䰡 ����!
		System.out.println("initBinder()");
	}
	
}
/*
  - Converter
	Spring 3 Type Conversion �ý��ۿ��� Ÿ�� ��ȯ�� ���� ����ϴ� ��ü�� Converter�̴�. Converter�� �ۼ��Ϸ��� Spring���� �����ϴ� org.springframework.core.convert.converter.Converter<S, T> �������̽��� �����ϸ� �ȴ�. Generics�� �̿��ؼ� Converter�� �����ϹǷ� Run-time Type-Safety�� �������ش�.
	
	package org.springframework.core.convert.converter;
					
	public interface Converter<S, T> {
	    T convert(S source);    
	}
	Converter �������̽����� �����ؾ� �� �޼ҵ�� convert() �޼ҵ� �ϳ��̴�. �� PropertyEditor�ʹ� �޸� �ܹ��� Ÿ�� ��ȯ�� �����Ѵ�. 'S'���� ��ȯ ���� Source Ÿ���� ����ϰ�, 'T'���� ��ȯ �� Target Ÿ���� ����Ѵ�. Converter ��ü�� ��ȯ�� ���õ� ���� ���� �������� �ʱ� ������ Converter�� Singlton Bean���� ����Ͽ� Multi-thread ȯ�濡���� �����ϰ� ����� �� �ִ�.
	
	������ Converter�� ������ ���� �ڵ��̴�.
	
	final class StringToInteger implements Converter<String, Integer> {
	
	    public Integer convert(String source) {
	        return Integer.valueOf(source);
	    }   
	}

  - ConverterFactory
	Ŭ���� �������� ���� �� �ִ� java.lang.Number�� java.lang.Enum�� ���� Ÿ�� ��ȯ ������ �� ������ �����ϰ��� �ϴ� ���, �Ʒ��� ConverterFactory �������̽��� ����Ŭ������ �ۼ��ϸ� �ȴ�..
	
	package org.springframework.core.convert.converter;
					
	public interface ConverterFactory<S, R> {
	    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
	}
	���⼭ 'S'���� ��ȯ ���� Source Ÿ���� ����ϰ�, 'R'���� ��ȯ�� Ŭ�������� ���� ���̽� Ŭ������ ����Ѵ�. �׸��� getConverter() �޼ҵ带 �����ϴµ�, �� ��, 'T'�� 'R'�� ���� Ŭ���� Ÿ���� �� ���̴�.
	������ ConverterFactory�� ����Ŭ���� ���̴�. (Spring���� �����ϴ� StringToNumberConverterFactory�̴�.)
	
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
	����, �� ���� �̻��� Ÿ�� ��ȯ�� �����ϴ� Converter�� �����ϰ��� �ϴ� ��쿡�� GenericConverter �������̽��� �����ϸ� �ȴ�. �������� Source/Target Ÿ���� ������ �� �ְ�, Source�� Target ��ü�� Field Context(Field�� ����� Annotation�̳� Generics ���� ������ Field�� ���õ� ��� ����)�� ����� �� �ֱ� ������ ������ Converter�̱� ������, �׸�ŭ �����ϱⰡ ��ư� �����ϴ�. �Ϲ������� Converter�� ConverterFactory������ Ŀ���� �� �ִ� �⺻���� ��ȯ���� ������� �ʴ� ���� ����.
	
	package org.springframework.core.convert.converter;
						
	public interface GenericConverter {
	    public Set<ConvertiblePair> getConvertibleTypes();
	    
	    Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType);
	}
	���� GenericConverter ���� ����� ���� �ʹٸ�, Spring���� �����ϴ� Built-in Converter �� �ϳ��� org.springframework.core.convert.support.ArrayToCollectionConverter �ڵ忡�� Ȯ���� �� �ִ�.

  - ConditionalGenericConverter
	���� � ������ �����ϴ� ��쿡�� ��ȯ�� �����ϴ� Converter�� ������ ���� ConditionalGenericConverter �������̽� ����Ŭ������ �ۼ��Ѵ�. ������ �� �ִ� ���� ���� Spring�� org.springframework.core.convert.support.IdToEntityConverter �̴�.
 */
