package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Company.Company;
import Methods.Client.Client_Init;
import Init.Company_Init;
import Methods.Change;
import User.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static Methods.Change.Change_View;

public class Init_Controller {
    public static Client _client;
    public static Company _company;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errLabel;

    @FXML
    private Button Button_init;

    @FXML
    private Button Button_registration;

    @FXML
    private CheckBox companyCheckBox;

    @FXML
    private Button im_WorkerButton;

    @FXML
    private TextField passport_init;

    @FXML
    private PasswordField password;


    @FXML
    void initialize() {
        _company = null;
        _client = null;
        im_WorkerButton.setOnAction(event -> {
            im_WorkerButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/worker_init.fxml");
        });
        companyCheckBox.setOnAction(event -> {
            if (companyCheckBox.isSelected())
                passport_init.setPromptText("УНП");
            else
                passport_init.setPromptText("Номер паспорта");
        });

        Button_registration.setOnAction(event -> {

            Button_registration.getScene().getWindow().hide();

            Change_View(this, "/sample/View/user_registration.fxml");
        });

        Button_init.setOnAction(event -> {
            if (!companyCheckBox.isSelected()) {
                Client client = new Client();
                client.setPassport(passport_init.getText());
                client.setPassword(password.getText());
                _client = Client_Init.Init(client);
                if(_client.getApprove()) {
                    Button_registration.getScene().getWindow().hide();
                    Change_View(this, "/sample/View/user_view.fxml");
                } else {
                    errLabel.setText("Требуется подтверждение.");
                }

            } else {
                Company company = new Company();
                company.setUnp(passport_init.getText());
                company.setPassword(password.getText());
                _company = Company_Init.Init(company, Controller.bank.getBik());

                if (_company == null) {
                    errLabel.setText("В этом банке нет такого предприятия");
                } else {
                    Button_registration.getScene().getWindow().hide();
                    Change_View(this, "/sample/View/company_view.fxml");
                }
            }

        });
    }
}
