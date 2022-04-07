package Methods.Account;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Unblock_account {
    public static void Unblock(String number) {
        String change = "UPDATE " + Const.BANKACCOUNT_TABLE + " SET " + Const.BANKACCOUNT_BLOKING + "=? WHERE " + Const.BANKACCOUNT_NUMBER + "=?";

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
