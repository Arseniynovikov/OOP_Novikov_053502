package sample.Controllers;

import Company.Company;
import Methods.Account.Take_Account;
import Methods.Change;
import Methods.Client.Clients_init;
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

import java.util.ArrayList;

public class zp_companyController {
    @FXML
    private ComboBox<String> account_ComboBox;

    @FXML
    private Label company_nameLabel;

    @FXML
    private TextField countField;

    @FXML
    private Button okButton;

    @FXML
    private Button returnButton;

    @FXML
    private ComboBox<String> worker_ComboBox;


    @FXML
    void initialize() {
        Company company = company_viewController.company;

        company_nameLabel.setText(company.getName());

        ArrayList<Client> workers = Clients_init.Take_clients(Controller.bank.getBik(), company.getName());

        String[] workers_names = new String[workers.size()];
        int i = 0;
        for(Client w : workers) {
            workers_names[i] = w.getName();
            System.out.print(w.getName()+"\n");
            i++;
        }
        ObservableList<String> values = FXCollections.observableArrayList(workers_names);
        worker_ComboBox.setItems(values);
        final Client[] cl = new Client[1];
        worker_ComboBox.setOnAction(event -> {
            for(Client w : workers) {
                if(w.getName().equals(worker_ComboBox.getValue())) {
                    cl[0] = w;
                    cl[0].setAccounts(Take_Account.Accounts(cl[0].getPassport(), Controller.bank.getBik()));
                }
            }
            int j = 0;
            String[] account_name = new String[cl[0].getAccounts().size()];
            for(Bank_Account account : cl[0].getAccounts()){
                account_name[j] = account.getNumber();
                j++;
            }
            ObservableList<String> values1 = FXCollections.observableArrayList(account_name);
            account_ComboBox.setItems(values1);
        });


        okButton.setOnAction(event -> {
            try {
                Salary_project_to_Sql.Make(cl[0], Double.parseDouble(countField.getText()), Controller.bank.getBik(), account_ComboBox.getValue());
                okButton.getScene().getWindow().hide();
                Change.Change_View(this, "/sample/View/company_view.fxml");
            } catch (NumberFormatException e) {
                countField.setText("");
                System.err.print("Неправельный формат строки");
            }
        });

        returnButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/company_view.fxml");
        });
    }
}
