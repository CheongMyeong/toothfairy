package com.cheongmyeong.toothfairy.controllers;

import com.cheongmyeong.toothfairy.config.StageManager;
import org.springframework.stereotype.Controller;
import com.cheongmyeong.toothfairy.models.Patient;
import com.cheongmyeong.toothfairy.services.IPatientService;
import com.cheongmyeong.toothfairy.views.FxmlView;
import java.net.URL;
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

@Controller
public class PatientController implements Initializable{

   @FXML
   private Label patientId;
   
   @FXML
   private TextField firstName;
  
   @FXML
   private TextField lastName;
   
   @FXML
   private TextField address;
   
   @FXML
   private TextField treatment;
   
   @FXML
   private RadioButton rbMale;
   
   @FXML
   private ToggleGroup sex;
   
   @FXML
   private RadioButton rbFemale;
   
   @FXML
   private TextField contactNum;
   
   @FXML
   private TextField amount;
   
   @FXML
   private RadioButton rbWalkin;
   
   @FXML
   private ToggleGroup status;
   
   @FXML
   private RadioButton rbByApp;
   
   @FXML
   private Button add;
   
   @FXML
   private Button delete;
   
   @FXML
   private Button reset;
   
   @FXML
   private ImageView home;
   
   @FXML
   private TableView<Patient> patientTable;

   @FXML
   private TableColumn<Patient, Long> colPatientId;
  
   @FXML
   private TableColumn<Patient, String> colFirstName;
   
   @FXML
   private TableColumn<Patient, String> colLastName;
   
   @FXML
   private TableColumn<Patient, String> colAddress;
   
   @FXML
   private TableColumn<Patient, String> colTreatment;
   
   @FXML
   private TableColumn<Patient, String> colSex;
      
   @FXML
   private TableColumn<Patient, Long> colContactNum;
   
   @FXML
   private TableColumn<Patient, Double> colAmount;
   
   @FXML
   private TableColumn<Patient, String> colStatus;
   
   @FXML
    private TableColumn<Patient, Boolean> colEdit;
   
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private IPatientService patientService;
    
    private ObservableList<Patient> patientList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        patientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        
        loadPatientDetails();
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
                 if (patientId.getText() == null || "".equals(patientId.getText())) {
                if (true) {

                    Patient patient = new Patient();
                    patient.setFirstName(getFirstName());
                    patient.setLastName(getLastName());
                    patient.setAddress(getAddress());
                    patient.setTreatment(getTreatment());
                    patient.setSex(getSex());
                    patient.setContactNumber(getContactNumber());
                    patient.setAmount(getAmount());
                    patient.setStatus(getStatus());

                    Patient newPatient = patientService.save(patient);

                    addAlert(newPatient);
                }

            } else {
                Patient patient = patientService.find(Long.parseLong(patientId.getText()));
                patient.setFirstName(getFirstName());
                patient.setLastName(getLastName());
                patient.setAddress(getAddress());
                patient.setTreatment(getTreatment());
                patient.setSex(getSex());
                patient.setContactNumber(getContactNumber());
                patient.setAmount(getAmount());
                patient.setStatus(getStatus());
                Patient updatedPatient = patientService.update(patient);
                updateAlert(updatedPatient);
            }

            clearFields();
            loadPatientDetails();
        }

    }
    
    @FXML
    private void delete(ActionEvent event) {
        List<Patient> patient = patientTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected data?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            patientService.deleteInBatch(patient);
           
        }
            loadPatientDetails();
        
        
    }
    
    private void clearFields() {
        patientId.setText(null);
        firstName.clear();
        lastName.clear();
        address.clear();
        treatment.clear();
        rbMale.setSelected(true);
        rbFemale.setSelected(false);
        contactNum.clear();
        amount.clear();
        rbWalkin.setSelected(true);
        rbByApp.setSelected(false);
    }
    private void addAlert(Patient patient) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient added successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The patient " + patient.getFirstName() + " " + patient.getLastName() + " has been added.\nId is " + patient.getId() + ".");
        alert.showAndWait();
    }
     private void updateAlert(Patient patient) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The patient " + patient.getFirstName() + " " + patient.getLastName() + " has been updated.");
        alert.showAndWait();
    }
    private String getFirstName() {
        return firstName.getText();
    }
    
    private String getLastName(){
        return lastName.getText();
    }
    
    private String getAddress(){
        return address.getText();
    }
    
    private String getTreatment(){
        return treatment.getText();
    }
    
    private String getSex(){
        return rbMale.isSelected() ? "Male" : "Female";
    }
    
    private long getContactNumber(){
        return Long.parseLong(contactNum.getText());
    }
    
    private Double getAmount(){
        return Double.parseDouble(amount.getText());
    }
    private String getStatus(){
        return rbWalkin.isSelected() ? "Walk-in" : "By Appointment";
    }
  
   
    private void setColumnProperties() {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTreatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colEdit.setCellFactory(cellFactory);
    }
    Callback<TableColumn<Patient, Boolean>, TableCell<Patient, Boolean>> cellFactory
            = new Callback<TableColumn<Patient, Boolean>, TableCell<Patient, Boolean>>() {
                
        @Override
        public TableCell<Patient, Boolean> call(final TableColumn<Patient, Boolean> param) {
            final TableCell<Patient, Boolean> cell = new TableCell<Patient, Boolean>() {
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
                            Patient patient = getTableView().getItems().get(getIndex());
                            updatePatient(patient);
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

                private void updatePatient(Patient patient) {
                    patientId.setText(Long.toString(patient.getId()));
                    firstName.setText(patient.getFirstName());
                    lastName.setText(patient.getLastName());
                    address.setText(patient.getAddress());
                    treatment.setText(patient.getTreatment());
                    if (patient.getSex().equals("Male")) {
                        rbMale.setSelected(true);
                    } else {
                        rbFemale.setSelected(true);
                    }
                    contactNum.setText(Long.toString(patient.getContactNumber()));
                    amount.setText(Double.toString(patient.getAmount()));
                    if (patient.getStatus().equals("Walk-in")) {
                        rbWalkin.setSelected(true);
                    } else {
                        rbByApp.setSelected(true);
                    }
                }
            };
            return cell;
        }
    };

    private void loadPatientDetails() {
        patientList.clear();
        patientList.addAll(patientService.findAll());

        patientTable.setItems(patientList);
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
