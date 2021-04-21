package ch.makery.address.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.makery.address.MainApp;
import ch.makery.address.view.LoginController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Character {

	private final StringProperty charName;
	private final StringProperty charClassLevel;
	private final StringProperty charAlignment;
	private final StringProperty charRace;
	private final StringProperty charBackground;
	private final IntegerProperty charExperience;	
	//Character ID to crossreference with Player class
	private final StringProperty charID;
	private final StringProperty charSavingThrows;
	private final StringProperty charSkills;
	private final StringProperty charHitDice;
	private final StringProperty charDeathSaves;
	private final StringProperty charAttacks;
	private final StringProperty charOtherProfs;
	private final StringProperty charFeaturesTraits;
	private final StringProperty charEquipment;
	private final StringProperty charBackstory;
	//Strength, Dex, Con, Int, Wis, Char, proficiency bonus, armor, initiative, speed, current hp, temp hp
	int[] attributes = new int[12];
	
	//Array of characters to be used for file I/O
	private static ArrayList<Character> characters = new ArrayList<Character>();
	

	//Default Constructor 
	public Character() {
		this.charName = new SimpleStringProperty("Name");
		this.charClassLevel = new SimpleStringProperty("Class & Level");
		this.charAlignment = new SimpleStringProperty("Alignment");
		this.charRace = new SimpleStringProperty("Race");
		this.charBackground = new SimpleStringProperty("Background");
		this.charExperience = new SimpleIntegerProperty(0);
		this.charID = new SimpleStringProperty(null);
		this.charSavingThrows = new SimpleStringProperty("Saving Throws");
		this.charSkills = new SimpleStringProperty("Skills");
		this.charHitDice = new SimpleStringProperty("Hit Dice");
		this.charDeathSaves = new SimpleStringProperty("Death Saves");
		this.charAttacks = new SimpleStringProperty("Attacks");
		this.charOtherProfs = new SimpleStringProperty("Other Proficiencies & Languages");
		this.charFeaturesTraits = new SimpleStringProperty("Features & Traits");
		this.charEquipment = new SimpleStringProperty("Equipment");
		this.charBackstory = new SimpleStringProperty("Backstory");
	}
	
	
	//Constructor for full character
	public Character(String name, String classLevel, String alignment, String race, String background,
			Integer experience, String charID, int[] stats, String savingThrows, String skills,
			String hitDice, String deathSaves, String attacks, String otherProfs, String featuresTraits,
			String equipment, String backstory) {
		this.charName = new SimpleStringProperty(name);
		this.charClassLevel = new SimpleStringProperty(classLevel);
		this.charAlignment = new SimpleStringProperty(alignment);
		this.charRace = new SimpleStringProperty(race);
		this.charBackground = new SimpleStringProperty(background);
		this.charExperience = new SimpleIntegerProperty(experience);
		this.charID = new SimpleStringProperty(charID);
		this.attributes = stats;
		this.charSavingThrows = new SimpleStringProperty(savingThrows);
		this.charSkills = new SimpleStringProperty(skills);
		this.charHitDice = new SimpleStringProperty(hitDice);
		this.charDeathSaves = new SimpleStringProperty(deathSaves);
		this.charAttacks = new SimpleStringProperty(attacks);
		this.charOtherProfs = new SimpleStringProperty(otherProfs);
		this.charFeaturesTraits = new SimpleStringProperty(featuresTraits);
		this.charEquipment = new SimpleStringProperty(equipment);
		this.charBackstory = new SimpleStringProperty(backstory);
	}
	
	
	//Getters
	public String getFirstName() { return charName.get(); }
	public String getClassLevel() { return charClassLevel.get(); }
	public String getAlignment() { return charAlignment.get(); }
	public String getRace() { return charRace.get(); }
	public String getBackground() { return charBackground.get(); }
	public int getExperience() { return charExperience.get(); }
	public String getCharID() { return charID.get();}
	public int[] getStats() { return this.attributes; }
	public String getSavingThrows() { return charSavingThrows.get();}
	public String getSkills() { return charSkills.get();}
	public String getHitDice() { return charHitDice.get();}
	public String getDeathSaves() { return charDeathSaves.get(); }
	public String getAttacks() { return charAttacks.get(); }
	public String getOtherProfs() { return charOtherProfs.get(); }
	public String getFeaturesTraits() { return charFeaturesTraits.get(); }
	public String getEquipment() { return charEquipment.get(); }
	public String getBackstory() { return charEquipment.get(); }

	//Setters
	public void setFirstName(String firstName) { this.charName.set(firstName); }
	public void setClassLevel(String ClassLevel) { this.charClassLevel.set(ClassLevel); }
	public void setAlignment(String Alignment) { this.charAlignment.set(Alignment); }
	public void setRace(String Race) { this.charRace.set(Race); }
	public void setBackground(String Background) { this.charBackground.set(Background); }
	public void setExperience(int Experience) { this.charExperience.set(Experience); }
	public void setCharID(String charID) { this.charID.set(charID); }
	public void setStats(int[] stats) { this.attributes=stats.clone(); }
	public void setSavingThrows(String savingThrows) { this.charSavingThrows.set(savingThrows);}
	public void setSkills(String skills) { this.charSkills.set(skills);}
	public void setHitDice(String hitDice) { this.charHitDice.set(hitDice);}
	public void setDeathSaves(String deathSaves) { this.charDeathSaves.set(deathSaves); }
	public void setAttacks(String attacks) { this.charAttacks.set(attacks); }
	public void setOtherProfs(String otherProfs) { this.charOtherProfs.set(otherProfs); }
	public void setFeaturesTraits(String featuresTraits) { this.charFeaturesTraits.set(featuresTraits); }
	public void setEquipment(String equipment) { this.charEquipment.set(equipment); }
	public void setBackstory(String backstory) { this.charEquipment.set(backstory); }
	
	//Properties
	public StringProperty charNameProperty() { return charName; }
	public StringProperty ClassLevelProperty() { return charClassLevel; }
	public StringProperty AlignmentProperty() { return charAlignment; }
	public StringProperty RaceProperty() { return charRace; }
	public StringProperty RaceBackground() { return charBackground; }
	public IntegerProperty ExperienceProperty() { return charExperience; }
	public StringProperty PlayerProperty(Character c) { 
		
		//Iterate though the list of players and get the one attached to the current character
		ArrayList<Player> pl = Player.getAllPlayers();
		for(int i = 0; i < pl.size(); i++) {
			ArrayList<String> ids = pl.get(i).getCharIDs();
			for(int j = 0; j < ids.size(); j++)
				try {
					if(ids.get(j).compareTo(c.getCharID()) == 0) {
						return new SimpleStringProperty(pl.get(i).getUserName());
					}
				} catch(NullPointerException npe) {
					
				}
		}
		
		//If the character isn't linked to a player somehow		
		return charID;
	}
	public StringProperty SavingThrowsProperty() { return charSavingThrows;}
	public StringProperty SkillsProperty() { return charSkills; }
	public StringProperty HitDiceProperty() { return charHitDice; }
	public StringProperty DeathSavesProperty() { return charDeathSaves; }
	public StringProperty AttacksProperty() { return charAttacks; }
	public StringProperty OtherProfsProperty() { return charOtherProfs; }
	public StringProperty FeaturesTraitsProperty() { return charFeaturesTraits; }
	public StringProperty EquipmentProperty() { return charEquipment; }
	public StringProperty BackstoryProperty() { return charBackstory; }

	//Returns list from playerData files
	public static ArrayList<Character> getAllChars() {
			//Reset the characters array for multiple calls of this function
			characters = new ArrayList<Character>();
			try {
				File inputCharacters = new File("resources/characterData.txt");
			    Scanner scanner = new Scanner(inputCharacters);
			    int i = 0;
			    
			    //Read line by line
			    while (scanner.hasNextLine()) {		    	
			    	//Get the next full line from the file
			    	String line = scanner.nextLine();
			    	
			    	//Splits the line by delimiters
			    	String[] data = line.split(";;");
			    	int[] attributeData = new int[12];
			    	
			    	//Convert string to array for characters
			    	String[] temp = data[7].split(",");
			    	for(int j = 0; j < temp.length; j++)
			    		attributeData[j] = Integer.parseInt(temp[j]);
			    	
			    	//Create a new character using the data parsed from the file
			    	Character c = new Character(data[0], data[1], data[2], data[3], data[4], Integer.parseInt(data[5]), data[6], 
			    			attributeData, data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15], data[16]);
			    	
			    	//Add the character to the list of characters.
			    	characters.add(c);
			      
			    	i++;
			    }
			    
			    scanner.close();
			} 
			catch (FileNotFoundException e) {
				//Output the error(s)
			    System.out.println("An error occurred.");
			    e.printStackTrace();
			}
			
			return characters;
	}
		
	public static void deleteCharacter(Character currentChar, MainApp mainApp) {
		//Get all the players to determine how many characters are owned by the player this character
		//belongs to - important for when this is used by a GM
		ArrayList<Player> players = Player.getAllPlayers();
		int numChars = -1;
		
		//Find the number of characters for the selected player - not nescesarily the currently logged in player
		for(int i = 0; i < players.size(); i++) {
			ArrayList<String> charIDs = players.get(i).getCharIDs();
			for(int j = 0; j < charIDs.size(); j++)
				if(currentChar.getCharID().equals(charIDs.get(j)))
					numChars = charIDs.size();
		}
			
		//If there are at least 2 characters owned by the player this is being deleted from
		if(numChars > 1) {
			ArrayList<Character> characters = getAllChars();
			String ID = currentChar.getCharID();

			for(int i = 0; i < characters.size(); i++) {
				if(characters.get(i).getCharID().equals(ID)) {
					//Decrease the number of characters for the appropriate player
					Player.removeCharacter(characters.get(i).getCharID());
					characters.remove(i);
				}
			}
			
			//Save the new char list without the selected character.
			saveData(characters);

		} else { //If there is only 1 character
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Operation");
            alert.setHeaderText("You must have at least 1 character");
            
            alert.showAndWait();
		}

	}
	
	public static void createCharacter(Character currentChar) {
		//Get the current character list and the IDs of the currentPlayer
		ArrayList<Character> characters = getAllChars();
		ArrayList<String> lastIDs = MainApp.currentPlayer.getCharIDs();
		
		int newID = Integer.parseInt(lastIDs.get(lastIDs.size()-1).substring(2, 4))+1;
				
		//set the charID to playerID + charID with leading 0 if nescesary.
		if(newID < 10)
			currentChar.charID.set(lastIDs.get(0).substring(0, 2) + "0" + newID);
		else
			currentChar.charID.set(lastIDs.get(0).substring(0, 2) + "" + newID);
		
		//Add new character to array of characters
		characters.add(currentChar);
		
		Player.addCharacter(currentChar.charID.get());
		
		saveData(characters);
	}
	
	public static void saveData(ArrayList<Character> characters) {
		//Save data to file
		Formatter output;
		
		try {
			output = new Formatter("resources/characterData.txt");
			
			for(int i = 0; i < characters.size(); i++) {
	
				//Outputs the user data to file
				output.format("%s;;%s;;%s;;%s;;%s;;%s;;%s;;", 
						characters.get(i).charName.get(), characters.get(i).charClassLevel.get(), characters.get(i).charAlignment.get(),
						characters.get(i).charRace.get(), characters.get(i).charBackground.get(), characters.get(i).charExperience.get(),
						characters.get(i).charID.get());
				
				//Outputs int array of stats
				for(int j = 0; j < characters.get(i).attributes.length; j++)
					output.format("%s,", characters.get(i).attributes[j]);
						
				//outputs the rest of the data after the stats array
				output.format(";;%s;;%s;;%s;;%s;;%s;;%s;;%s;;%s;;%s;;\n", characters.get(i).charSavingThrows.get(),
						characters.get(i).charSkills.get(), characters.get(i).charHitDice.get(), characters.get(i).charDeathSaves.get(), 
						characters.get(i).charAttacks.get(), characters.get(i).charOtherProfs.get(), characters.get(i).charFeaturesTraits.get(),
						characters.get(i).charEquipment.get(), characters.get(i).charBackstory.get());
				
			}
			
			output.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	
}