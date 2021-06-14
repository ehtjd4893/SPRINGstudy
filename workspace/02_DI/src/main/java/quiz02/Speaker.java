package quiz02;

public class Speaker {

	// field
	private int vol;
	
	// constructor
	
	// method
	public void volUp() {
		vol++;
		if (vol > 100) vol = 100;
		System.out.println("현재 볼륨: " + vol);
	}
	public void volDown() {
		vol--;
		if (vol < 0) vol = 0;
		System.out.println("현재 볼륨: " + vol);
	}
	
}