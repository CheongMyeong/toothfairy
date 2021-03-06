package com.cheongmyeong.toothfairy.views;

import java.util.ResourceBundle;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
public enum FxmlView {

    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    APPOINTMENT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("appointment.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Appointment.fxml";
        }
    },
      MAINUI{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MainUI.fxml";
        }
    },
      PATIENT{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("patient.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Patient.fxml";
        }
    },
      STAFF{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("staff.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Staff.fxml";
        }
    };
    
    
    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
