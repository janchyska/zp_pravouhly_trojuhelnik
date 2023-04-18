/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bpog_zapocet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author janch
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField tbOdvesnaA;
    @FXML
    private Button btnVypocitej;
    @FXML
    private TextField tbOdvesnaB;
    @FXML
    private TextField tbPrepona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnVypocitejOA(ActionEvent event) {
        double odvesnaA = 0, odvesnaB = 0, prepona = 0;

        if (!tbOdvesnaA.getText().isBlank() && tbOdvesnaB.getText().isBlank() && tbPrepona.getText().isBlank()
                || tbOdvesnaA.getText().isBlank() && !tbOdvesnaB.getText().isBlank() && tbPrepona.getText().isBlank()
                || tbOdvesnaA.getText().isBlank() && tbOdvesnaB.getText().isBlank() && !tbPrepona.getText().isBlank()) {
            message("Je zadána pouze jedna strana.");
            return;
        }

        if (!tbOdvesnaA.getText().isBlank()) {
            try {
                odvesnaA = Double.parseDouble(tbOdvesnaA.getText());
                if (odvesnaA <= 0) {
                    message("Strana a není kladné číslo.");
                    tbOdvesnaA.requestFocus();
                    return;
                }
            } catch (Exception e) {
                message("Strana a není kladné číslo.");
                tbOdvesnaA.requestFocus();
                return;
            }
        }
        if (!tbOdvesnaB.getText().isBlank()) {
            try {
                odvesnaB = Double.parseDouble(tbOdvesnaB.getText());
                if (odvesnaB <= 0) {
                    message("Strana b není kladné číslo.");
                    tbOdvesnaB.requestFocus();
                    return;
                }
            } catch (Exception e) {
                message("Strana b není kladné číslo.");
                tbOdvesnaB.requestFocus();
                return;
            }
        }
        if (!tbPrepona.getText().isBlank()) {
            try {
                prepona = Double.parseDouble(tbPrepona.getText());
                if (prepona <= 0) {
                    message("Přepona není skladné číslo.");
                    tbPrepona.requestFocus();
                    return;
                }
            } catch (Exception e) {
                message("Přepona není skladné číslo.");
                tbPrepona.requestFocus();
                return;
            }
        }

        if (odvesnaA == 0 || odvesnaB == 0 || prepona == 0) {
            BigDecimal odA = new BigDecimal(String.valueOf(odvesnaA));
            BigDecimal odB = new BigDecimal(String.valueOf(odvesnaB));
            BigDecimal prep = new BigDecimal(String.valueOf(prepona)).setScale(5, RoundingMode.HALF_UP);
            if (odvesnaA == 0) {
                odA = (prep.multiply(prep)).subtract((odB.multiply(odB)));
                odA = BigDecimal.valueOf(Math.sqrt(odA.doubleValue())).setScale(5, RoundingMode.HALF_UP);
                tbOdvesnaA.setText(odA.toString());
            } else if (odvesnaB == 0) {
                odB = (prep.multiply(prep)).subtract((odA.multiply(odA)));
                odB = BigDecimal.valueOf(Math.sqrt(odB.doubleValue())).setScale(5, RoundingMode.HALF_UP);
                tbOdvesnaB.setText(odB.toString());
            } else if (prepona == 0) {
                prep = (odA.multiply(odA)).add(odB.multiply(odB));
                prep = BigDecimal.valueOf(Math.sqrt(prep.doubleValue())).setScale(5, RoundingMode.HALF_UP);
                tbPrepona.setText(prep.toString());
            }
            return;
        }
        if (odvesnaA != 0 && odvesnaB != 0 && prepona != 0) {
            BigDecimal odA = new BigDecimal(String.valueOf(odvesnaA));
            BigDecimal odB = new BigDecimal(String.valueOf(odvesnaB));
            BigDecimal prep = new BigDecimal(String.valueOf(prepona)).setScale(5, RoundingMode.HALF_UP);
            BigDecimal result = (odA.multiply(odA)).add(odB.multiply(odB));
            result = BigDecimal.valueOf(Math.sqrt(result.doubleValue())).setScale(5, RoundingMode.HALF_UP);
            if (result.compareTo(prep) == 0) {
                message("Trojúhelník je pravoúhlý.");
            } else {
                message("Trojúhelník není pravoúhlý.");
            }
        }

    }

    private void message(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setContentText(msg);
        alert.show();
    }

}
