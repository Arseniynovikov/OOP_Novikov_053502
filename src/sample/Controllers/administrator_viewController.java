package sample.Controllers;

import Company.Salary_project;
import Methods.Change;
import Methods.Salary_project.Approve_salary_project;
import Methods.Salary_project.Salary_project_from_sql;
import User.Administrator;
import User.Operator;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class administrator_viewController {
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
        Administrator administrator = worker_initController.administrator;
        nameLable.setText(administrator.getName());
        ArrayList<Salary_project> salary_projects = Salary_project_from_sql.Take(Controller.bank.getBik());

        exit.setOnAction(actionEvent -> {
            nameLable.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });
    }
}
