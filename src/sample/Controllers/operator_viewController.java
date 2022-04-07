package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Company.Salary_project;
import Init.Operator_Init;
import Methods.Account.Find_account;
import Methods.Change;
import Methods.Client.Find_client;
import Methods.Credit.Find_credit;
import Methods.Installment_Plan.Installment_Plan_Find;
import Methods.Salary_project.Approve_salary_project;
import Methods.Salary_project.Salary_project_from_sql;
import User.Operator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class operator_viewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button approveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button cancellation;

    @FXML
    private ComboBox<String> chComboBox;

    @FXML
    private MenuItem exit;

    @FXML
    private Label nameLable;

    @FXML
    private ListView<String> showList;

    @FXML
    void initialize() {
        Operator operator = worker_initController.operator;
        nameLable.setText(operator.getName());
        ArrayList<Salary_project> salary_projects = Salary_project_from_sql.Take(Controller.bank.getBik());

        int i = 0;
        String[] names = new String[salary_projects.size()];
        for(Salary_project sp : salary_projects){
            names[i] = sp.getName();
            i++;
        }
        chComboBox.setOnAction(event -> {
            if(chComboBox.getValue().equals("Заявки")){
                showList.getItems().addAll(names);
            }
        });

        ObservableList<String> values = FXCollections.observableArrayList("Дейсвия", "Заявки");
        chComboBox.setItems(values);


        exit.setOnAction(actionEvent -> {
            nameLable.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });
        final String[] name = new String[1];
        MultipleSelectionModel<String> langsSelectionModel = showList.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                name[0] = newValue;
            }
        });

        approveButton.setOnAction(event -> {
            for(Salary_project sp : salary_projects){
                if(sp.getName().equals(name[0])){
                    Approve_salary_project.Approve(name[0]);
                }
            }
        });
    }

}
