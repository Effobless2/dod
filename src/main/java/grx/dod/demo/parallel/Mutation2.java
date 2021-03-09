package grx.dod.demo.parallel;

public class Mutation2 implements Action {

	String type;
	
	public Mutation2(String type) {
		this.type = type;
	}
	
	@Override
	public String transform(String forme) {
		String[] formeParts = forme.split("@");
		String formeType = formeParts[0];
		String formeInfo = formeParts[1];
		int formeDelay = Integer.valueOf(formeInfo);
		
		System.out.println();
		System.out.println(" > ("+forme+") ...");
		try {
			String resultat;
			
			if (!formeType.equals(type)) {
				// On change de type et on ajoute de suffix
				// Imaginons que la mutation nécessite un effort suivant le poid dans la forme
				// On représente cet effort par le delay ci-dessous
				// cercle@1 -> il va attendre 1s
				Thread.sleep(formeDelay*1000);
				resultat = type+"@"+formeInfo+"#mut";
			} else {
				// On ajoute seulement le suffix
				resultat = forme+"#mut";
			}
			
			System.out.println(" > ("+forme+") => ("+resultat+")");
			return resultat;
		} catch (Exception failure) {
			return failure.toString();
		}
	}

}
