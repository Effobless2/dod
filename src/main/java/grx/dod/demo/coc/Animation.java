package grx.dod.demo.coc;

public class Animation {

	// Tableau d'objets
	public static void main(String[] args) {
		// Je n'ai que 2 objets sur le terrain
		Form[] formes = new Form[] {
			new Form("cabane", 0, 1),
			new Form("tesla", 4, 3)
		};
		
		// Terrain :
		// Lignes 	: 0 à 4 (5 lignes)
		// Colonnes	: 0 à 4 (5 colonnes)
		// => 5 x 5 : 25 cases
		
		for (Form forme : formes) {
			System.out.println(" > "+forme.type+" : "+forme.x+", "+forme.y);
		}
		
		// Boucle sur les 25 cases :
		// 0, 0 : <rien>
		// 0, 1 : cabane
		// 0, 2 : <rien>
		// ...  : <rien>
		// 4, 3 : tesla
		// ...  : <rien> 
		for (int ligne=0 ; ligne < 5 ; ligne++) {
			for (int col=0 ; col < 5 ; col++) {
				// ???
			}
		}
	}
	
}
