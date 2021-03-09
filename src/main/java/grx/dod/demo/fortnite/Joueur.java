package grx.dod.demo.fortnite;

public class Joueur {

	String skin; // IRONMAN, ...
	
	int niveauVie; // Commence à 100
	int niveauKO; // Au début du KO, on commence à 100 (mais ce niveau descent progressivement)
	
	// boolean actif ? NON, pas besoin !
	// boolean KO ? NON, pas besoin !
	// boolean inactif ? NON, pas besoin !
	
	// Etat du joueur en tant qu'enumeration : Actif (En Vie), KO, Inactif
	// Etat etat ? NON, pas besoin !
	
	public Joueur(String skin) {
		this.skin = skin;
		this.niveauVie = 100;
		this.niveauKO = 100;
	}
	
	public int infligerDegat(int niveau) {
		if (this.niveauVie>=niveau) {
			this.niveauVie = this.niveauVie - niveau;
		} else {
			this.niveauVie = 0;
		}
		return this.niveauVie;
	}
	
	public boolean isActif() {
		return (this.niveauVie>0);
	}
	
	public boolean isKO() {
		return (this.niveauVie==0 && this.niveauKO>0);
	}
	
	public boolean isInactif() {
		return (this.niveauVie==0 && this.niveauKO==0);
	}
	
	// Est-ce qu'il fallait avoir cette énumeration ?
	public static enum Etat {
		ACTIF,
		KO,
		INACTIF;
	}

}
