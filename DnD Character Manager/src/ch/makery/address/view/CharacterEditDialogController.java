package ch.makery.address.view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

import ch.makery.address.model.Character;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class CharacterEditDialogController {

    @FXML private TextField firstNameField;
    @FXML private TextField RaceField;
    @FXML private TextField BackgroundField;
    @FXML private TextField AlignmentField;
    @FXML private TextField ExperienceField;
    @FXML private TextField ClassLevelField;
    @FXML private TextField strengthField;
    @FXML private TextField dexterityField;
    @FXML private TextField constitutionField;
    @FXML private TextField intelligenceField;
    @FXML private TextField wisdomField;
    @FXML private TextField charismaField;
    @FXML private TextField savingThrowsField;
    @FXML private TextField skillsField;
    @FXML private TextField attacksField;
    @FXML private TextField hitDiceField;
	@FXML private TextField deathSavesField;
	@FXML private TextField otherProfsField;
	@FXML private TextField featuresTraitsField;
	@FXML private TextField equipmentField;
	@FXML private TextField backstoryField;
	@FXML private TextField profBonusField;
    @FXML private TextField armorField;
    @FXML private TextField initiativeField;
    @FXML private TextField speedField;
    @FXML private TextField currentHPField;
    @FXML private TextField tempHPField;

    private CharacterOverviewController COC;
    private Stage dialogStage;
    private Character person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Character person) {
    	this.person = person;
        firstNameField.setText(person.getFirstName());
        RaceField.setText(person.getRace());
        BackgroundField.setText(person.getBackground());
        AlignmentField.setText(person.getAlignment());
        ClassLevelField.setText(person.getClassLevel());
        ExperienceField.setText(Integer.toString(person.getExperience()));
        hitDiceField.setText(person.getHitDice());
        savingThrowsField.setText(person.getSavingThrows());
        skillsField.setText(person.getSkills());
        deathSavesField.setText(person.getDeathSaves());
        attacksField.setText(person.getAttacks());
        otherProfsField.setText(person.getOtherProfs());
        featuresTraitsField.setText(person.getFeaturesTraits());
        equipmentField.setText(person.getEquipment());
        backstoryField.setText(person.getBackstory());
        
        int [] temp = person.getStats();
        strengthField.setText(Integer.toString(temp[0]));
        dexterityField.setText(Integer.toString(temp[1]));
        constitutionField.setText(Integer.toString(temp[2]));
        intelligenceField.setText(Integer.toString(temp[3]));
        wisdomField.setText(Integer.toString(temp[4]));
        charismaField.setText(Integer.toString(temp[5]));
    	profBonusField.setText(Integer.toString(temp[6]));
        armorField.setText(Integer.toString(temp[7]));
        initiativeField.setText(Integer.toString(temp[8]));
        speedField.setText(Integer.toString(temp[9]));
        currentHPField.setText(Integer.toString(temp[10]));
        tempHPField.setText(Integer.toString(temp[11]));
    
    }
    

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setRace(RaceField.getText());
            person.setBackground(BackgroundField.getText());
            person.setAlignment(AlignmentField.getText());
            person.setClassLevel(ClassLevelField.getText());
            person.setExperience(Integer.parseInt(ExperienceField.getText()));
            person.setHitDice(hitDiceField.getText());
            person.setSavingThrows(savingThrowsField.getText());
            person.setSkills(skillsField.getText());
            person.setDeathSaves(deathSavesField.getText());
            person.setAttacks(attacksField.getText());
            person.setOtherProfs(otherProfsField.getText());
            person.setFeaturesTraits(featuresTraitsField.getText());
            person.setEquipment(equipmentField.getText());
            person.setBackstory(backstoryField.getText());
            
            int [] temp = new int[]{Integer.parseInt(strengthField.getText())
            ,Integer.parseInt(dexterityField.getText()),
            Integer.parseInt(constitutionField.getText()),
            Integer.parseInt(intelligenceField.getText()),
            Integer.parseInt(wisdomField.getText()),
            Integer.parseInt(charismaField.getText()),
            Integer.parseInt(profBonusField.getText()),
            Integer.parseInt(armorField.getText()),
            Integer.parseInt(initiativeField.getText()),
            Integer.parseInt(speedField.getText()),
            Integer.parseInt(currentHPField.getText()),
            Integer.parseInt(tempHPField.getText())};
            person.setStats(temp);
            okClicked = true;
            dialogStage.close();
        }
        
        //Get all the current characters
        ArrayList<Character> characters = Character.getAllChars();
        
        int count = 0, orgSize = characters.size();
        
        //Find the correct person
        for(int i = 0; i < characters.size(); i++) {
        	if(characters.get(i).getCharID().equals(person.getCharID())) 
        		characters.set(i, person);
        	else
        		count++;
        }
                
        //If it made it through the loop without changing a value then create a new one.
        if(count == characters.size())
        	Character.createCharacter(person);
        else
        	Character.saveData(characters);
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
    //	if(COC.getSelectedCharacter().equals(new Character())) {COC.handleDeleteCharacter();}
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (RaceField.getText() == null || RaceField.getText().length() == 0) {
            errorMessage += "No valid Race!\n"; 
        }
        if (BackgroundField.getText() == null || BackgroundField.getText().length() == 0) {
            errorMessage += "No valid Background!\n"; 
        }
        if (AlignmentField.getText() == null || AlignmentField.getText().length() == 0) {
            errorMessage += "No valid Alignment!\n"; 
        }

        if (ExperienceField.getText() == null || ExperienceField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(ExperienceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Experience (must be an integer)!\n"; 
            }
        }

        if (ClassLevelField.getText() == null || ClassLevelField.getText().length() == 0) {
            errorMessage += "No valid ClassLevel!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    @FXML
    private void handleDeleteCharacter() {
    	Alert alert = new Alert(AlertType.CONFIRMATION,"You are about to PERMANENTLY delete this character. Are you SURE you want to continue?");
    	alert.setHeaderText("CAUTION!");
    	alert.showAndWait().ifPresent(response -> {
    		if (response == ButtonType.OK) {
    			COC.handleDeleteCharacter();
    	    	dialogStage.close();
    		}
    	});
    }
    
	public void setMainApp(CharacterOverviewController characterOverviewController) {
		this.COC = characterOverviewController;
	}
}