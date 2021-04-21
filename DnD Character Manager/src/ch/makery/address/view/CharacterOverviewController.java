package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import ch.makery.address.MainApp;
import ch.makery.address.model.Character;
import ch.makery.address.model.Player;

public class CharacterOverviewController {
    @FXML private TableView<Character> personTable;
    @FXML private TableColumn<Character, String> charNameColumn;
    @FXML private TableColumn<Character, String> playerColumn;
    @FXML private TableColumn<Character, String> classColumn;
    @FXML private TableColumn<Character, String> raceColumn;

    @FXML private Label CharNameLabel;
    @FXML private Label RaceLabel;
    @FXML private Label AlignmentLabel;
    @FXML private Label ExperienceLabel;
    @FXML private Label BackgroundLabel;
    @FXML private Label ClassLevelLabel;
    
    //Stat labels
    @FXML private Label StrengthLabel;
    @FXML private Label DexterityLabel;
    @FXML private Label ConstitutionLabel;
    @FXML private Label IntelligenceLabel;
    @FXML private Label WisdomLabel;
    @FXML private Label CharismaLabel;
    
    @FXML private Label ProficiencyBonusLabel;
    @FXML private Label SavingThrowsLabel;
    @FXML private Label SkillsLabel;
    
    @FXML private Label ArmorLabel;
    @FXML private Label InitiativeLabel;
    @FXML private Label SpeedLabel;
    @FXML private Label CurrentHPLabel;
    @FXML private Label TempHPLabel;
    @FXML private Label HitDiceLabel;
    @FXML private Label DeathSavesLabel;
    @FXML private Label AttacksLabel;
    @FXML private Label OtherProfsLabel;
    
    @FXML private Label FeaturesLabel;
    @FXML private Label EquipmentLabel;
    @FXML private Label BackstoryLabel;
   
    
    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public CharacterOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        charNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().charNameProperty());
        raceColumn.setCellValueFactory(
                cellData -> cellData.getValue().RaceProperty());
        classColumn.setCellValueFactory(
                cellData -> cellData.getValue().ClassLevelProperty());
        playerColumn.setCellValueFactory(
                cellData -> cellData.getValue().PlayerProperty(cellData.getValue()));

        // Clear person details.
        //showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getCharacterData());
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Character person) {
        if (person != null) {
        	//Get the current player from main class
        	Player currentPlayer = MainApp.currentPlayer;
        	
            // Fill the labels with info from the person object.
            CharNameLabel.setText(person.getFirstName());
            RaceLabel.setText(person.getRace());
            AlignmentLabel.setText(person.getAlignment());
            ExperienceLabel.setText(Integer.toString(person.getExperience()));
            ClassLevelLabel.setText(person.getClassLevel());
            BackgroundLabel.setText(person.getBackground());
            SavingThrowsLabel.setText(person.getSavingThrows());
            SkillsLabel.setText(person.getSkills());
            HitDiceLabel.setText(person.getHitDice());
            DeathSavesLabel.setText(person.getDeathSaves());
            AttacksLabel.setText(person.getAttacks());
            OtherProfsLabel.setText(person.getOtherProfs());
            FeaturesLabel.setText(person.getFeaturesTraits());
            EquipmentLabel.setText(person.getEquipment());
            BackstoryLabel.setText(person.getBackstory());

            int[] temp = person.getStats();
            StrengthLabel.setText(Integer.toString(temp[0]));
            DexterityLabel.setText(Integer.toString(temp[1]));
            ConstitutionLabel.setText(Integer.toString(temp[2]));
            IntelligenceLabel.setText(Integer.toString(temp[3]));
            WisdomLabel.setText(Integer.toString(temp[4]));
            CharismaLabel.setText(Integer.toString(temp[5]));
            ProficiencyBonusLabel.setText("+"+Integer.toString(temp[6]));
            ArmorLabel.setText(Integer.toString(temp[7]));
            InitiativeLabel.setText("+"+Integer.toString(temp[8]));
            SpeedLabel.setText(Integer.toString(temp[9]));
            CurrentHPLabel.setText(Integer.toString(temp[10]));
            TempHPLabel.setText("+"+Integer.toString(temp[11]));
        } else {
            // Person is null, remove all the text.
            CharNameLabel.setText(" n");
            RaceLabel.setText(" n");
            AlignmentLabel.setText(" n");
            BackgroundLabel.setText(" n");
            ExperienceLabel.setText(" n");
            ClassLevelLabel.setText(" n");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    public boolean showCharacterEditDialog(Character character) {
        if(MainApp.currentPlayer == null) {
        	Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Credentials");
            alert.setHeaderText("You are not authorized to edit/create.");
            alert.setContentText("Please login first.");

            alert.showAndWait();
        	return false;
        }
    	
    	ArrayList<String> al = MainApp.currentPlayer.getCharIDs();
    	String id;
        try {
        	id = character.getCharID().substring(0, 2);
        	//If the selected character belongs to the current player, or if the current player is a GM
        	if(al.get(0).substring(0, 2).equals(character.getCharID().substring(0, 2)) || MainApp.currentPlayer.getPlayerType() == 1) 
        		return editUser(character);
        	else {
        		Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Invalid Credentials");
                alert.setHeaderText("You are not authorized to edit this usesr");
                alert.setContentText("Please select one of your characters.");

                alert.showAndWait();        	
        		return false;
        	}
        }
        catch (NullPointerException npe) { //will throw this if the 
        	return editUser(character);
        }
    }
    
    //Edit the character's data
    public boolean editUser(Character character) {
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CharacterEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            
            dialogStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
            
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the character into the controller.
            CharacterEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(character);
            controller.setMainApp(this);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //When the delete button is pressed
    void handleDeleteCharacter() {
    	//Getting the selected character from the table.
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        Character selectedPerson = personTable.getItems().get(personTable.getSelectionModel().getSelectedIndex());
        
        //Ensure a character was selected from the table
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
            Character.deleteCharacter(selectedPerson, mainApp);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleEditCharacter() { 
    	try{
    		showCharacterEditDialog(personTable.getItems().get(personTable.getSelectionModel().getSelectedIndex()));
    	} catch(ArrayIndexOutOfBoundsException e) {
    		//Do nothing
    	}
    }
    
    @FXML
    private void handleCreateCharacter() {
    	Character bob = new Character();
    	personTable.getItems().add(bob);
    	showCharacterEditDialog(bob);
    }
    
    @FXML
    private void handleRefresh() {
    	
    }
}