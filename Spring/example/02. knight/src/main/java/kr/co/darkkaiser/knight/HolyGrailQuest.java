package kr.co.darkkaiser.knight;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HolyGrailQuest implements Quest, BeanPostProcessor {

	public HolyGrailQuest() {
		System.out.println("HolygrailQuest construct.");
	}

	public Object embark() throws QuestException {
		System.out.println("HolygrailQuest embark.");
		return new HolyGrail();
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("HolygrailQuest postProcessBeforeInitialization.");
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("HolygrailQuest postProcessAfterInitialization.");
		return bean;
	}
	
}
