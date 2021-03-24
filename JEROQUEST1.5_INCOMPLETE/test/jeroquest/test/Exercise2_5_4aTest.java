package jeroquest.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeroquest.boardgame.Dice;
import jeroquest.boardgame.XYLocation;
import jeroquest.gui.JeroquestWindow;
import jeroquest.logic.Game;
import jeroquest.logic.Jeroquest;
import jeroquest.units.Character;
import jeroquest.units.Hero;
import jeroquest.units.Weapon;
import jeroquest.utils.DynamicVectorCharacters;

class Exercise2_5_4aTest {

	@Test
	void testCharacterVictimHighestBody() {

		for (int j = 0; j < 10; j++) {

			Game currentGame = new Game(1, 4, 7, 10, 20);

			Character hero = currentGame.getCharacters()[0];
			currentGame.getBoard().movePiece(hero, new XYLocation(4, 5));
			currentGame.getBoard().movePiece(currentGame.getCharacters()[1], new XYLocation(3, 5));
			currentGame.getBoard().movePiece(currentGame.getCharacters()[2], new XYLocation(4, 4));
			currentGame.getBoard().movePiece(currentGame.getCharacters()[3], new XYLocation(5, 5));
			currentGame.getBoard().movePiece(currentGame.getCharacters()[4], new XYLocation(4, 6));

			// Find the character(s) with highest body
			int highestBody = 0;
			DynamicVectorCharacters charsWithHighestBody = new DynamicVectorCharacters();
			for (int i = 1; i < currentGame.getCharacters().length; i++) {
				if (currentGame.getCharacters()[i].getBody() == highestBody) {
					charsWithHighestBody.add(currentGame.getCharacters()[i]);
				} else if (currentGame.getCharacters()[i].getBody() > highestBody) {
					highestBody = currentGame.getCharacters()[i].getBody();
					charsWithHighestBody = new DynamicVectorCharacters();
					charsWithHighestBody.add(currentGame.getCharacters()[i]);
				}
			}
			// We want that the hero always kills the target
			((Hero) hero).setWeapon(new Weapon("Atomic bomb", 100));

			hero.actionCombat(currentGame);

			assertTrue(anyDeadCharacter(charsWithHighestBody));
		}
	}

	private boolean anyDeadCharacter(DynamicVectorCharacters characters) {
		for (int i = 0; i < characters.length(); i++) {
			if (!characters.get(i).isAlive())
				return true;
		}
		return false;
	}
}
