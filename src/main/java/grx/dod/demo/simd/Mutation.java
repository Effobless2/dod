package grx.dod.demo.simd;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mutation implements Pipeline {

	String type;
	
	public Mutation(String type) {
		this.type = type;
	}
	
	@Override
	public List<String> output(List<String> input) {
		// Mutation un type en un autre, ex : cercle ou rectangle
		final String mutationType = this.type;
		
		// Si mutation en cercle
		// -> cercle@1 => cercle@1#mut
		// -> rectangle@2 => cercle@2#mut
		return input.stream().map(
			new Function<String, String>() {
				@Override
				public String apply(String forme) {
					String[] formeParts = forme.split("@");
					String formeType = formeParts[0];
					String formeInfo = formeParts[1];
					
					if (!formeType.equals(mutationType)) {
						// On change de type et on ajoute de suffix
						return mutationType+"@"+formeInfo+"#mut";
					} else {
						// On ajoute seulement le suffix
						return forme+"#mut";
					}
				}
				
			}
		)
		.collect(Collectors.toList());
	}

}
