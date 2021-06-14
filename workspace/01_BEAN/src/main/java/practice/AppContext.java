package practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	
public class AppContext {
	@Bean(name="engine")
	public Engine e1(){
		Engine e = new Engine();
		e.setModel("엔진");
		return e;
	}
	
	@Bean(name="c1")
	public Car xcx() {
		return new Car("도성쓰", e1());
	}
}
