package Init;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Client;
import User.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager_Init {
    public static Manager Init(Manager manager){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        Manager _manager = new Manager();
        String select = "SELECT * FROM " + Const.MANAGER_TABLE + " WHERE " +
                Const.MANAGER_PASSPORT + "=? AND " + Const.MANAGER_PASSWORD + "=? ";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, manager.getPassport());
            prSt.setString(2, manager.getPassword());

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                _manager.setName(resSet.getString(Const.MANAGER_NAME));
                _manager.setPassword(resSet.getString(Const.MANAGER_PASSWORD));
                _manager.setPassport(resSet.getString(Const.MANAGER_PASSPORT));
                _manager.setEmail(resSet.getString(Const.MANAGER_EMAIL));
                _manager.setId(resSet.getString(Const.MANAGER_ID));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return _manager;
    }
}
