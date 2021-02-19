/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheongmyeong.toothfairy.controllers;

import com.cheongmyeong.toothfairy.config.StageManager;
import com.cheongmyeong.toothfairy.views.FxmlView;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

/**
 *
 * @author Sapo
 */
@Controller
public class MainUIController implements Initializable {
   
     @FXML
    private AnchorPane ap;

    @FXML
    private ImageView patientClick;
   
    @FXML
    private ImageView dentistClick;

    @FXML
    private ImageView appointmentClick;
    
    @FXML
    private Button login;

    @FXML
    void appointmentClick(MouseEvent event) {
    stageManager.switchScene(FxmlView.APPOINTMENT);
    }

    @FXML
    void patientClick(MouseEvent event) {
        stageManager.switchScene(FxmlView.PATIENT);
    }

    @FXML
    void staffClick(MouseEvent event) {
        
        stageManager.switchScene(FxmlView.STAFF);
    }
    @FXML
    void login(MouseEvent event) {
        stageManager.switchScene(FxmlView.LOGIN);
    }
  //  @FXML
  //  private ImageView patient;
   // @FXML
  //  private BorderPane bp;
    
  // @FXML
  // private Button p1;
  //   @FXML
   // private Button p2;
   //   @FXML
   // private Button p3;
//    
     @Lazy
    @Autowired
    private StageManager stageManager;
//    
   @Override
   public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//
//    @FXML
//    private void home(MouseEvent event) {
//        bp.setCenter(ap);
//    }
//   
//    @FXML
//    private void page1(MouseEvent event) {
////       loadPage("page1");
//         stageManager.switchScene(FxmlView.PATIENT);
//    }
//
//    @FXML
//    private void page2(MouseEvent event) {
////        loadPage("page2");
//stageManager.switchScene(FxmlView.STAFF);
//    }
//
//    @FXML
//    private void page3(MouseEvent event) {
////        loadPage("page3");
//stageManager.switchScene(FxmlView.APPOINTMENT);
//    }
//   }
//    private void loadPage(String page){
//        Parent root = null;
//        
//        try {
//           root = FXMLLoader.load(getClass().getResource(page +".fxml"));
//       } catch (IOException ex) {
//           Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
//       }
       //ap.(root);
    }
}
