package Init;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Administrator;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Administrator_Init {
    public static Administrator Init(Administrator administrator){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();

        Administrator _administrator = new Administrator();
        String select = "SELECT * FROM " + Const.ADMINISTRATORS_TABLE + " WHERE " +
                Const.ADMINISTRATORS_PASSPORT + "=? AND " + Const.ADMINISTRATORS_PASSWORD + "=? ";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, administrator.getPassport());
            prSt.setString(2, administrator.getPassword());

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                _administrator.setName(resSet.getString(Const.ADMINISTRATORS_NAME));
                _administrator.setPassword(resSet.getString(Const.ADMINISTRATORS_PASSWORD));
                _administrator.setPassport(resSet.getString(Const.ADMINISTRATORS_PASSPORT));
                _administrator.setEmail(resSet.getString(Const.ADMINISTRATORS_EMAIL));
                _administrator.setId(resSet.getString(Const.ADMINISTRATORS_ID));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return _administrator;
    }
}
