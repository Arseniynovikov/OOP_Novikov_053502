package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Methods.Account.Block_account;
import Methods.Account.Make_Account;
import Methods.Account.Take_Account;
import Methods.Account.Unblock_account;
import Methods.Change;
import Methods.Credit.Credit_From_Sql;
import Methods.Installment_Plan.Installment_Plan_From_Sql;
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


public class user_viewController {

    static Client client;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem change_passwordMenuitem;

    @FXML
    private Button blockButton;

    @FXML
    private Button unblockButton;

    @FXML
    private MenuItem exitMenuitem;

    @FXML
    private Button make_accountButton;

    @FXML
    private Button make_creditButton;

    @FXML
    private Button make_installment_planButton;

    @FXML
    private Label nameLabel;

    @FXML
    private ComboBox<String> showComboBox;

    @FXML
    private ListView<String> showListView;

    @FXML
    private Button transferButton;

    @FXML
    private TextField countField;

    @FXML
    private Button zpButton;


    @FXML
    void initialize() {
        client = Init_Controller._client;

        if(client.getCompany().equals("Безработный"))
            zpButton.setDisable(true);
        String current_passport = client.getPassport();
        String current_bik = Controller.bank.getBik();
        client.setAccounts(Take_Account.Accounts(current_passport, current_bik));
        client.setCredits(Credit_From_Sql.Take(current_passport, current_bik));
        client.setInstallment_plans(Installment_Plan_From_Sql.Take(current_passport, current_bik));

        final String[][] accounts_output = {new String[client.getAccounts().size()]};
        for(int i = 0; i < client.getAccounts().size(); i++) {
            accounts_output[0][i] = Make_account_string(client.getAccounts().get(i), i);

        }

        showListView.getItems().addAll(accounts_output[0]);

        ObservableList<String> values = FXCollections.observableArrayList("Счета", "Рассрочки", "Кредиты");
        showComboBox.setItems(values);
        showComboBox.setValue("Счета");

        nameLabel.setText(client.getName());

        make_accountButton.setOnAction(event -> {
            showComboBox.setValue("Счета");
            String account_number = client.getName() + Integer.toString(client.getAccounts().size());
            try {
                Bank_Account bank_account = new Bank_Account(client.getPassport(), account_number,
                        Double.parseDouble(countField.getText()), Controller.bank.getBik());

                Make_Account.New_Account(bank_account);
                client.getAccounts().add(bank_account);

                String buf_str = Make_account_string(bank_account, client.getAccounts().size());
                showListView.getItems().add(buf_str);
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }

            countField.setText("");
        });

        transferButton.setOnAction(event -> {
            transferButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/make_transfer.fxml");
        });

        showComboBox.setOnAction(event -> {
            if(showComboBox.getValue().equals("Кредиты")) {
                showListView.getItems().clear();
                final String[][] credits_output = {new String[client.getCredits().size()]};
                for(int i = 0; i < client.getCredits().size(); i++) {
                    credits_output[0][i] = Make_credit_string(client.getCredits().get(i), i);
                }

                showListView.getItems().addAll(credits_output[0]);

            } else if(showComboBox.getValue().equals("Счета")) {
                showListView.getItems().clear();
                final String[][] _accounts_output = {new String[client.getAccounts().size()]};
                for(int i = 0; i < client.getAccounts().size(); i++) {
                    _accounts_output[0][i] = Make_account_string(client.getAccounts().get(i), i);
                }

                showListView.getItems().addAll(_accounts_output[0]);

            } else if(showComboBox.getValue().equals("Рассрочки")) {
                showListView.getItems().clear();
                final String[][] instalment_plans_output = {new String[client.getInstallment_plans().size()]};
                for (int i = 0; i < client.getInstallment_plans().size(); i++) {
                    instalment_plans_output[0][i] = Make_instalmentplan_string(client.getInstallment_plans().get(i), i);
                }

                showListView.getItems().addAll(instalment_plans_output[0]);
            }
        });

        make_creditButton.setOnAction(event -> {
            make_creditButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/make_credit.fxml");
        });

        make_installment_planButton.setOnAction(event -> {
            make_creditButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/make_installment_plan.fxml");
        });

        exitMenuitem.setOnAction(event -> {
            make_creditButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });

        final String[][] account_name = new String[1][1];
        MultipleSelectionModel<String> langsSelectionModel = showListView.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                if(newValue != null)
                    account_name[0] = newValue.split("\t\t\t");
            }
        });

        blockButton.setOnAction(event -> {
            Block_account.Block(account_name[0][0]);
        });

        unblockButton.setOnAction(event -> {
            Unblock_account.Unblock(account_name[0][0]);
        });

        zpButton.setOnAction(event -> {
            zpButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/zp_project.fxml");
        });
    }
    private String Make_account_string(Bank_Account bank_account, int i){
        String tab, tab2 = "\t\t\t";
        if(i<10) tab = "\t\t\t";
        else tab = "\t\t";

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
}

