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
		return toucheEnAbscisse() || toucheEnOrdonneeAGauche() || toucheEnOrdonneADroite();
	}

	private boolean toucheEnOrdonneADroite() {
		return memeAbscisseToucheADroite() && ordonneeLaPlusHauteTouche() || memeAbscisseToucheADroite() && ordonneeLaPlusBasseTouche();
	}

	private boolean toucheEnOrdonneeAGauche() {
		return memeAbscisseToucheAGauche() && ordonneeLaPlusHauteTouche() || memeAbscisseToucheAGauche() && ordonneeLaPlusBasseTouche();
	}

	private boolean ordonneeLaPlusBasseTouche() {
		return deuxiemeSprite.ordonneeLaPlusBasse() <= premierSprite.ordonneeLaPlusBasse()
				&& deuxiemeSprite.ordonneeLaPlusBasse() >= premierSprite.ordonneeLaPlusHaute();
	}

	private boolean memeAbscisseToucheADroite() {
		return deuxiemeSprite.abscisseLaPlusADroite() == premierSprite.abscisseLaPlusAGauche();
	}

	private boolean memeAbscisseToucheAGauche() {
		return deuxiemeSprite.abscisseLaPlusAGauche() == premierSprite.abscisseLaPlusADroite();
	}

	private boolean toucheEnAbscisse() {
		return abscisseLaPlusAGaucheTouche() || abscisseLaPlusADroiteTouche();
	}

	private boolean abscisseLaPlusADroiteTouche() {
		return deuxiemeSprite.abscisseLaPlusADroite() >= premierSprite.abscisseLaPlusAGauche() 
				&& deuxiemeSprite.abscisseLaPlusADroite() <= premierSprite.abscisseLaPlusADroite()
				&& memeOrdonnee();
	}

	private boolean abscisseLaPlusAGaucheTouche() {
		return deuxiemeSprite.abscisseLaPlusAGauche() >= premierSprite.abscisseLaPlusAGauche() 
				&& deuxiemeSprite.abscisseLaPlusAGauche() <= premierSprite.abscisseLaPlusADroite()
				&& memeOrdonnee();
	}
	
	private boolean memeOrdonnee() {
		return deuxiemeSprite.ordonneeLaPlusHaute() == premierSprite.ordonneeLaPlusBasse();
	}
	
	private boolean ordonneeLaPlusHauteTouche() {
		return deuxiemeSprite.ordonneeLaPlusHaute() <= premierSprite.ordonneeLaPlusBasse()
				&& deuxiemeSprite.ordonneeLaPlusHaute() >= premierSprite.ordonneeLaPlusHaute();
	}

}