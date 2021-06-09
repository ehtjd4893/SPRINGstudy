package ex03_xml;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context3-1.xml");
		ListBean listBean = ctx.getBean("listBean",ListBean.class);
		listBean.info();
		
		ctx.close();
	}

}
