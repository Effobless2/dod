package grx.dod.demo.coc;

public class Affichage {

	public static void main(String[] args) {
		// Terrain de 5 x 5 : 25 cases
		String[][] formes = new String[5][5];
		
		// Indiquer les 2 objets :
		// x, y : dimension de tableau
		// type : contenu dans le tableau
		formes[0][1] = "cabane";
		formes[4][3] = "tesla";
		
		String[] ligne;
		String col;
		
		// Boucle sur les 25 cases :
		// 0, 0 : <rien>
		// 0, 1 : cabane
		// 0, 2 : <rien>
		// ...  : <rien>
		// 4, 3 : tesla
		// ...  : <rien> 
		for (int x=0 ; x<5 ; x++) {
			for (int y=0 ; y<5 ; y++) {
				ligne = formes[x];
				col = (ligne!=null ? ligne[y] : "<rien>");
				col = (col!=null ? col : "<rien>");
				System.out.println(" > "+col+" : "+x+", "+y);
			}
		}
	}

}
