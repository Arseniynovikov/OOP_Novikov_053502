package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Methods.Account.Make_Account;
import Methods.Account.Take_Account;
import Methods.Change;
import Money.Bank_Account;
import Money.Money_Transfer;
import User.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class make_transferController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> in_accountComboBox;

    @FXML
    private TextField countText;

    @FXML
    private ComboBox<String> out_accountComboBox;

    @FXML
    private Button transferButton;

    @FXML
    void initialize() {
        Client out_client = Init_Controller._client;
        int i = 0;
        String[] account_name = new String[out_client.getAccounts().size()];
        for(Bank_Account account : out_client.getAccounts()){
            account_name[i] = account.getNumber();
            i++;
        }
        ObservableList<String> values = FXCollections.observableArrayList(account_name);
        out_accountComboBox.setItems(values);

        i = 0;
        ArrayList<Bank_Account> bank_accounts = Take_Account.Accounts(null, null);
        String[] accounts = new String[bank_accounts.size()];
        for(Bank_Account account : bank_accounts){
            accounts[i] = account.getNumber();
            i++;
        }
        values = FXCollections.observableArrayList(accounts);
        in_accountComboBox.setItems(values);

        transferButton.setOnAction(event -> {
            transferButton.getScene().getWindow().hide();
            Bank_Account buf = new Bank_Account(), buf1= new Bank_Account();
            for(Bank_Account b : bank_accounts)
                if(b.getNumber().equals(in_accountComboBox.getValue()))
                    buf = b;
                else if(b.getNumber().equals(out_accountComboBox.getValue()))
                    buf1 = b;

            Money_Transfer.Transfer(buf1, buf, Double.parseDouble(countText.getText()));

            Change.Change_View(this, "/sample/View/user_view.fxml");
        });
    }

}
