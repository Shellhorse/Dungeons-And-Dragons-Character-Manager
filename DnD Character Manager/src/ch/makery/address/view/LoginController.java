package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ch.makery.address.MainApp;
import ch.makery.address.model.Character;

//Benjamin
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import ch.makery.address.model.Player;

public class LoginController {
    
	//Benjamin
	private static final SecureRandom RAND = new SecureRandom();
	private String userName, unsecurePassword;
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 128;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static Player[] players;
    @FXML
    private Label mainLabel;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public LoginController() {
    	
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//handleCreateUser();
    	//Player.getAllPlayers();
    	
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }    
    
    @FXML
    private void handleLogin() {
    	userName = userField.getText();
    	    	
    	/*System.out.println(userField.getText());
    	System.out.println(passField.getText());
    	System.out.println(hashPW);
    	System.out.println(salt);*/
    	
    	/*//Testing with password of "Bobson"
    	String testPW = "JpI0SQXX/7JnWVve192OUA==";
    	String testSalt = "YTaJ9AHTD4HPRg==";
    	
    	//password
    	String testPW2 = "aLo85ziLa07VrzvXf5BYKw==";
    	String testSalt2 = "GU4Y/yLiXLCyrg==";*/
    	
    	ArrayList<Player> players = Player.getAllPlayers();
    	for(int i = 0; i < players.size(); i++) {
    		if(verifyPassword(passField.getText(), players.get(i).getPassword(), players.get(i).getSalt())  
    						&& players.get(i).getUserName().equals(userName)) {
    			
    			//Successfully logged in
    			MainApp.currentPlayer = players.get(i);
    			
    			FXMLLoader loader = new FXMLLoader();
                loader.setLocation(LoginController.class.getResource("view/RootLayout.fxml"));
                RootLayoutController controller = loader.getController();
                //controller.showPlayerMenu();
    			//if (MainApp.currentPlayer.getPlayerType()==2) { controller.showGMMenu(); }
                
    			mainApp.showCharacterOverview();
    			//System.out.println("Login");
    			break;
    		}
    		else if(i == players.size()-1) {
    			Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Error");
            	alert.setHeaderText("We were unable to log this user in.");
            	alert.setContentText("Please try again or, if this is your first time here, create a user now.");
            	alert.showAndWait();
    		}
    	}        
    }
    
    @FXML
    private void handleCreateUser() {
    	mainApp.showCreateUser();
    }
    
    //Benjamin
    public static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = makeHash(password, salt);
        if (!optEncrypted.isPresent()) 
        	return false;
        return optEncrypted.get().equals(key);
      }
    
    public static Optional<String> generateSalt (final int length) {

        if (length < 1) {
          System.err.println("error in generateSalt: length must be > 0");
          return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
      }
    
    //Takes the unsecured (plain text) password and converts it to a hash.
    public static Optional<String> makeHash(String uPass, String salt) {
    	
    	//Create a hash from the unsecured password
    	char[] chars = uPass.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, java.lang.Character.MIN_VALUE);

        try {
          SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
          byte[] securePassword = fac.generateSecret(spec).getEncoded();
          return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
          System.err.println("Exception encountered in hashPassword()");
          return Optional.empty();

        } finally {
          spec.clearPassword();
        }
    }
    
    /*private static void savePasswords (String userName, String password, String salt) {
    	
    }*/
    
    //Shelby    
    
    //May need to remove this until we can determine if it's needed.
    @FXML
    private void handleGuestLogin() {
    	mainApp.showCharacterOverview();
    }
    /*
     public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }    
    
    boolean checkForUser(String user, String pass) {
    	boolean success = false;
    	return success;
    }
    
    @FXML
    private void handleLogin() {
    	String user = userField.getText();
    	String pass = passField.getText();
    	System.out.println(user);
    	System.out.println(pass);
    	if (checkForUser(user,pass)) {mainApp.showCharacterOverview();}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Error");
        	alert.setHeaderText("We were unable to log this user in.");
        	alert.setContentText("Please try again or, if this is your first time here, create a user now.");
        	alert.showAndWait();
        	}
    
    }
    
    @FXML
    private void handleCreateUser() {
    	mainApp.showCreateUser();
    }
    
    @FXML
    private void handleGuestLogin() {
    	mainApp.showCharacterOverview();
    }
      
     */
}
