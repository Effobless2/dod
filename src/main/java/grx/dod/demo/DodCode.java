package grx.dod.demo;

import java.util.ArrayList;
import java.util.List;

public abstract class DodCode {

	private DodCode() {}
	
	public static Object function(Object data) {
		// Evaluate something
		return (data!=null ? data.toString() : "...");
	}
	
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		
		list.add("League");
		list.add("of");
		list.add("Legends");
		
		for (Object entity : list) {
			System.out.println(" > "+function(entity));
		}
	}

}
