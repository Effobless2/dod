package grx.dod.demo.simd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class GenerationFichier {

	String chemin;
	
	public GenerationFichier(String chemin) {
		this.chemin = chemin;
	}
	
	public List<String> output() throws Exception {
		File fichier = new File(chemin);
		
		try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
			return lecteur.lines().collect(Collectors.toList());
		}

	}

}
