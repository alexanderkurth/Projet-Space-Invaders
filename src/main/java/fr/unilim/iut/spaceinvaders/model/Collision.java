package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	private Sprite premierSprite;
	private Sprite deuxiemeSprite;
	
	public Collision(Sprite premierSprite, Sprite deuxiemeSprite) {
		this.premierSprite = premierSprite;
		this.deuxiemeSprite = deuxiemeSprite;
	}

	public void detecterCollision(SpaceInvaders spaceInvaders) {
		if (toucheEnAbscisse()) {
			spaceInvaders.supprimerMissile();
			spaceInvaders.supprimerEnvahisseur();
		}
		
		if (deuxiemeSprite.abscisseLaPlusAGauche() == premierSprite.abscisseLaPlusADroite()
				&& deuxiemeSprite.ordonneeLaPlusHaute() <= premierSprite.ordonneeLaPlusBasse()
				&& deuxiemeSprite.ordonneeLaPlusHaute() >= premierSprite.ordonneeLaPlusHaute()) {
			spaceInvaders.supprimerMissile();
			spaceInvaders.supprimerEnvahisseur();
		}
		
		if (deuxiemeSprite.abscisseLaPlusAGauche() == premierSprite.abscisseLaPlusADroite()
				&& deuxiemeSprite.ordonneeLaPlusBasse() <= premierSprite.ordonneeLaPlusBasse()
				&& deuxiemeSprite.ordonneeLaPlusBasse() >= premierSprite.ordonneeLaPlusHaute()) {
			spaceInvaders.supprimerMissile();
			spaceInvaders.supprimerEnvahisseur();
		}
		
	}

	private boolean toucheEnAbscisse() {
		return abscisseLaPlusAGaucheTouche() || abscisseLaPlusADroiteTouche();
	}

	private boolean abscisseLaPlusADroiteTouche() {
		return deuxiemeSprite.abscisseLaPlusADroite() >= premierSprite.abscisseLaPlusAGauche() 
				&& deuxiemeSprite.abscisseLaPlusADroite() <= premierSprite.abscisseLaPlusADroite()
				&& deuxiemeSprite.ordonneeLaPlusHaute() == premierSprite.ordonneeLaPlusBasse();
	}

	private boolean abscisseLaPlusAGaucheTouche() {
		return deuxiemeSprite.abscisseLaPlusAGauche() >= premierSprite.abscisseLaPlusAGauche() 
				&& deuxiemeSprite.abscisseLaPlusAGauche() <= premierSprite.abscisseLaPlusADroite()
				&& deuxiemeSprite.ordonneeLaPlusHaute() == premierSprite.ordonneeLaPlusBasse();
	}

}
