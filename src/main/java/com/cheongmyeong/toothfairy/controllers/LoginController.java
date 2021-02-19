package com.cheongmyeong.toothfairy.controllers;

import org.springframework.stereotype.Controller;
import com.cheongmyeong.toothfairy.config.StageManager;
import com.cheongmyeong.toothfairy.views.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


/**
 *
 * @author pc
 */
@Controller
public class LoginController {
  
     @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button Login;
    
    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    private void Login(ActionEvent event){
        if("admin".equals(username.getText()) && "password".equals(password.getText()))
        {
           stageManager.switchScene(FxmlView.MAINUI);
        }
        else
        {
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Login Failed! Please try again");

            alert.showAndWait();
           
        }
    }
    
}
