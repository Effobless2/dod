package grx.dod.demo.simd;

import java.util.Iterator;
import java.util.List;

public class Iteration {
	
	private static List<String> generation(Espace espace) {
		return (new Generation(espace)).output();
	}
	
	private static List<String> generation2(String chemin) throws Exception {
		return (new GenerationFichier(chemin)).output();
	}
	
	private static void iteration(List<String> formes) {
		Iterable<String> iterable = formes;
		Iterator<String> iterator = iterable.iterator();
		
		System.out.println();
		System.out.println("[ ITERATION ]");
		System.out.println();
		System.out.println(" > Iterable :");
		for (String type : iterable) {
			System.out.println("  - "+type);
		}
		
		System.out.println();
		System.out.println(" > Iterator :");
		String type;
		while (iterator.hasNext()) {
			type = iterator.next();
			System.out.println("  - "+type);
		}
	}
	
	private static void filter(List<String> formes) {
		System.out.println();
		System.out.println("[ FILTRE ]");
		// Filtre (cercle)
		System.out.println();
		System.out.println(" > Cercles :");
		Iterable<String> cerles = (new Filtre("cercle")).output(formes);
		for (String cercle : cerles) {
			System.out.println("  - "+cercle);
		}
		
		// Filtre (rectangle)
		System.out.println();
		System.out.println(" > Rectangles :");
		Iterable<String> rectangles = (new Filtre("rectangle")).output(formes);
		for (String rectangle : rectangles) {
			System.out.println("  - "+rectangle);
		}
		System.out.println();
	}
	
	private static void mutation(List<String> formes) {
		System.out.println();
		System.out.println("[ MUTATION ]");
		// Mutation en cercle
		System.out.println();
		System.out.println(" > En cercles :");
		Iterable<String> cerles = (new Mutation("cercle")).output(formes);
		for (String cercle : cerles) {
			System.out.println("  - "+cercle);
		}
		
		// Mutation en rectangle
		System.out.println();
		System.out.println(" > En rectangles :");
		Iterable<String> rectangles = (new Mutation("rectangle")).output(formes);
		for (String rectangle : rectangles) {
			System.out.println("  - "+rectangle);
		}
		System.out.println();
	}
	
	private static void consommation(List<String> formes) {
		System.out.println();
		System.out.println("[ CONSOMMATION ]");
		(new Consommation()).consume(formes);
	}
	
	
	public static void emission(List<String> formes) {
		System.out.println();
		System.out.println("[ EMISSION ]");
		// Filtre (cercle)
		System.out.println();
		Iterable<String> autres = (new Emission()).output(formes);
		for (String forme : autres) {
			System.out.println("  - "+forme);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Espace espace = new Espace(
			"cercle@1", "rectangle@2", "cercle@3", "rectangle@0", "cercle@5"
		);
		
		List<String> formes;

		formes = generation(espace);
		iteration(formes);
		
		formes = generation2("src\\main\\resources\\forme.txt");
		
		filter(formes);
		
		mutation(formes);
		
		emission(formes);
		
		consommation(formes);
		
	}

}
