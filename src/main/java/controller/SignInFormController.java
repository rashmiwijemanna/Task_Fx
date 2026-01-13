package controller;

import db.DBConnection;
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
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    void btnSignIn(ActionEvent event) throws SQLException {
        String email=emailTxt.getText();
        String password= passwordTxt.getText();

        if(email.isEmpty() || password.isEmpty()){
            errorPop("Please fill in all feilds");
            return;
        }

        String sql="SELECT password_hash FROM User WHERE email = ?";
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            String storedHash = resultSet.getString("password_hash");

            if (BCrypt.checkpw(password, storedHash)) {
                System.out.println("login success");
                navigateTo(event, "/view/DashBoard.fxml");


            } else {
                errorPop("Incorrect password");

            }

        }else {
            errorPop("No user found with this email");

        }

    }
    private void navigateTo(ActionEvent event, String path){
        URL resource=getClass().getResource(path);
        if (resource == null){
            System.out.println("fxml file not found at" + path);
            return;
        }
        try {
            Parent root=FXMLLoader.load(resource);
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void errorPop(String message){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    @FXML
    void btnSignUp(ActionEvent event) throws IOException {
        navigateTo(event,"/view/SignUp.fxml");


    }

}
