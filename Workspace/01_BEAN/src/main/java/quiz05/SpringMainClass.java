package quiz05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		HoneyMoon h1 = ctx.getBean("h1", HoneyMoon.class);
		HoneyMoon h2 = ctx.getBean("h2", HoneyMoon.class);
		h1.info();
		h2.info();
		
		ctx.close();
		
	}

}
