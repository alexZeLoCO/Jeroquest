package jeroquest.utils;

public class Statistics {

	private int HeroAttacks;
	private int MonsAttacks;
	private int HeroDamage;
	private int MonsDamage;
	
	public Statistics() {
		setAll(0);
	}
	
	public void setAll (int n) {
		this.HeroAttacks=n;
		this.MonsAttacks=n;
		this.HeroDamage=n;
		this.MonsDamage=n;
	}
	
	public void increaseHA () {
		this.HeroAttacks++;
	}
	
	public void increaseMA () {
		this.MonsAttacks++;
	}
	
	public void increaseHD (int dmg) {
		this.HeroDamage=getHeroesDamageInflicted()+dmg;
	}
	
	public void increaseMD (int dmg) {
		this.MonsDamage=getMonstersDamageInflicted()+dmg;
	}
	
	public int getHeroesAttacks () {
		return this.HeroAttacks;
	}
	
	public int getMonstersAttacks () {
		return this.MonsAttacks;
	}
	
	public int getHeroesDamageInflicted () {
		return this.HeroDamage;
	}
	
	public int getMonstersDamageInflicted () {
		return this.MonsDamage;
	}
	
	@Override
	public String toString () {
		return String.format("Statistics:\n\t\tHeroes\tMonsters\nAttacks:\t\t%d\t%d\nDamage:\t\t%d\t%d",
				getHeroesAttacks(),getMonstersAttacks(),
				getHeroesDamageInflicted(),getMonstersDamageInflicted());
	}
}
