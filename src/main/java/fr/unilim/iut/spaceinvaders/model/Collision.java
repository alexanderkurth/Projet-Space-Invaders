package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	private Sprite premierSprite;
	private Sprite deuxiemeSprite;
	private boolean collisionTrouvee;
	private int abscisseTesteePremierSprite;
	
	public Collision(Sprite premierSprite, Sprite deuxiemeSprite) {
		this.premierSprite = premierSprite;
		this.deuxiemeSprite = deuxiemeSprite;
		this.collisionTrouvee = false;
		this.abscisseTesteePremierSprite = premierSprite.abscisseLaPlusAGauche();
	}

	public void detecterCollision(SpaceInvaders spaceInvaders) {
		
		while (!collisionTrouvee && toutesLesAbcissesNeSontPasTestees(abscisseTesteePremierSprite)) {
			if (lesSpriteSeTouchent(abscisseTesteePremierSprite)) {
				spaceInvaders.supprimerMissile();
				spaceInvaders.supprimerEnvahisseur();
				
				collisionTrouvee = true;
			}

			onTesteLAbscisseSuivante();
		}
	}

	private int onTesteLAbscisseSuivante() {
		return abscisseTesteePremierSprite++;
	}

	private boolean toutesLesAbcissesNeSontPasTestees(int abscisseTesteePremierSprite) {
		return abscisseTesteePremierSprite <= deuxiemeSprite.abscisseLaPlusADroite();
	}

	private boolean lesSpriteSeTouchent(int abscisseTesteePremierSprite) {
		return abscisseLaPlusAGaucheTouche(abscisseTesteePremierSprite) || abscisseLaPlusADroiteTouche(abscisseTesteePremierSprite);
	}

	private boolean abscisseLaPlusADroiteTouche(int abscisseTesteePremierSprite) {
		return abscisseTesteePremierSprite == deuxiemeSprite.abscisseLaPlusADroite() && premierSprite.ordonneeLaPlusBasse() == deuxiemeSprite.ordonneeLaPlusHaute();
	}

	private boolean abscisseLaPlusAGaucheTouche(int abscisseTesteePremierSprite) {
		return abscisseTesteePremierSprite == deuxiemeSprite.abscisseLaPlusAGauche() && premierSprite.ordonneeLaPlusBasse() == deuxiemeSprite.ordonneeLaPlusHaute();
	}
}
