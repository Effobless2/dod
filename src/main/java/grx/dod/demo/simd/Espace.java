package grx.dod.demo.simd;

import java.util.ArrayList;
import java.util.List;

public class Espace {
	
	List<String> formes;
	
	public Espace(String ... types) {
		this.formes = new ArrayList<>();
		
		if (types!=null) {
			for (String type : types) {
				this.formes.add(type);
			}
		}
	}
	
	public static void main(String[] args) {
		Espace espace = new Espace("cercle@1", "rectangle@1", "rectangle@2", "cercle@2");
		
		// For classique
		System.out.println();
		System.out.println(" > Classique :");
		for (String type : espace.formes) {
			System.out.println("  - "+type);
		}
		
		// Avec SIMD (Single Instruction for Multiple Data) en Java Streams
		System.out.println();
		System.out.println(" > SIMD :");
		espace.formes.stream().forEach(
			type -> {
				System.out.println("  - "+type);
			}
		);
	}

}
