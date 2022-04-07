package Methods.Client;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client_approve {
    public static void Approve(Client client) {
        String change = "UPDATE " + Const.CLIENTS_TABLE + " SET " + Const.CLIENTS_APPROVE + "=? WHERE " + Const.CLIENTS_PASSPORT + "=?";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(change);
            prSt.setBoolean(1, true);
            prSt.setString(2, client.getPassport());

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
