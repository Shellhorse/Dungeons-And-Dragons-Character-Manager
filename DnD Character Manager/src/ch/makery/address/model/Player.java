package ch.makery.address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//Benjamin
//For working with files
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

import ch.makery.address.MainApp;
import ch.makery.address.view.LoginController;

public class Player {
	
	private String name;
	private String userName;
	private int type; //for Party Members vs the DM
	private String password;
	private String salt; //The key to hash the password
	private int numChars; //Holds the number of characters
	private ArrayList<String> charIDs = new ArrayList<String>(); //holds the IDs of characters (first 2 digits is the ID for player, 2nd 2 digits is the ID for character
	
	//Player array
    private static ArrayList<Player> players = new ArrayList<Player>();
	
	//Default constructor for Player
	public Player() {
		this(null,null,0,null,null,0,null);
	}
	
	//Constructor to create a brand new player
	public Player(String name, String un, int playerType, String pw, String salt, int charNum, ArrayList<String> charIDs) {
		this.name = name;
		this.type = playerType;
		this.userName = un;
		this.password = pw;
		this.salt = salt;
		this.numChars = charNum;
		
		if(charIDs.size() > this.numChars)
			this.numChars = charIDs.size();
		
		for(int i = 0; i < charIDs.size(); i++)
			this.charIDs.add(charIDs.get(i));
	}
	
	//Returns list from playerData files
	public static ArrayList<Player> getAllPlayers() {
		players = new ArrayList<Player>();
		try {
			File inputPlayers = new File("resources/playerData.txt");
		    Scanner scanner = new Scanner(inputPlayers);
		    int i = 0;
		    
		    //Read line by line
		    while (scanner.hasNextLine()) {		    	
		    	//Get the next full line from the file
		    	String line = scanner.nextLine();
		    	
		    	//Splits the line by delimiters
		    	String[] data = line.split(";;");
		    	ArrayList<String> charData = new ArrayList<String>();
		    	
		    	//Convert string to array for characters
		    	String[] temp = data[6].split(",");
		    	for(int j = 0; j < temp.length; j++)
		    		charData.add(temp[j]);
		    	
		    	Player p = new Player(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], Integer.parseInt(data[5]), charData);
		    	
		    	players.add(p);
		    	
		    	//System.out.println(data[6]);
		      
		    	i++;
		    }
		    
		    scanner.close();
		} 
		catch (FileNotFoundException e) {
			//Output the error(s)
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
		return players;
	}
	
	//Getters
	public String getName() 				{return this.name;}
	public String getUserName() 			{return this.userName;}
	public int getPlayerType() 				{return this.type;}
	public String getPassword() 			{return this.password;}
	public String getSalt() 				{return this.salt;}
	public int getNumChars() 				{return this.numChars;}
	public ArrayList<String> getCharIDs() 	{return this.charIDs;}
	public String getSpecificCharID(int i) 	{return this.charIDs.get(i);}
	
	//Setters
	public void setName(String n) 			{this.name = n;}
	public void setUserName(String u) 		{this.userName = u;}
	public void setPlayerType(int t) 		{this.type = t;}
	public void setPassword(String p) 		{this.password = p;}
	public void setnumChars(int c) 			{this.numChars = c;}
	public void setCharIDs(ArrayList<String> id) 	{
		
		if(id.size() > this.numChars)
			this.numChars++; //If there are more charIDs than the expected num chars
		
		for(int i = 0; i < id.size(); i++)
			this.charIDs.add(id.get(i));
	}
	
