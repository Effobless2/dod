package grx.dod.demo.simd;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Emission implements Pipeline {

	@Override
	public List<String> output(List<String> input) {
		// Emission de nouvelles formes à partir de l'entrée
		return input.stream().flatMap(
			new Function<String, Stream<String>>() {

				// Emmission :
				// -> cercle@1 => cercle@1-0
				// -> rectangle@3 => rectangle@3-0, rectangle@3-1, rectangle@3-2
				// -> rectangle@0 => (rien)
				@Override
				public Stream<String> apply(String forme) {
					String[] formeParts = forme.split("@");
					String type = formeParts[0];
					int number = Integer.valueOf(formeParts[1]);
					List<String> liste = new ArrayList<>();
					
					// On identifie le nombre de duplication
					for (int index=0 ; index<number ; index++) {
						liste.add(type+"@"+number+"-"+index);
					}
					
					return liste.stream();
				}
				
			}
		).collect(Collectors.toList());
	}

}
