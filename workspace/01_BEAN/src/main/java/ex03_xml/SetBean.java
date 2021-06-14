package ex03_xml;

import java.util.Iterator;
import java.util.Set;

public class SetBean {
	
	// field
	private Set<String> set;
	
	// constructor
	public SetBean() {}
	
	// method
	public void info() {
		/*
 		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		*/
		for(String str: set) {
			System.out.println(str);
		}
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	
	
}
