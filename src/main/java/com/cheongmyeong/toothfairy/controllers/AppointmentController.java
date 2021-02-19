/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheongmyeong.toothfairy.controllers;

import com.cheongmyeong.toothfairy.config.StageManager;
import com.cheongmyeong.toothfairy.models.Appointment;
import com.cheongmyeong.toothfairy.services.IAppointmentService;
import com.cheongmyeong.toothfairy.views.FxmlView;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
public class AppointmentController implements Initializable {

   @FXML
   private Label appointmentId;
   
   @FXML
   private TextField patientName;
  
   @FXML
   private TextField staffName;
   
   @FXML
   private DatePicker schedule;
   
   @FXML
   private RadioButton rbNew;
   
   @FXML
   private ToggleGroup status;
   
   @FXML
   private RadioButton rbClose;
   
   @FXML
   private Button add;
   
   @FXML
   private Button delete;
   
   @FXML
   private Button reset;
   
   @FXML
   private ImageView home;
   
   @FXML
   private TableView<Appointment> appointmentTable;

   @FXML
   private TableColumn<Appointment, Long> colAppointmentId;
  
   @FXML
   private TableColumn<Appointment, String> colPName;
   
   @FXML
   private TableColumn<Appointment, String> colSName;
   
   @FXML
   private TableColumn<Appointment, String> colSched;
   
   @FXML
   private TableColumn<Appointment, String> colStatus;
   
   @FXML
   private TableColumn<Appointment, Boolean> colEdit;
   
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private IAppointmentService appointmentService;
    
    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        appointmentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        
        loadAppointmentDetails();
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
        
        if (validate("Patient Name", getPatientName(), "([a-zA-Z]{3,50}\\s*)+")
                && validate("Staff Name", getStaffName(), "([a-zA-Z]{3,50}\\s*)+"))
                {
                 if (appointmentId.getText() == null || "".equals(appointmentId.getText())) {
                if (true) {

                    Appointment appointment = new Appointment();
                    appointment.setPatientName(getPatientName());
                    appointment.setStaffName(getStaffName());
                    appointment.setSchedule(getSchedule());
                    appointment.setStatus(getStatus());

                    Appointment newAppointment = appointmentService.save(appointment);

                    addAlert(newAppointment);
                    
                }

            } else {
                Appointment appointment = appointmentService.find(Long.parseLong(appointmentId.getText()));
                appointment.setPatientName(getPatientName());
                appointment.setStaffName(getStaffName());
                appointment.setSchedule(getSchedule());
                appointment.setStatus(getStatus());
                Appointment updatedStaff = appointmentService.update(appointment);
                updateAlert(updatedStaff);
            }

            clearFields();
            loadAppointmentDetails();
        }

    }
    
    @FXML
    private void delete(ActionEvent event) {
        List<Appointment> appointment = appointmentTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected data?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            appointmentService.deleteInBatch(appointment);
           
        }
            loadAppointmentDetails();
        
        
    }
    
    private void clearFields() {
        appointmentId.setText(null);
        patientName.clear();
        staffName.clear();
        rbNew.setSelected(true);
        rbClose.setSelected(false);
        schedule.getEditor().clear();
        
    }
    private void addAlert(Appointment appointment) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment added successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The patient " + appointment.getPatientName() + " and assigned staff, " + appointment.getStaffName() + 
        " has been added and its schedule:" + appointment.getSchedule() + ".\nId is " + appointment.getId() + ".");
        alert.showAndWait();
    }
     private void updateAlert(Appointment appointment) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The patient " + appointment.getPatientName() + " and assigned staff, " + appointment.getStaffName() + " has been updated.");
        alert.showAndWait();
    }
    private String getPatientName() {
        return patientName.getText();
    }
    
    private String getStaffName(){
        return staffName.getText();
    }
    
    private LocalDate getSchedule(){
        return schedule.getValue();
    }
    
    private String getStatus(){
        return rbNew.isSelected() ? "New" : "Close" ;
    }
   
    
  
    
    private void setColumnProperties() {
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        colSched.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colEdit.setCellFactory(cellFactory);
    }
    Callback<TableColumn<Appointment, Boolean>, TableCell<Appointment, Boolean>> cellFactory
            = new Callback<TableColumn<Appointment, Boolean>, TableCell<Appointment, Boolean>>() {
        @Override
        public TableCell<Appointment, Boolean> call(final TableColumn<Appointment, Boolean> param) {
            final TableCell<Appointment, Boolean> cell = new TableCell<Appointment, Boolean>() {
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
                            Appointment appointment = getTableView().getItems().get(getIndex());
                            updatedAppointment(appointment);
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

                private void updatedAppointment(Appointment appointment) {
                    appointmentId.setText(Long.toString(appointment.getId()));
                    patientName.setText(appointment.getPatientName());
                    staffName.setText(appointment.getStaffName());
                    schedule.setValue(appointment.getSchedule());
                    if (appointment.getStatus().equals("New")) {
                        rbNew.setSelected(true);
                    } 
                    else {
                        rbClose.setSelected(true);
                    }
                }
            };
            return cell;
        }
    };

    private void loadAppointmentDetails() {
        appointmentList.clear();
        appointmentList.addAll(appointmentService.findAll());

        appointmentTable.setItems(appointmentList);
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

            
            