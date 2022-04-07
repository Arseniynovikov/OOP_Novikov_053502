package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Company.Bank;
import Methods.Bank.Take_Banks;
import Methods.Change;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Controller {

    static Bank bank;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> banksComboBox;

    @FXML
    private Button begin;

    @FXML
    void initialize() {
        ArrayList<Bank> banks;
        banks = Take_Banks.Take();

        ObservableList<String> values = FXCollections.observableArrayList();

        for (Bank b : banks)
            values.add(b.getName());

        begin.setDisable(true);
        banksComboBox.setItems(values);

        banksComboBox.setOnAction(event -> {
            begin.setDisable(false);
            for(Bank b : banks)
                if(b.getName().equals(banksComboBox.getValue())) {
                    bank = b;
                    break;
                }
        });

        begin.setOnAction(event -> {
            begin.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });

    }

}
