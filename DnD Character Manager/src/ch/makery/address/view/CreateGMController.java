package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ch.makery.address.MainApp;
import ch.makery.address.model.Character;
import ch.makery.address.model.Player;

public class CreateGMController {
	
	 @FXML
	 private Label mainLabel;
	 @FXML
	 private TextField userField;
	 @FXML
	 private PasswordField passField;
	 @FXML
	 private TextField nameField;
	 @FXML
	 private TextField charField;
	 @FXML
	 private Label playerType;
	 
	 
	 private MainApp mainApp;
	 
	 public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	    }
	 
	 
	 @FXML
	 private void handleFinalizePlayer() {
		 Player.createNewPlayer(nameField.getText(), userField.getText(), passField.getText(), Integer.parseInt(charField.getText()));
		 mainApp.showLogin();
	 }
	 
	 @FXML
	 private void handleFinalizeGM() {
		 Player.createNewGM(nameField.getText(), userField.getText(), passField.getText(), Integer.parseInt(charField.getText()));
		 mainApp.showLogin();
	 }
	 
	 @FXML
	 private void handleCancel() {
		 mainApp.showCharacterOverview();
	 }
	    

}
