package grx.dod.demo.fortnite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipe {

	// Choix 1 de recherche de joueur :
	List<Joueur> membres;
	
	// Choix 2 de recherche de joueur :
	Map<String, Joueur> membres2;
	
	public Equipe(Joueur ... membres) {
		this.membres = new ArrayList<>();

		// Choix 2
		this.membres2 = new HashMap<>();
		
		if (membres!=null) {
			for (Joueur joueur : membres) {
				this.membres.add(joueur);
				this.membres2.put(joueur.skin, joueur);
			}
		}
	}
	
	public Joueur joueurChoix1(String skin) {
		for (Joueur joueur : membres) {
			if (joueur.skin.equals(skin)) {
				return joueur;
			}
		}
		// Non trouvé
		return null;
	}
	
	public Joueur joueurChoix2(String skin) {
		return membres2.get(skin);
	}
	
	public static void main(String[] args) {
		Equipe equipe = new Equipe(
			new Joueur("IRONMAN"),
			new Joueur("GROUT")
		);
		
		// Afficher les membres de l'équipe :
		System.out.println(" > Membre de l'équipe :");
		for (Joueur membre : equipe.membres) {
			System.out.println("   - "+membre.skin+" est vivant : "+membre.isActif());
		}
		
		// Est-ce que je peux accéder facilement si je veux savoir l'état de vie d'un membre
		// > IRONMAN est il en vie (avec le skin en entrée) ?
		
		String qui = "IRONMAN";
		Joueur joueur;
		
		System.out.println();
		System.out.println("[ Choix N° 1 ]");
		joueur = equipe.joueurChoix1(qui);
		if (joueur!=null) {
			System.out.println(" > Joueur '"+qui+"' est trouvé et vivant : "+joueur.isActif());
		} else {
			System.out.println(" > Joueur '"+qui+"' n'est pas trouvé");
		}
		
		System.out.println();
		System.out.println("[ Choix N° 2 ]");
		joueur = equipe.joueurChoix2(qui);
		if (joueur!=null) {
			System.out.println(" > Joueur '"+qui+"' est trouvé et vivant : "+joueur.isActif());
		} else {
			System.out.println(" > Joueur '"+qui+"' n'est pas trouvé");
		}
	}

}
