package quiz01;

public class Person {
	private String name;
	private Car car;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, Car car) {
		super();
		this.name = name;
		this.car = car;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public void info() {
		System.out.println("이름: " + name);
		car.info();
	}
}
