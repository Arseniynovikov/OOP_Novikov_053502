package Methods.Account;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Unfreez_account {
    public static void Unfreez(String number) {
        String change = "UPDATE " + Const.BANKACCOUNT_TABLE + " SET " + Const.BANKACCOUNT_FREEZING + "=? WHERE " + Const.BANKACCOUNT_NUMBER + "=?";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(change);
            prSt.setBoolean(1, false);
            prSt.setString(2, number);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
