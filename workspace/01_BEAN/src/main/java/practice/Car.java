package practice;

public class Car {
	private String name;
	private Engine engine;
	
	public Car() {}

	public String getName() {
		return name;
	}
	
	public void info() {
		System.out.println("자동차이름: " + name);
		engine.info();
	}

	public Car(String name, Engine engine) {
		super();
		this.name = name;
		this.engine = engine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	
}
