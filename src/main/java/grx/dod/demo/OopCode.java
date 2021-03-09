package grx.dod.demo;

import java.util.ArrayList;
import java.util.List;

public class OopCode {

	private Object data;
	
	public OopCode(Object data) {
		this.data = data;
	}
	
	public String function() {
		// Evaluate something
		return (data!=null ? data.toString() : "...");
	}
	
	public static void main(String[] args) {
		List<OopCode> list = new ArrayList<>();
		
		list.add(new OopCode("League"));
		list.add(new OopCode("of"));
		list.add(new OopCode("Legends"));
		
		for (OopCode entity : list) {
			System.out.println(" > "+entity.function());
		}
	}

}
