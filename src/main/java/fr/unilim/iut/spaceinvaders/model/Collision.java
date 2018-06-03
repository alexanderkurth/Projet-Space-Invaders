package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	private Missile missile;
	private Envahisseur envahisseur;
	
	public Collision(Envahisseur envahisseur, Missile missile) {
		this.missile = missile;
		this.envahisseur = envahisseur;
	}

	public void detecterCollision(SpaceInvaders spaceInvaders) {
		if (missile.abscisseLaPlusAGauche() == envahisseur.abscisseLaPlusAGauche()) {
			spaceInvaders.supprimerMissile();
			spaceInvaders.supprimerEnvahisseur();
		}
	}
}
