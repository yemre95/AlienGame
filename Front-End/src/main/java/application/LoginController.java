package application;

import ceng453.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {

    private static final String registrationFormUrl = "/fxml/registrationForm.fxml";
    private static final String mainMenuUrl = "/fxml/mainMenu.fxml";


    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) throws IOException {

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent registerPage = FXMLLoader.load(getClass().getResource(registrationFormUrl));
        Scene scene = new Scene(registerPage, 600, 800);
        currentStage.setScene(scene);
        currentStage.show();
    }

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        Window owner = loginButton.getScene().getWindow();
        if (nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        User user = User.builder().name(nameField.getText()).password(passwordField.getText()).build();

        RestServiceConsumer restServiceConsumer = new RestServiceConsumer();
        User loggedInUser = restServiceConsumer.login(user);
        if (loggedInUser == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Username or Password is Wrong!");
        } else {
            Integer id = loggedInUser.getId();
            String userId = id.toString();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent mainMenu = FXMLLoader.load(getClass().getResource(mainMenuUrl));
            mainMenu.setId(userId);
            Scene scene = new Scene(mainMenu, 600, 800);
            currentStage.setScene(scene);
            currentStage.show();
        }
        
    }
}