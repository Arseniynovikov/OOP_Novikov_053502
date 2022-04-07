package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Init.Administrator_Init;
import Init.Manager_Init;
import Init.Operator_Init;
import Methods.Change;
import User.Administrator;
import User.Manager;
import User.Operator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class worker_initController {

    @FXML
    private ResourceBundle resources;

    static Operator operator;
    static Administrator administrator;
    static Manager manager;

    @FXML
    private Label errLabel;

    @FXML
    private URL location;

    @FXML
    private TextField pasportField;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> rangComboBox;

    @FXML
    private Button initButton;

    @FXML
    void initialize() {
        operator = null;
        administrator = null;
        manager = null;
        initButton.setDisable(true);
        ObservableList<String> values = FXCollections.observableArrayList("Оператор", "Администратор", "Менеджер");
        rangComboBox.setItems(values);
        rangComboBox.setOnAction(event -> {
            initButton.setDisable(false);
            pasportField.setEditable(true);
            password.setEditable(true);
        });

        initButton.setOnAction(event -> {
            String passport = pasportField.getText();
            String pass = password.getText();
            if(passport.equals("") || pass.equals("")){
                errLabel.setText("Поля должны быть заполнены.");
            } else {
                switch (rangComboBox.getValue()) {
                    case "Оператор":
                        Operator buff_operator = new Operator();
                        buff_operator.setPassport(passport);
                        buff_operator.setPassword(pass);
                        operator = Operator_Init.Init(buff_operator);
                        if(operator.getName() != null) {
                            initButton.getScene().getWindow().hide();
                            Change.Change_View(this, "/sample/View/operator_view.fxml");
                        }
                        break;
                    case "Администратор":
                        Administrator buff_administrator = new Administrator();
                        buff_administrator.setPassport(passport);
                        buff_administrator.setPassword(pass);
                        administrator = Administrator_Init.Init(buff_administrator);
                        if(administrator.getName() != null) {
                            initButton.getScene().getWindow().hide();
                            Change.Change_View(this, "/sample/View/administrator_view.fxml");
                        }
                        break;
                    case "Менеджер":
                        Manager buff_manager = new Manager();
                        buff_manager.setPassport(passport);
                        buff_manager.setPassword(pass);
                        manager = Manager_Init.Init(buff_manager);
                        if(manager.getName() != null) {
                            initButton.getScene().getWindow().hide();
                            Change.Change_View(this, "/sample/View/manager_view.fxml");
                        }
                        break;
                }
            }
        });
    }

}
