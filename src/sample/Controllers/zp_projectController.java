package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Methods.Change;
import Methods.Salary_project.Salary_project_to_Sql;
import Money.Bank_Account;
import User.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class zp_projectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> account_ComboBox;

    @FXML
    private AnchorPane accounts;

    @FXML
    private Label company_nameLabel;

    @FXML
    private TextField countField;

    @FXML
    private Label countryLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label phoneLabel;

    @FXML
    private Button returnButton;

    @FXML
    void initialize() {
        Client client = user_viewController.client;

        company_nameLabel.setText(client.getCompany());
        nameLabel.setText(client.getName());
        countryLabel.setText(client.getCountry());
        emailLabel.setText(client.getEmail());
        phoneLabel.setText(client.getPhone_number());

        String[] account_name = new String[client.getAccounts().size()];
        int i = 0;
        for(Bank_Account account : client.getAccounts()){
            account_name[i] = account.getNumber();
            i++;
        }
        ObservableList<String> values = FXCollections.observableArrayList(account_name);
        account_ComboBox.setItems(values);

        okButton.setOnAction(event -> {
            try {
                Salary_project_to_Sql.Make(client, Double.parseDouble(countField.getText()), Controller.bank.getBik(), account_ComboBox.getValue());
                okButton.getScene().getWindow().hide();
                Change.Change_View(this, "/sample/View/user_view.fxml");
            } catch (NumberFormatException e) {
                countField.setText("");
                System.err.print("Неправельный формат строки");
            }
        });

        returnButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/user_view.fxml");
        });
    }

}
