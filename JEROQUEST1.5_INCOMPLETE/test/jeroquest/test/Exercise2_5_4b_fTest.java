package jeroquest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeroquest.boardgame.Dice;
import jeroquest.boardgame.XYLocation;
import jeroquest.logic.Game;
import jeroquest.units.Character;
import jeroquest.units.Hero;
import jeroquest.units.Weapon;

class Exercise2_5_4b_fTest {

	@Test
	void testRemoveDeadCharacters() {
		Game currentGame = new Game(1, 1, 7, 10, 20);

		Character hero = currentGame.getCharacters().get(0);
		currentGame.getBoard().movePiece(hero, new XYLocation(4, 5));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(1), new XYLocation(3, 5));

		// Initially there are 2 characters
		assertEquals(2, currentGame.getCharacters().length());
		// We want that the hero always kills the target
		((Hero) hero).setWeapon(new Weapon("Atomic bomb", 100));
		hero.actionCombat(currentGame);
		// After the combat the monster dies and the vector has one single character
		assertEquals(1, currentGame.getCharacters().length());

	}

	@Test
	void testHeroMoveAndAttack() {
		Dice.setSeed(7L);
		// create a new game with a hero and a horizontal board of 1 row by 4 columns
		Game currentGame = new Game(1, 2, 1, 4, 20);

		Character hero = currentGame.getCharacters().get(0);
		currentGame.getBoard().movePiece(hero, new XYLocation(0, 3));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(1), new XYLocation(0, 0));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(2), new XYLocation(0, 1));

		hero.resolveTurn(currentGame);
		// the hero has attacked the monster at position (0,1), a Mummy who got one
		// wound
		assertEquals(1, currentGame.getCharacters().get(2).getBody());

	}

	@Test
	void testStatistics() {
		Dice.setSeed(1L);

		Game currentGame = new Game(2, 2, 2, 2, 2);
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(0), new XYLocation(0, 0));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(1), new XYLocation(1, 1));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(2), new XYLocation(0, 1));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(3), new XYLocation(1, 0));

		currentGame.getCharacters().get(0).resolveTurn(currentGame);
		currentGame.getCharacters().get(1).resolveTurn(currentGame);
		currentGame.getCharacters().get(2).resolveTurn(currentGame);
		currentGame.getCharacters().get(3).resolveTurn(currentGame);
		assertEquals(2, currentGame.getStatistics().getHeroesAttacks());
		assertEquals(1, currentGame.getStatistics().getHeroesDamageInflicted());
		assertEquals(2, currentGame.getStatistics().getMonstersAttacks());
		assertEquals(2, currentGame.getStatistics().getMonstersDamageInflicted());

	}
}
