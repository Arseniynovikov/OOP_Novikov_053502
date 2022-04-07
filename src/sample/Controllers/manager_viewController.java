package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Company.Company;
import Methods.Account.Find_account;
import Methods.Account.Take_Account;
import Methods.Change;
import Methods.Client.All_Clients_From_Sql;
import Methods.Client.Client_approve;
import Methods.Client.Client_delete;
import Methods.Client.Find_client;
import Methods.Credit.Credit_From_Sql;
import Methods.Credit.Find_credit;
import Methods.Installment_Plan.Installment_Plan_Find;
import Methods.Installment_Plan.Installment_Plan_From_Sql;
import Money.Bank_Account;
import Money.Credit;
import Money.Installment_Plan;
import User.Client;
import User.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class manager_viewController {

    static Client _client = null;
    static Company _company = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private MenuItem exit;


    @FXML
    private Button approveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<String> ListOut;

    @FXML
    private ComboBox<String> choiceComboBox;

    @FXML
    private Menu name;

    @FXML
    private Label nameLabel;

    @FXML
    void initialize() {
        Manager manager = worker_initController.manager;
        nameLabel.setText(manager.getName());

        ObservableList<String> values = FXCollections.observableArrayList("Счета", "Рассрочки", "Кредиты",
                "Клиенты требующие подтверждения");
        choiceComboBox.setItems(values);
        choiceComboBox.setValue("Счета");
        Client client = new Client();
        client.setAccounts(Take_Account.Accounts(null, Controller.bank.getBik()));
        client.setCredits(Credit_From_Sql.Take(null, Controller.bank.getBik()));
        client.setInstallment_plans(Installment_Plan_From_Sql.Take(null, Controller.bank.getBik()));

        final String[][] accounts_output = {new String[client.getAccounts().size()]};
        for (int i = 0; i < client.getAccounts().size(); i++) {
            accounts_output[0][i] = Make_account_string(client.getAccounts().get(i), i);
        }

        ListOut.getItems().addAll(accounts_output[0]);

        choiceComboBox.setOnAction(event -> {
            if (choiceComboBox.getValue().equals("Кредиты")) {
                ListOut.getItems().clear();

                final String[][] credits_output = {new String[client.getCredits().size()]};
                for (int i = 0; i < client.getCredits().size(); i++) {
                    credits_output[0][i] = Make_credit_string(client.getCredits().get(i), i);
                }

                ListOut.getItems().addAll(credits_output[0]);

            } else if (choiceComboBox.getValue().equals("Счета")) {
                ListOut.getItems().clear();
                final String[][] _accounts_output = {new String[client.getAccounts().size()]};
                for (int i = 0; i < client.getAccounts().size(); i++) {
                    _accounts_output[0][i] = Make_account_string(client.getAccounts().get(i), i);
                }

                ListOut.getItems().addAll(_accounts_output[0]);

            } else if (choiceComboBox.getValue().equals("Рассрочки")) {
                ListOut.getItems().clear();
                final String[][] instalment_plans_output = {new String[client.getInstallment_plans().size()]};
                for (int i = 0; i < client.getInstallment_plans().size(); i++) {
                    instalment_plans_output[0][i] = Make_instalmentplan_string(client.getInstallment_plans().get(i), i);
                }

                ListOut.getItems().addAll(instalment_plans_output[0]);

            } else if (choiceComboBox.getValue().equals("Клиенты требующие подтверждения")) {
                ListOut.getItems().clear();
                ArrayList<Client> buf_client = All_Clients_From_Sql.Take();

                for (Client cl : buf_client) {
                    if (!cl.getApprove()) {
                        ListOut.getItems().add(cl.getName() + "\t\t\t" + cl.getCompany() + "\t\t\t" + cl.getPassport());
                    }
                }
            }
        });

        MultipleSelectionModel<String> langsSelectionModel = ListOut.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                switch (choiceComboBox.getValue()) {
                    case "Счета":
                        if(newValue != null) {
                            _client = Find_account.Find(newValue);
                            ListOut.getScene().getWindow().hide();
                            Change.Change_View(this, "/sample/View/show_client.fxml");
                            break;
                        }
                    case "Кредиты":
                        if(newValue != null) {
                            _client = Find_credit.Find(newValue);
                            ListOut.getScene().getWindow().hide();
                            Change.Change_View(this, "/sample/View/show_client.fxml");
                            break;
                        }
                    case "Рассрочки":
                        if(newValue != null) {
                            _client = Installment_Plan_Find.Find(newValue);
                            ListOut.getScene().getWindow().hide();
                            Change.Change_View(this, "/sample/View/show_client.fxml");
                            break;
                        }
                    case "Клиенты требующие подтверждения":
                        if(newValue != null) {
                            _client = Find_client.Find(newValue);
                            break;
                        }
                }

            }
        });

        approveButton.setOnAction(event -> {
            _client.setApprove(true);
            Client_approve.Approve(_client);
        });
        cancelButton.setOnAction(event -> {
            Client_delete.Delete(_client);
        });

        exit.setOnAction(event-> {
            approveButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
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

}

