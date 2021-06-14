package ex02_xml;

public class Car {
	
	// field
	private String model;
	private Engine engine;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(String model, Engine engine) {
		super();
		this.model = model;
		this.engine = engine;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void info() {
		System.out.println("자동차모델: " + model);
		engine.info();
	}
}
