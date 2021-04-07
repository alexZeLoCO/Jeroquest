package jeroquest.logic;
import jeroquest.utils.DynamicVectorCharacters;
import jeroquest.utils.Statistics;
import jeroquest.boardgame.Board;
import jeroquest.boardgame.Dice;
import jeroquest.units.Barbarian;
import jeroquest.units.Character;
import jeroquest.units.Dwarf;
import jeroquest.units.Goblin;
import jeroquest.units.Mummy;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Game - represents the state of a game and it is defined
 * as: a set of characters, the board where they move around, the current round
 * and the total number of rounds to play
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 * 
 */

public class Game {
	private Statistics stats;
	private DynamicVectorCharacters characters; // characters in the game
	private Board board; // where the game takes place
	private int currentRound; // current round
	private int totalRounds; // maximum number of rounds to play
	
	public Statistics getStatistics() {
		return this.stats;
	}
	
	/**
	 * Gets the total rounds of the game
	 * 
	 * @return the total number of rounds of the game
	 */
	public int getTotalRounds() {
		return totalRounds;
	}

	/**
	 * Set the total of rounds (it is a private method)
	 * 
	 * @param totalRounds the total number of rounds to play
	 */
	private void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}

	/**
	 * Create a new game with parameters
	 * 
	 * @param numHeroes   total number of heroes to create
	 * @param numMonsters total number of monsters to create
	 * @param rows        height of the board to create
	 * @param columns     width of the board to create
	 * @param totalRounds total number of rounds to play
	 */
	public Game(int numHeroes, int numMonsters, int rows, int columns, int totalRounds) {
		// new stats
		stats = new Statistics();
		
		// total number of rounds
		setTotalRounds(totalRounds);

		// Create a board with the given dimensions
		board = new Board(rows, columns);

		// create the characters
		characters = new DynamicVectorCharacters();

		// random heroes
		for (int x = 0; x < numHeroes; x++)
			if (Dice.roll() % 2 == 0)// if even create a barbarian
				characters.add(new Barbarian("Barbarian" + x, "<NoPlayer>"));
			else // if odd create a Dwarf
				characters.add(new Dwarf("Dwarf" + x, "<NoPlayer>"));

		// random monsters
		for (int y = 0; y < numMonsters; y++)
			if (Dice.roll() % 2 == 0)// if even create a mummy
				characters.add(new Mummy("Mummy" + y));
			else // if odd create a goblin
				characters.add(new Goblin("Goblin" + y));

		// first round
		currentRound = 1;
	}

	/**
	 * Get the current round in the game
	 * 
	 * @return the current round
	 */
	public int getCurrentRound() {
		return currentRound;
	}

	/**
	 * Set the current round
	 * 
	 * @param currentRound the new current round
	 */
	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	/**
	 * Get the character of the game
	 * 
	 * @return array with the character of the game
	 */
	public DynamicVectorCharacters getCharacters() {
		return this.characters;
	}

	/**
	 * Get the board of the game
	 * 
	 * @return the board of the game
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Generate a printable version of the object as String (Overridden method)
	 * 
	 * @return The printable version of the object as String
	 */
	@Override
	public String toString() {
		String s = "";
		for (int x = 0; x < characters.length(); x++) {
			s += String.format("%s\n", characters.vectorNormal()[x]);
		}
		s += getBoard();
		return s;
	}
	
	/**
	 * Shuffles all characters in the Game vector
	 * 
	 * @return DynamicVectorCharacters (shuffled)
	 */
	public DynamicVectorCharacters shuffle () {
		Character memory;
		for (int i=0;i<getCharacters().length();i++) {
			int position = Dice.roll(getCharacters().length())-1;
			memory = getCharacters().get(position);
			getCharacters().set(position, getCharacters().get(i));
			getCharacters().set(i,memory);
		}
		return getCharacters();
	}

}
