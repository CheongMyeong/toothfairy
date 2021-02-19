/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheongmyeong.toothfairy.controllers;

import com.cheongmyeong.toothfairy.config.StageManager;
import com.cheongmyeong.toothfairy.services.IStaffService;
import com.cheongmyeong.toothfairy.models.Staff;
import com.cheongmyeong.toothfairy.views.FxmlView;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * FXML Controller class
 *
 * @author Sapo
 */
@Controller
public class StaffController implements Initializable {
    
   @FXML
   private Label staffId;
   
   @FXML
   private TextField firstName;
  
   @FXML
   private TextField lastName;
   
   @FXML
   private RadioButton Dr;
   
   @FXML
   private ToggleGroup position;
   
   @FXML
   private RadioButton Nurse;
   
   @FXML
   private Button add;
   
   @FXML
   private Button delete;
   
   @FXML
   private Button reset;
   
   @FXML
   private ImageView home;
   
  @FXML
   private TableView<Staff> staffTable;

   @FXML
   private TableColumn<Staff, Long> colId;
  
   @FXML
   private TableColumn<Staff, String> colFirstName;
   
   @FXML
   private TableColumn<Staff, String> colLastName;
   
   @FXML
   private TableColumn<Staff, String> colPosition;
   
   @FXML
    private TableColumn<Staff, Boolean> colEdit;
   
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private IStaffService staffService;
    
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        staffTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        
        loadStaffDetails();
    }
    
    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }
    
     @FXML
    void home(MouseEvent event) {
        stageManager.switchScene(FxmlView.MAINUI);
    }
    
    @FXML
    private void add(ActionEvent event) {
        
        if (validate("First Name", getFirstName(), "([a-zA-Z]{3,30}\\s*)+")
                && validate("Last Name", getLastName(), "([a-zA-Z]{3,30}\\s*)"))
                {
                 if (staffId.getText() == null || "".equals(staffId.getText())) {
                if (true) {

                    Staff staff = new Staff();
                    staff.setFirstName(getFirstName());
                    staff.setLastName(getLastName());
                    staff.setPosition(getPosition());
                    

                    Staff newUser = staffService.save(staff);

                    addAlert(newUser);
                }

            } else {
                Staff staff = staffService.find(Long.parseLong(staffId.getText()));
                staff.setFirstName(getFirstName());
                staff.setLastName(getLastName());
                staff.setPosition(getPosition());
                Staff updatedStaff = staffService.update(staff);
                updateAlert(updatedStaff);
            }

            clearFields();
            loadStaffDetails();
        }

    }
    
    @FXML
    private void delete(ActionEvent event) {
        List<Staff> staff = staffTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected data?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            staffService.deleteInBatch(staff);
           
        }
            loadStaffDetails();
        
        
    }
    
    private void clearFields() {
        staffId.setText(null);
        firstName.clear();
        lastName.clear();
        Dr.setSelected(true);
        Nurse.setSelected(false);
    }
    private void addAlert(Staff staff) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Staff added successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The staff " + staff.getFirstName() + " " + staff.getLastName() + 
        " has been added\n" + "Id is " + staff.getId() + ".");
        alert.showAndWait();
    }
     private void updateAlert(Staff staff) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Staff updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The staff " + staff.getFirstName() + " " + staff.getLastName() + " has been updated.");
        alert.showAndWait();
    }
    private String getFirstName() {
        return firstName.getText();
    }
    
    private String getLastName(){
        return lastName.getText();
    }
    public String getPositionDr(String position){
        return (position.equals("Dr")) ? "Dr." : "Nurse";
    }
    public String getPositionNurse(String position){
        return (position.equals("Nurse")) ? "Dr." : "Nurse";
    }
    private String getPosition(){
        return Dr.isSelected() ? "Dr." : "Nurse";
    }
  
    
    private void setColumnProperties() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEdit.setCellFactory(cellFactory);
    }
    Callback<TableColumn<Staff, Boolean>, TableCell<Staff, Boolean>> cellFactory
            = new Callback<TableColumn<Staff, Boolean>, TableCell<Staff, Boolean>>() {
        @Override
        public TableCell<Staff, Boolean> call(final TableColumn<Staff, Boolean> param) {
            final TableCell<Staff, Boolean> cell = new TableCell<Staff, Boolean>() {
              Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEdit.setOnAction(e -> {
                            Staff staff = getTableView().getItems().get(getIndex());
                            updateStaff(staff);
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                private void updateStaff(Staff staff) {
                    staffId.setText(Long.toString(staff.getId()));
                    firstName.setText(staff.getFirstName());
                    lastName.setText(staff.getLastName());
                    if (staff.getPosition().equals("Dr")) {
                        Dr.setSelected(true);
                    } else {
                        Nurse.setSelected(true);
                    }
                }
            };
            return cell;
        }
    };

    private void loadStaffDetails() {
        staffList.clear();
        staffList.addAll(staffService.findAll());

        staffTable.setItems(staffList);
    }
    
    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }
     private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) {
            alert.setContentText("Please Select " + field);
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }
}