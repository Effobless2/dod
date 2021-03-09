package grx.dod.demo.coc;

public class Jeu {

	private static void animer(Form[] tableauDeFormes) {
		for (Form forme : tableauDeFormes) {
			System.out.println(" > "+forme.type+" : "+forme.x+", "+forme.y);
		}
	}
	
	private static void afficher(String[][] tableauDAttributs) {
		String[] ligne;
		String col;

		for (int x=0 ; x<5 ; x++) {
			for (int y=0 ; y<5 ; y++) {
				ligne = tableauDAttributs[x];
				col = (ligne!=null ? ligne[y] : "<rien>");
				col = (col!=null ? col : "<rien>");
				System.out.println(" > "+col+" : "+x+", "+y);
			}
		}
	}
	
	public static void main(String[] args) {
		// Je n'ai que 2 objets sur le terrain
		Form[] tableauDeFormes = new Form[] {
			new Form("cabane", 0, 1),
			new Form("tesla", 4, 3)
		};
		
		String[][] tableauDAttributs = new String[5][5];
		for (Form forme : tableauDeFormes) {
			tableauDAttributs[forme.x][forme.y] = forme.type;
		}
		
		// Animer les formes :
		System.out.println(" > Animation :");
		animer(tableauDeFormes);
		System.out.println();
		
		// Afficher le terrain :
		System.out.println(" > Affichage du terrain :");
		afficher(tableauDAttributs);
	}

}
