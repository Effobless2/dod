package grx.dod.demo.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import grx.dod.demo.simd.GenerationFichier;

public class Iteration2 {
	
	private static List<String> generation(String chemin) throws Exception {
		return (new GenerationFichier(chemin)).output();
	}

	public static void main(String[] args) throws Exception {
		List<String> formes = generation("src\\main\\resources\\forme.txt");
		
		int nbCoeurs = 8;
		ExecutorService processeur = Executors.newFixedThreadPool(nbCoeurs);
		
		List<Future<String>> taches = new ArrayList<>();
		String type = "rectangle";
		Action action = new Mutation2(type);
		
		for (String forme : formes) {
			taches.add(
				processeur.submit(
					new Tache(forme, action)
				)
			);
		}
		
		System.out.println();
		System.out.println("[ MUTATION ] (Parellel)");
		
		List<String> resultats = new ArrayList<>();
		for (Future<String> tache : taches) {
			// Evaluation de la tâche
			resultats.add(tache.get());
		}
		
		processeur.shutdown();
		
		System.out.println();
		System.out.println("[ RESULTATS ] (Parellel)");
		for (String resultat : resultats) {
			System.out.println("  - "+resultat);
		}
	}

}
