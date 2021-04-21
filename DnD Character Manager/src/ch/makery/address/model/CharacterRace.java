package ch.makery.address.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CharacterRace {
	private SimpleStringProperty increase;
	private SimpleIntegerProperty speed;
	private SimpleStringProperty abilities;
	private SimpleStringProperty languages;
	private SimpleStringProperty extras;
	private CharacterRace subRace;
	
	public CharacterRace() {

	}
	
	public CharacterRace(String increase, int speed, String abilities, String languages) {
		this.increase = new SimpleStringProperty(increase);
		this.speed = new SimpleIntegerProperty(speed);
		this.increase = new SimpleStringProperty(abilities);
		this.increase = new SimpleStringProperty(languages);	
	}
	
	public CharacterRace(CharacterRace subRace, String increase, int speed, String abilities, String languages) {
		this.subRace = subRace;
		this.increase = new SimpleStringProperty(increase);
		this.speed = new SimpleIntegerProperty(speed);
		this.increase = new SimpleStringProperty(abilities);
		this.increase = new SimpleStringProperty(languages);	
	}
	
	public CharacterRace(String increase, String extras) {
		this.increase = new SimpleStringProperty(increase);
		this.extras = new SimpleStringProperty(extras); 	
	}
	
	
	public CharacterRace getSubRace() {
		return subRace;
	}
	
	public String getIncrease() {
		return increase.get();
	}
	
	public int getSpeed() {
		return speed.get();
	}
	
	public String getAbilities() {
		return abilities.get();
	}
	
	public String getLanguages() {
		return languages.get();
	}
	
	public String getExtras() {
		return extras.get();
	}
}
