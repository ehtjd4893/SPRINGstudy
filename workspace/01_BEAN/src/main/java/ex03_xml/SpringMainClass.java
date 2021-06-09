package ex03_xml;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context3-1.xml", "app-context3-2.xml", "app-context3-3.xml");
		ListBean listBean = ctx.getBean("listBean",ListBean.class);
		listBean.info();
		
		SetBean setBean = ctx.getBean("setBean", SetBean.class);
		setBean.info();
		
		MapBean mapBean = ctx.getBean("mapBean", MapBean.class);
		mapBean.info();
		ctx.close();
	}

}
