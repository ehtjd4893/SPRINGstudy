package quiz03;

import java.util.List;

public class Exam {
	private List<Integer> scores;
	private double average;
	private char grade;
	
	public Exam() {
		// TODO Auto-generated constructor stub
	}
	
	// method
	public void info() {
		setAverage();
		setGrade();
		System.out.println("scores: " + scores.toString());
		System.out.println("average: " + average);
		System.out.println("grade: " + grade);
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public void setAverage() {
		average = 0;
		for(int i = 0; i < scores.size();i++) {
			average += scores.get(i);
		}
		average /= scores.size();
	}
	
	public double getAverage() {
		return average;
	}
	public void setGrade() {
		double avg = getAverage();
		grade = 'F';
		if(avg >= 90) grade = 'A';
		else if(avg >= 80) grade = 'B';
		else if(avg >= 70) grade = 'C';
		else if(avg >= 60) grade = 'D';
	}
	public char getGrade() {
		return grade;
	}
	
}
