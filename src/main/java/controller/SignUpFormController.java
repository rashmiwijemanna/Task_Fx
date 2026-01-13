package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class SignUpFormController {

    @FXML
    private PasswordField emailTxt;

    @FXML
    private TextField firstnameTxt;

    @FXML
    private TextField lastNameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private PasswordField reEnterPasswordTxt;

    @FXML
    private Button signin;

    @FXML
    private Button signin1;
    Stage stage=new Stage();

    @FXML
    void backToLoginBtn(ActionEvent event) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"))));
        stage.show();


    }

    @FXML
    void registerBtn(ActionEvent event) throws SQLException {
        String firstName=firstnameTxt.getText();
        String lastName=lastNameTxt.getText();
        String email=emailTxt.getText();
        String password=passwordTxt.getText();
        String reEnterPassword=reEnterPasswordTxt.getText();

        if(firstName.isEmpty()|lastName.isEmpty()){
            errorPop("First name and last name are required");
            return;
        }

        if(!email.endsWith("@gmail.com")){
            errorPop("Email must ends with @gmail.com");
            return;
        }

        if(!password.equals(reEnterPassword)){
            errorPop("password dont match enter again");
            return;
        }

        String passwordCheck="^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        if (!password.matches(reEnterPassword)){
            errorPop("password must have at least 8 characters . UPPER, LOWER, Symbols");
            return;
        }

        String hashedPassword= org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        Connection connection= DBConnection.getInstance().getConnection();
        String sql="INSERT INTO User(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2,lastName);
        preparedStatement.setString(3,email);
        preparedStatement.setString(4,hashedPassword);
        preparedStatement.executeUpdate();



    }
    private void errorPop(String message){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void navigateToLogin() throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"))));
        stage.show();
    }



//    private void signUpControlling(){
//
//
//
//    }



}
