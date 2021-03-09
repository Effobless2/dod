package grx.dod.demo.simd;

import java.util.List;
import java.util.stream.Collectors;

public class Filtre implements Pipeline {

	String type;
	
	// Filtre d'un type
	public Filtre(String type) {
		this.type = type;
	}
	
	public List<String> output(List<String> input) {
		return input.stream()
		.filter(
			forme -> {
				// Filtre qui ne laisse passer qu'un type particulier : "cercle" ou "rectangle"
				return (forme.startsWith(type+"@"));
			}
		).collect(Collectors.toList());
	}

}
