package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    @FXML
    private Button logoutButton;

    @FXML
    private Label userTxt;

    @FXML
    private Label welcomeLabel;

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setWelcomeMessage(String name){
        userTxt.setText(name +"!");
    }

}
