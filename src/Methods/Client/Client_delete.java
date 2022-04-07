package Methods.Client;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client_delete {
    public static void Delete(Client client) {
        DatabaseHandler handler = new DatabaseHandler();
        String delete = "DELETE FROM " + Const.CLIENTS_TABLE + " WHERE " + Const.CLIENTS_PASSPORT + "=?";

        PreparedStatement prSt = null;
        try {
            prSt = handler.getDbConnection().prepareStatement(delete);

            prSt.setString(1, client.getPassport());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
