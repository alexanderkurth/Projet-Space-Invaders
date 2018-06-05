package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {
	
	int longueur;
    int hauteur;
    Vaisseau vaisseau;
    Missile missile;
    Envahisseur envahisseur;
    Collision collision;

    public SpaceInvaders(int longueur, int hauteur) {
	   this.longueur = longueur;
	   this.hauteur = hauteur;
    }

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x,y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}
	
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}
	
	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}
	
	public boolean aUnVaisseau() {
		return vaisseau!=null;
	}
	
	public boolean aUnEnvahisseur() {
		return this.envahisseur != null;
	}

	public boolean aUnMissile() {
		return missile != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
	
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}
	
	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusBasse())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusBasse());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusBasse());
		}
	}
	
	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();
		
		if (!estDansEspaceJeu(x,y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");
		
		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();
		
		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");
		
		envahisseur = new Envahisseur(dimension, position, vitesse);
	}
	
	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}
	
	public void supprimerEnvahisseur() {
		this.envahisseur = null;
	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);	
		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse())) 
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusBasse());
	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);	
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusBasse()))
				envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusBasse());
		}
	}
	
	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if (pasAssezDePlaceEntreVaisseauEtHautEcran(dimensionMissile))
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
							
		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
	}

	private boolean pasAssezDePlaceEntreVaisseauEtHautEcran(Dimension dimensionMissile) {
		return (vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur;
	}

	public Missile recupererMissile() {
		return this.missile;
	}
	
	public void supprimerMissile() {
		this.missile = null;
	}
	
	public void deplacerMissile() {
		this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		
		if (missileSortDeLEcran())
			supprimerMissile();
	}

	private boolean missileSortDeLEcran() {
		return this.missile.ordonneeLaPlusHaute() < 0;
	}
	
	public boolean collisionDetectee(Sprite premierSprite, Sprite deuxiemeSprite) {
		collision = new Collision (premierSprite, deuxiemeSprite);
		
		return collision.detecterCollision(this);
	}
	
	public void initialiserJeu() {
	    Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
	    Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
	    positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	    
	    Position positionEnvahisseur = new Position(this.longueur/2,this.hauteur-(this.hauteur-Constante.ENVAHISSEUR_HAUTEUR));
	    Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
	    positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
    }
	
	@Override
	public boolean etreFini() {
		return false; 
	}

	@Override
    public void evoluer(Commande commandeUser) {
				
		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}
		
		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
		
		if (commandeUser.tir && !this.aUnMissile()) {
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR), Constante.MISSILE_VITESSE);
		}
		
		if (this.aUnMissile()) {
			deplacerMissile();
		}
		
		deplacementAutomatiqueEnvahisseur();
		
		if (this.aUnMissile() && this.aUnEnvahisseur() && collisionDetectee(this.envahisseur, this.missile)) {
			supprimerMissile();
			supprimerEnvahisseur();
		}
	}

	private void deplacementAutomatiqueEnvahisseur() {
		if (this.aUnEnvahisseur() && envahisseur.doitSeDeplacerVersLaDroite()) {
			deplacerEnvahisseurVersLaDroite();				
		}
		
		if (this.aUnEnvahisseur() && !envahisseur.doitSeDeplacerVersLaDroite()) {
			deplacerEnvahisseurVersLaGauche();
		}
		
		if (envahisseurEstColleADroite() || envahisseurEstColleAGauche())
			envahisseur.changementDeDirection();
	}

	private boolean envahisseurEstColleAGauche() {
		return envahisseur.abscisseLaPlusAGauche() == 0;
	}

	private boolean envahisseurEstColleADroite() {
		return envahisseur.abscisseLaPlusADroite() == this.longueur-1;
	}

}