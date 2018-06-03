package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	private Sprite premierSprite;
	private Sprite deuxiemeSprite;
	
	public Collision(Sprite premierSprite, Sprite deuxiemeSprite) {
		this.premierSprite = premierSprite;
		this.deuxiemeSprite = deuxiemeSprite;
	}

	public void detecterCollision(SpaceInvaders spaceInvaders) {
		if (touche()) {
			spaceInvaders.supprimerMissile();
			spaceInvaders.supprimerEnvahisseur();
		}
	}

	private boolean touche() {
		return coinSuperieurGaucheTouche() || coinInferieurGaucheTouche() || coinInferieurDroitTouche() || coinSuperieurDroitTouche();
	}

	private boolean coinSuperieurDroitTouche() {
		return abscisseLaPlusADroiteTouche() && ordonneeLaPlusHauteTouche();
	}

	private boolean coinInferieurDroitTouche() {
		return abscisseLaPlusADroiteTouche() && ordonneeLaPlusBasseTouche();
	}

	private boolean coinInferieurGaucheTouche() {
		return abscisseLaPlusAGaucheTouche() && ordonneeLaPlusBasseTouche();
	}

	private boolean coinSuperieurGaucheTouche() {
		return abscisseLaPlusAGaucheTouche() && ordonneeLaPlusHauteTouche();
	}

	private boolean ordonneeLaPlusBasseTouche() {
		return deuxiemeSprite.ordonneeLaPlusBasse() <= premierSprite.ordonneeLaPlusBasse()
				&& deuxiemeSprite.ordonneeLaPlusBasse() >= premierSprite.ordonneeLaPlusHaute();
	}

	private boolean abscisseLaPlusAGaucheTouche() {
		return deuxiemeSprite.abscisseLaPlusAGauche() >= premierSprite.abscisseLaPlusAGauche()
				&& deuxiemeSprite.abscisseLaPlusAGauche() <= premierSprite.abscisseLaPlusADroite();
	}

	private boolean abscisseLaPlusADroiteTouche() {
		return deuxiemeSprite.abscisseLaPlusADroite() >= premierSprite.abscisseLaPlusAGauche()
				&& deuxiemeSprite.abscisseLaPlusADroite() <= premierSprite.abscisseLaPlusADroite();
	}

	private boolean ordonneeLaPlusHauteTouche() {
		return deuxiemeSprite.ordonneeLaPlusHaute() <= premierSprite.ordonneeLaPlusBasse()
				&& deuxiemeSprite.ordonneeLaPlusHaute() >= premierSprite.ordonneeLaPlusHaute();
	}

}