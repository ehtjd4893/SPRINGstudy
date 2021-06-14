package quiz01;

public class Car {
	private String model;
	private int price;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}
	

	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
	}


	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public void info() {
		System.out.println("자동차모델: " + getModel()
			+ "\n자동차가격: " + getPrice() + "");
	}
	
}
