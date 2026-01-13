package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashBoardFormController {

    @FXML
    private Button logoutButton;

    @FXML
    private Label userTxt;

    @FXML
    private Label welcomeLabel;

    @FXML
    void handleLogout(ActionEvent event) {

    }

    public void setWelcomeMessage(String name){
        userTxt.setText(name +"!");
    }

}
