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

public class RegistrationController {


    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Window owner = submitButton.getScene().getWindow();
        if (nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        User user = User.builder().name(nameField.getText()).password(passwordField.getText()).email(emailField.getText()).build();

        RestServiceConsumer restServiceConsumer = new RestServiceConsumer();
        restServiceConsumer.register(user);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent loginPage = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(loginPage, 600, 800);
        currentStage.setScene(scene);
        currentStage.show();
    }

    @FXML
    protected void handleCancelButtonAction(ActionEvent event) throws IOException {


        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent loginPage = FXMLLoader.load(getClass().getResource("/fxml/loginPage.fxml"));
        Scene scene = new Scene(loginPage, 600, 800);
        currentStage.setScene(scene);
        currentStage.show();
    }
}