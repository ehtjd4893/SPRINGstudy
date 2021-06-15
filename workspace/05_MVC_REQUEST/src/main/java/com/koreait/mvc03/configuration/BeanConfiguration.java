package com.koreait.mvc03.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.mvc03.dto.Person;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public Person person1() {
		Person m = new Person();
		m.setName("버터");
		m.setAge(35);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("게임");
		hobbies.add("도박");
		m.setHobbies(hobbies);
		return m;
	}
	
	@Bean
	public Person person2() {
		Person m = new Person();
		m.setName("공주");
		m.setAge(35);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("화장");
		hobbies.add("소풍");
		m.setHobbies(hobbies);
		return m;
	}
}
