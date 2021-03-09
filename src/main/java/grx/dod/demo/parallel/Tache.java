package grx.dod.demo.parallel;

import java.util.concurrent.Callable;

public class Tache implements Callable<String> {

	private String forme;
	private Action mapping;
	
	public Tache(String forme, Action transform) {
		this.forme = forme;
		this.mapping = transform;
	}
	
	@Override
	public String call() throws Exception {
		if (mapping!=null) {
			return mapping.transform(forme);
		} else {
			return forme;
		}
	}

}
