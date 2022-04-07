package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Methods.Change;
import Methods.Credit.Credit_To_Sql;
import Methods.Installment_Plan.Installment_Plan_To_Sql;
import Money.Credit;
import Money.Installment_Plan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class make_installment_planController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField countField;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private Label nameLabel;

    @FXML
    void initialize() {
        if(Init_Controller._company.getName() == null)
            nameLabel.setText("Credit" + Init_Controller._client.getName() + Integer.toString(Init_Controller._client.getCredits().size()));
        else
            nameLabel.setText("Credit" + Init_Controller._company.getName() + Integer.toString(Init_Controller._company.getCredits().size()));

        ObservableList<String> values = FXCollections.observableArrayList("3", "6", "12", "24", "48");
        monthComboBox.setItems(values);



        addButton.setOnAction(event -> {
            addButton.getScene().getWindow().hide();
            if(Init_Controller._company.getName() == null)
                Change.Change_View(this, "/sample/View/user_view.fxml");
            else
                Change.Change_View(this, "/sample/View/company_view.fxml");
            int month = Integer.parseInt(monthComboBox.getValue());

            Installment_Plan installment_plan = new Installment_Plan(nameLabel.getText(), month,
                    Init_Controller._client.getPassport(), Controller.bank.getBik(), Double.parseDouble(countField.getText()));
            Init_Controller._client.getInstallment_plans().add(installment_plan);
            Installment_Plan_To_Sql.Make(installment_plan);
        });

        backButton.setOnAction(event -> {
            if(Init_Controller._company.getName() == null)
                Change.Change_View(this, "/sample/View/user_view.fxml");
            else
                Change.Change_View(this, "/sample/View/company_view.fxml");
        });
    }

}
