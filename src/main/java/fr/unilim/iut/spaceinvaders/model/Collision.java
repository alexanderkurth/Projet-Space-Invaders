package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	private Sprite premierSprite;
	private Sprite deuxiemeSprite;
	
	public Collision(Sprite premierSprite, Sprite deuxiemeSprite) {
		this.premierSprite = premierSprite;
		this.deuxiemeSprite = deuxiemeSprite;
	}

	public boolean detecterCollision(SpaceInvaders spaceInvaders) {
		return deuxiemeSpriteTouchePremierSprite();
	}

	private boolean deuxiemeSpriteTouchePremierSprite() {
		return coinSuperieurGaucheDeuxiemeSpriteTouchePremierSprite() || coinInferieurGaucheDeuxiemeSpriteTouchePremierSprite() || coinInferieurDroitDeuxiemeSpriteTouchePremierSprite() || coinSuperieurDroitDeuxiemeSpriteTouchePremierSprite();
	}

	private boolean coinSuperieurDroitDeuxiemeSpriteTouchePremierSprite() {
		return abscisseLaPlusADroiteTouche() && ordonneeLaPlusHauteTouche();
	}

	private boolean coinInferieurDroitDeuxiemeSpriteTouchePremierSprite() {
		return abscisseLaPlusADroiteTouche() && ordonneeLaPlusBasseTouche();
	}

	private boolean coinInferieurGaucheDeuxiemeSpriteTouchePremierSprite() {
		return abscisseLaPlusAGaucheTouche() && ordonneeLaPlusBasseTouche();
	}

	private boolean coinSuperieurGaucheDeuxiemeSpriteTouchePremierSprite() {
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