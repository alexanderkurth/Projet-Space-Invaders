package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {

	private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
    
    @Test
	public void test_CoinSuperieurDroitDeuxiemeSpriteTouchePremierSprite() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(2,2),new Position(5,8), 2);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2), new Position(4,9), 3);
		
		assertEquals(true, spaceinvaders.collisionDetectee(spaceinvaders.recupererEnvahisseur(), spaceinvaders.recupererVaisseau()));
	}
	
	@Test
	public void test_CoinSuperieurGaucheDeuxiemeSpriteTouchePremierSprite() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(2,2),new Position(5,8), 2);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2), new Position(6,9), 3);
		
		assertEquals(true, spaceinvaders.collisionDetectee(spaceinvaders.recupererEnvahisseur(), spaceinvaders.recupererVaisseau()));
	}
	
	@Test
	public void test_CoinInferieurDroitDeuxiemeSpriteTouchePremierSprite() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(2,2),new Position(5,9), 2);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2), new Position(4,8), 3);
		
		assertEquals(true, spaceinvaders.collisionDetectee(spaceinvaders.recupererEnvahisseur(), spaceinvaders.recupererVaisseau()));
	}
	
	@Test
	public void test_CoinInferieurGaucheDeuxiemeSpriteTouchePremierSprite() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(2,2),new Position(5,9), 2);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,2), new Position(6,8), 3);
		
		assertEquals(true, spaceinvaders.collisionDetectee(spaceinvaders.recupererEnvahisseur(), spaceinvaders.recupererVaisseau()));
	}
	
}
