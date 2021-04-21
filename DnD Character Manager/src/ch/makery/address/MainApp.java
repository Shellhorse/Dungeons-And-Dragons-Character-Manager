package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.text.html.HTMLEditorKit.LinkController;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import ch.makery.address.view.LoginController;
import ch.makery.address.model.Character;
import ch.makery.address.model.CharacterListWrapper;
import ch.makery.address.model.Player;
import ch.makery.address.view.CharacterEditDialogController;
import ch.makery.address.view.CharacterOverviewController;
import ch.makery.address.view.CreateGMController;
import ch.makery.address.view.LoginController;
import ch.makery.address.view.CreateUserController;
import ch.makery.address.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    //Stores the player currently logged in
    public static Player currentPlayer;

    /**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Character> characterData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data -- Sample data now comes from files
				
		int[] stats = {10,15,8,18,12,20};
		ArrayList <Character> tempChars = Character.getAllChars();
		
		for(int i = 0; i < tempChars.size(); i++) 
			characterData.add(tempChars.get(i));
	}
  
	/**
	 * Returns the data as an observable list of Characters. 
	 * @return
	 */
	public ObservableList<Character> getCharacterData() {
		return characterData;
	}
    
    @Override
    public void start(Stage primaryStage) {
    	//Change this to a login screen, allow user to login, then retrieve data based on the user
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("D&D Character Manager");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/Square Logo.png"));

        initRootLayout();

        showLogin();
        //showCharacterOverview();
    }
    
    /**
     * Initializes the root layout and tries to load the last opened
     * character file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLogin() {
        try {
            // Load character overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            
            // Set character overview into the center of root layout.
            rootLayout.setCenter(login);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCreateUser() {
        try {
            // Load character overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CreateUser.fxml"));
            AnchorPane createUser = (AnchorPane) loader.load();
            
            // Set character overview into the center of root layout.
            rootLayout.setCenter(createUser);

            // Give the controller access to the main app.
            CreateUserController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the character overview inside the root layout.
     */
    public void showCharacterOverview() {
        try {
            // Load character overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CharacterOverview.fxml"));
            AnchorPane characterOverview = (AnchorPane) loader.load();
            
            // Set character overview into the center of root layout.
            rootLayout.setCenter(characterOverview);

            // Give the controller access to the main app.
            CharacterOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCreateGM() {
        try {
            // Load character overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CreateGM.fxml"));
            AnchorPane characterOverview = (AnchorPane) loader.load();
            
            // Set character overview into the center of root layout.
            rootLayout.setCenter(characterOverview);

            // Give the controller access to the main app.
            CreateGMController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setCharacterFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("D&D Character Manager - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("D&D Character Manager");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}