	public static void saveData(ArrayList<Player> players) {
		//Save data to file
		Formatter output;
		try {
			output = new Formatter("resources/playerData.txt");
			
			for(int i = 0; i < players.size(); i++) {
				//Fixing a potential error with mismatched numbers
				players.get(i).numChars = players.get(i).charIDs.size();
				
				//Outputs the user data to file
				output.format("%s;;%s;;%d;;%s;;%s;;%d;;", players.get(i).getName(), players.get(i).getUserName(), players.get(i).getPlayerType(), 
						players.get(i).getPassword(), players.get(i).getSalt(), players.get(i).getNumChars());
				
				//Outputs all char IDs
				for(int j = 0; j < players.get(i).charIDs.size(); j++)
					output.format("%4s,", players.get(i).charIDs.get(j));
				
				output.format("\n");
				
			}
			
			output.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void createNewPlayer(String name, String username, String rawPassword, int characters) {
		String salt = LoginController.generateSalt(10).get();
		String password = LoginController.makeHash(rawPassword, salt).get();
		
		ArrayList<Player> players = getAllPlayers();
		ArrayList<Character> charactersArray = Character.getAllChars();
		ArrayList<String> lastIDs = players.get(players.size()-1).getCharIDs();
		
		int newID = Integer.parseInt(lastIDs.get(0).substring(0, 2))+1;
		ArrayList<String> charIDs = new ArrayList<String>();
		
		for(int i = 1; i < characters+1; i++) {
			if(newID < 10 && i < 10) 
				charIDs.add("0" + newID + "0" + i);
			else if (newID < 10)
				charIDs.add("0" + newID + i);
			else if (i < 10)
				charIDs.add(newID + "0" + i);
			else
				charIDs.add("" + newID + i);
			
			//Add a new character with base data, and set the charID to the one just set above
			Character c = new Character();
			c.setCharID(charIDs.get(charIDs.size()-1));
			charactersArray.add(c);
		}
		
		//Add the appropriate number of new players
		Character.saveData(charactersArray);
		
		//System.out.println(charIDs.get(charIDs.size()-1));
		
		//Create the new player object		
		Player newPlayer = new Player(name, username, 2, password, salt, characters, charIDs);
		players.add(newPlayer);
		saveData(players);
	}
	
	public static void createNewGM(String name, String username, String rawPassword, int characters) {
		String salt = LoginController.generateSalt(10).get();
		String password = LoginController.makeHash(rawPassword, salt).get();
		
		ArrayList<Player> players = getAllPlayers();
		ArrayList<String> lastIDs = players.get(players.size()-1).getCharIDs();
		
		int newID = Integer.parseInt(lastIDs.get(0).substring(0, 2))+1;
		ArrayList<String> charIDs = new ArrayList<String>();
		
		for(int i = 1; i < characters+1; i++) {
			if(newID < 10 && i < 10) 
				charIDs.add("0" + newID + "0" + i);
			else if (newID < 10)
				charIDs.add("0" + newID + i);
			else if (i < 10)
				charIDs.add(newID + "0" + i);
			else
				charIDs.add("" + newID + i);
		}
		
			
		System.out.println(charIDs.get(charIDs.size()-1));
		
		//Create the new player object		
		Player newPlayer = new Player(name, username, 1, password, salt, characters, charIDs);
		players.add(newPlayer);
		saveData(players);
	}
	
	public static void addCharacter(String charID) {
		ArrayList<Player> players = getAllPlayers();
		
		//Find the index of the currentPlayer, and add the new charID
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).charIDs.get(0).equals(MainApp.currentPlayer.charIDs.get(0))) {
				players.get(i).charIDs.add(charID);
				players.get(i).numChars++;
			}
		}
		
		saveData(players);
	}
	
	public static void removeCharacter(String charID) {
		ArrayList<Player> players = getAllPlayers();
		
		//Find the index of the currentPlayer, and add the new charID
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).charIDs.get(0).substring(0, 2).equals(charID.subSequence(0, 2))) {
				for(int j = 0; j < players.get(i).charIDs.size(); j++) {
					if(players.get(i).charIDs.get(j).equals(charID)) {
						players.get(i).charIDs.remove(j);
						players.get(i).numChars--;
					}
				
				}
				//players.get(i).charIDs.add(charID);
				//players.get(i).numChars++;
			}
		}
		
		saveData(players);
	}
	
	//Call when the user logs out, resets the current player in MainApp
	public static void logout() {
		MainApp.currentPlayer = null;
	}
}
