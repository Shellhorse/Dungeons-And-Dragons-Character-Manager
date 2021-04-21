package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;
import ch.makery.address.model.Character;
import ch.makery.address.model.Player;

	
/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;
    
    @FXML
    private TableView<Character> personTable;
    @FXML private Menu partyMenu;
    @FXML private Menu gmMenu;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void showPlayerMenu() {
    	partyMenu.setVisible(true);
    }
    
    public void showGMMenu() {
    	gmMenu.setVisible(true);
    }

    
    
    
    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("About");
    	alert.setHeaderText("About");
    	alert.setContentText("DnD Character Manager created by Austin Montgomery, "
    			+ "Benjamin Dudley, Jordan Shellhorse, and Shelby Parrish for Valdosta State University.");

    	alert.showAndWait();
    }

    @FXML
    private void handleLogout() {
    	MainApp.currentPlayer = null;
    	mainApp.showLogin();
    }
    
    @FXML
    private void handleCreateGM() {
    	
    	//if the user isn't logged in, or user not a gm
    	if(MainApp.currentPlayer == null || !(MainApp.currentPlayer.getPlayerType() == 1)) {
    		Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText("You are not authorized to do this");
            alert.setContentText("Please login to a GM account.");
            
            alert.showAndWait();
    	}    		
    	else {
    		mainApp.showCreateGM();
    	}
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteCharacter() {
    	try {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        }
        else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    	}
    	catch (Exception e){
    		System.out.println("Failed, exception: "+e);
    	}
    }
}