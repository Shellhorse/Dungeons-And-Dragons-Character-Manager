package ch.makery.address.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListOfRaces {
	private ArrayList<CharacterRace> listOfRaces;
	
	public ListOfRaces() {
		listOfRaces = new ArrayList<CharacterRace>();
		//Making of Dwarf Race and Sub-Races
		CharacterRace Dwarf = new CharacterRace("Con 2", 25, "Darkvision, Dwarven Resilence, Stonecunning", "Common, Dwarvish");
		CharacterRace Hill_Dwarf = new CharacterRace("Wis 1", "Dwarven Toughness");
		CharacterRace Mountain_Dwarf = new CharacterRace("Str 2", "Dwarven Armour Training");
		
		//Making of Races with included Sub_Race and adding to list of Races
		CharacterRace HillDwarf = new CharacterRace(Hill_Dwarf, "Con 2", 25, "Darkvision, Dwarven Resilence, Stonecunning", "Common, Dwarvish");
		CharacterRace MountainDwarf = new CharacterRace(Mountain_Dwarf, "Con 2", 25, "Darkvision, Dwarven Resilence, Stonecunning", "Common, Dwarvish");
		listOfRaces.add(Dwarf);
		listOfRaces.add(HillDwarf);
		listOfRaces.add(MountainDwarf);
	
		
		//Making of Elf Race and Sub-Races
		CharacterRace Elf = new CharacterRace("Dex 2", 30, "Darkvision, Keen Senses, Fey Ancestory, Trance", "Common, Elvish");
		CharacterRace High_Elf = new CharacterRace("Int 1", "Elf Weapon Training. Cantrip, Extra Language");
		CharacterRace Wood_Elf = new CharacterRace("Wis 1", "Elf Weapon Training, Mask of the Wild, Fleet of Foot");
        CharacterRace Dark_Elf = new CharacterRace("Char 1", "Superior Darkvision, Drow Magic, Drow Weapon Training");
        
        //Making of Races with included Sub_Race and adding to list of Races
        CharacterRace HighElf = new CharacterRace(High_Elf, "Dex 2", 30, "Darkvision, Keen Senses, Fey Ancestory, Trance", "Common, Elvish");
        CharacterRace WoodElf = new CharacterRace(Wood_Elf, "Dex 2", 30, "Darkvision, Keen Senses, Fey Ancestory, Trance", "Common, Elvish");
        CharacterRace DarkElf = new CharacterRace(Dark_Elf, "Dex 2", 30, "Darkvision, Keen Senses, Fey Ancestory, Trance", "Common, Elvish");
		listOfRaces.add(Elf);
		listOfRaces.add(HighElf);
		listOfRaces.add(WoodElf);
		listOfRaces.add(DarkElf);
		
		
		//Making of Human Race and Adding to list of Races
		CharacterRace Human = new CharacterRace("All 1", 30, "", "Common, One extra of your choice");
		listOfRaces.add(Human);
		
		
		//Making of Gnome Race and Sub-Races
		CharacterRace Gnome = new CharacterRace("Int 2", 25, "Darkvision, Gnome Cunning", "Common, Gnomish");
		CharacterRace Forrest_Gnome = new CharacterRace("Dex 1", "Natural Illusionist, Speak with Small Beasts");
		CharacterRace Rock_Gnome = new CharacterRace("Con 1", "Artificer's Lore, Tinker");
		
		//Making of Races with included Sub-Race and adding to list of Races
		CharacterRace RockGnome = new CharacterRace(Rock_Gnome, "Int 2", 25, "Darkvision, Gnome Cunning", "Common, Gnomish");
		CharacterRace ForrestGnome = new CharacterRace(Forrest_Gnome, "Int 2", 25, "Darkvision, Gnome Cunning", "Common, Gnomish");
		listOfRaces.add(Gnome);
		listOfRaces.add(ForrestGnome);
		listOfRaces.add(RockGnome);
		
		
		//Making of Halfling Race and Sub-Races
		CharacterRace Halfling = new CharacterRace("Dex 2", 25, "Lucky, Brave, Halfling Nimbleness", "Common, Halfling");
		CharacterRace Lightfoot_Halfling = new CharacterRace("Char 1", "Naturally Stealthy");
		CharacterRace Stout_Halfling = new CharacterRace("Con 1", "Stout Resilience");
		
		//Making of Races with included Sub-Race and adding to list of Races
		CharacterRace LightfootHalfling = new CharacterRace(Lightfoot_Halfling, "Dex 2", 25, "Lucky, Brave, Halfling Nimbleness", "Common, Halfling");
		CharacterRace StoutHalfling = new CharacterRace(Stout_Halfling, "Dex 2", 25, "Lucky, Brave, Halfling Nimbleness", "Common, Halfling");
		listOfRaces.add(Halfling);
		listOfRaces.add(LightfootHalfling);
		listOfRaces.add(StoutHalfling);
		
		
		//Making of Half-Orc Race
		CharacterRace Half_Orc = new CharacterRace("Str 2, Con 1", 30, "Darkvision, Menacing, Relentless Endurance, Savage Attacks", "Common, Orc");
		listOfRaces.add(Half_Orc);
		
		
		//Making of Half-Elf Race
		CharacterRace Half_Elf = new CharacterRace("Char 2, 2 Choice 1", 30, "Darkvision, Fey Ancestory, Skill Versitility", "Common, Elvish");
		listOfRaces.add(Half_Elf);
		
		
		//Making of Dragonborn Race
		CharacterRace Dragonborn = new CharacterRace("Str 2, Char 1", 30, "Draconic Ancestory, Breath Weapon, Damage Resistence", "Common, Draconic");
		listOfRaces.add(Dragonborn);
		
		
		//Making of Tiefling Race
		CharacterRace Tiefling = new CharacterRace("Int 1, Char 2", 30, "Darkvision, Hellish Resilence, Infernal Legacy", "Common, Infernal");
		listOfRaces.add(Tiefling);
	}
	
	//Returns List of Races
	public ArrayList<CharacterRace> getListOfRaces(){
		return this.listOfRaces;
	}
	
	//Returns all Races
	public ObservableList<CharacterRace> getAllRaces(){
		ObservableList<CharacterRace> CharacterRace = FXCollections.observableArrayList(listOfRaces);
		return CharacterRace;
	}

	public static void main(String[] args) {

	}

}
