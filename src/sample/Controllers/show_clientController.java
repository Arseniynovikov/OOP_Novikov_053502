package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Methods.Account.Freez_account;
import Methods.Account.Take_Account;
import Methods.Account.Unfreez_account;
import Methods.Change;
import Methods.Credit.Credit_Delete;
import Methods.Credit.Credit_From_Sql;
import Methods.Credit.Credit_allowed;
import Methods.Installment_Plan.Installment_Plan_Delete;
import Methods.Installment_Plan.Installment_Plan_From_Sql;
import Methods.Installment_Plan.Installment_Plan_allowed;
import Money.Bank_Account;
import Money.Credit;
import Money.Installment_Plan;
import User.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class show_clientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button alloweButton;


    @FXML
    private Button cancelButton;

    @FXML
    private Button freezButton;

    @FXML
    private Label companyLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private ComboBox<String> showComboBox;

    @FXML
    private Button returnButton;

    @FXML
    private Button openButton;

    @FXML
    private ListView<String> showList;

    @FXML
    void initialize() {
        alloweButton.setDisable(true);
        cancelButton.setDisable(true);
        freezButton.setDisable(true);
        openButton.setDisable(true);

        final String[] str = new String[1];
        Client client = manager_viewController._client;
        ObservableList<String> values = FXCollections.observableArrayList("Счета", "Рассрочки", "Кредиты");
        showComboBox.setItems(values);

        MultipleSelectionModel<String> langsSelectionModel = showList.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
               str[0] = newValue;
            }
        });

        nameLabel.setText(client.getName());
        emailLabel.setText("Email:\t" + client.getEmail());
        phoneLabel.setText("Номер:\t" + client.getPhone_number());
        countryLabel.setText("Страна:\t" + client.getCountry());
        companyLabel.setText("Работа:\t" + client.getCompany());
        client.setAccounts(Take_Account.Accounts(client.getPassport(), Controller.bank.getBik()));
        client.setInstallment_plans(Installment_Plan_From_Sql.Take(client.getPassport(), Controller.bank.getBik()));
        client.setCredits(Credit_From_Sql.Take(client.getPassport(), Controller.bank.getBik()));


        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/manager_view.fxml");
        });

        showComboBox.setOnAction(event -> {
            if(showComboBox.getValue().equals("Кредиты")) {
                showList.getItems().clear();
                final String[][] credits_output = {new String[client.getCredits().size()]};
                for(int i = 0; i < client.getCredits().size(); i++) {
                    credits_output[0][i] = Make_credit_string(client.getCredits().get(i), i);
                }

                showList.getItems().addAll(credits_output[0]);
                alloweButton.setDisable(false);
                cancelButton.setDisable(false);
                freezButton.setDisable(true);
                openButton.setDisable(true);

            } else if(showComboBox.getValue().equals("Счета")) {
                showList.getItems().clear();
                final String[][] _accounts_output = {new String[client.getAccounts().size()]};
                for(int i = 0; i < client.getAccounts().size(); i++) {
                    _accounts_output[0][i] = Make_account_string(client.getAccounts().get(i), i);
                }

                showList.getItems().addAll(_accounts_output[0]);
                alloweButton.setDisable(true);
                cancelButton.setDisable(true);
                freezButton.setDisable(false);
                openButton.setDisable(false);

            } else if(showComboBox.getValue().equals("Рассрочки")) {
                showList.getItems().clear();
                final String[][] instalment_plans_output = {new String[client.getInstallment_plans().size()]};
                for (int i = 0; i < client.getInstallment_plans().size(); i++) {
                    instalment_plans_output[0][i] = Make_instalmentplan_string(client.getInstallment_plans().get(i), i);
                }

                showList.getItems().addAll(instalment_plans_output[0]);
                alloweButton.setDisable(false);
                cancelButton.setDisable(false);
                freezButton.setDisable(true);
                openButton.setDisable(true);
            }
        });

        cancelButton.setOnAction(event -> {
            if(showComboBox.getValue().equals("Рассрочки")) {
                Installment_Plan installment_plan = installmentPlan(client.getInstallment_plans(), str[0]);
                Installment_Plan_Delete.Delete(installment_plan.getNumber());
            } else if(showComboBox.getValue().equals("Кредиты")){
                Credit credit = take_credit(client.getCredits(), str[0]);
                Credit_Delete.Delete(credit.getNumber());
            }
        });

        alloweButton.setOnAction(event -> {
            if(showComboBox.getValue().equals("Рассрочки")) {
                Installment_Plan installment_plan = installmentPlan(client.getInstallment_plans(), str[0]);

                installment_plan.setAllowed(true);
                Installment_Plan_allowed.Allowed(installment_plan.getNumber());

            } else if(showComboBox.getValue().equals("Кредиты")){
                Credit credit = take_credit(client.getCredits(), str[0]);

                credit.setAllowed(true);
                Credit_allowed.Allowed(credit.getNumber());
            }
        });

        freezButton.setOnAction(event -> {
            String[] s = str[0].split("\t\t\t");
            Freez_account.Freez(s[0]);
        });
        openButton.setOnAction(event -> {
            String[] s = str[0].split("\t\t\t");
            Unfreez_account.Unfreez(s[0]);
        });
    }
    private String Make_account_string(Bank_Account bank_account, int i){
        String tab = "\t\t\t", tab2 = "\t\t\t";

        if(!bank_account.getBloking() && !bank_account.getFreezing())
            return bank_account.getNumber() + tab + "Активен." + tab2 + Double.toString(bank_account.getCount());

        else if(bank_account.getBloking())
            return bank_account.getNumber() + tab + "Заблокироан." + tab2 + Double.toString(bank_account.getCount());

        else
            return bank_account.getNumber() + tab + "Заморожен.";
    }
    private String Make_credit_string(Credit credit, int i) {
        String tab, tab2 = "\t\t\t";
        if(i<10) tab = "\t\t\t";
        else tab = "\t\t";

        if(credit.getAllowed())
            return credit.getNumber() + tab + "Открыт." + tab2 + Double.toString(credit.getCount());

        else
            return credit.getNumber() + tab + "В обработке." + tab2 + Double.toString(credit.getCount());

    }
    private String Make_instalmentplan_string(Installment_Plan installment_plan, int i){
        String tab, tab2 = "\t\t\t";
        if(i<10) tab = "\t\t\t";
        else tab = "\t\t";

        if(installment_plan.getAllowed())
            return installment_plan.getNumber() + tab + "Открыт." + tab2 + Double.toString(installment_plan.getCount());

        else
            return installment_plan.getNumber() + tab + "В обработке." + tab2 + Double.toString(installment_plan.getCount());

    }
    private Installment_Plan installmentPlan(ArrayList<Installment_Plan> ip, String number){
        String[] current_number = number.split("\t\t\t");
        for(Installment_Plan i : ip)
            if(i.getNumber().equals(current_number[0]))
                return i;
        return null;
    }
    private Credit take_credit(ArrayList<Credit> ip, String number){
        String[] current_number = number.split("\t\t\t");

        for(Credit i : ip)
            if(i.getNumber().equals(current_number[0]))
                return i;
        return null;
    }
}
