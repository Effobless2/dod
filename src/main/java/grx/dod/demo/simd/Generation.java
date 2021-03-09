package grx.dod.demo.simd;

import java.util.List;

public class Generation {

	Espace espace;
	
	public Generation(Espace espace) {
		this.espace = espace;
	}
	
	public List<String> output() {
		return espace.formes;
	}

}
