package quiz01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Quiz01MainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:quiz01.xml");
		Car car = ctx.getBean("car1", Car.class);
		Person person = ctx.getBean("person1",Person.class);
		
		person.info();
		ctx.close();
	}

}
