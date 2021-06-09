package quiz02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		String resourceLocations = "quiz02.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		MultiplicationTable mul = ctx.getBean("mul",MultiplicationTable.class);
		mul.info();
		
		ctx.close();
	}

}
