package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	Position origine;
	int longueur;
	int hauteur;

	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.origine = new Position(x,y);
	}
	
	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}

	public boolean occupeLaPosition(int x, int y) {
		if (estAbscisseCouverte(x) && estOrdonneeCouverte(y))
			return true;
		
	     return false;
	}

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusHaute()<=y) && (y<=ordonneLaPlusBasse());
	}

	private int ordonneLaPlusBasse() {
		return this.origine.ordonnee();
	}

	private int ordonneeLaPlusHaute() {
		return ordonneLaPlusBasse()-this.hauteur+1;
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche()<=x) && (x<=abscisseLaPlusADroite());
	}

	private int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}

	public int abscisseLaPlusADroite() {
		return abscisseLaPlusAGauche()+this.longueur-1;
	}

	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse()+1);
	}
	
	public int abscisse() {
        return abscisseLaPlusAGauche();
	}

	public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse()-1);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
	}
	
}
