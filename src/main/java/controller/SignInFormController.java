package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInFormController {
    Stage stage=new Stage();

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button signin;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    void btnSignIn(ActionEvent event) {

    }

    @FXML
    void btnSignUp(ActionEvent event) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"))));
        stage.show();


    }

}
