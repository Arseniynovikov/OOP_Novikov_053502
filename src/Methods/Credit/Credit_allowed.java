package Methods.Credit;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Credit_allowed {
    public static void Allowed(String number) {

        String change = "UPDATE " + Const.CREDIT_TABLE + " SET " + Const.CREDIT_ALLOWED + "=? WHERE " + Const.CREDIT_NUMBER + "=?";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(change);
            prSt.setBoolean(1, true);
            prSt.setString(2, number);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
