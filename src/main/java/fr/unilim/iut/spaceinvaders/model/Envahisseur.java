package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {
	
	boolean doitSeDeplacerVersLaDroite;
	
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
		
		this.doitSeDeplacerVersLaDroite = true;
	}
	
	public boolean doitSeDeplacerVersLaDroite() {
		return this.doitSeDeplacerVersLaDroite;
	}

	public void changementDeDirection() {
		this.doitSeDeplacerVersLaDroite = !this.doitSeDeplacerVersLaDroite;
	}
}
