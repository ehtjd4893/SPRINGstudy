package quiz05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("quiz05.xml")
public class AppContext {
	
	@Bean(name="h1")
	public HoneyMoon h() {
		
		Person p1 = new Person();
		p1.setGender("M");
		p1.setName("hc");
		Person p2 = new Person();
		p2.setGender("F");
		p2.setName("hy");
		
		HoneyMoon h = new HoneyMoon("seoul", p1, p2);
		return h;
	}
}
