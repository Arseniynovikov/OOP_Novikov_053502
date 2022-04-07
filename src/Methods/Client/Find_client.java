package Methods.Client;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Find_client {
    public static Client Find(String str) {
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        Client client = new Client();
        String[] buf_name = str.split("\t\t\t");
        String passport = buf_name[2];

        String take = "SELECT * FROM " + Const.CLIENTS_TABLE + " WHERE " + Const.CLIENTS_PASSPORT + "=? ";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(take);
            prSt.setString(1, passport);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                client.setName(resSet.getString(Const.CLIENTS_NAME));
                client.setPassword(resSet.getString(Const.CLIENTS_PASSWORD));
                client.setPassport(resSet.getString(Const.CLIENTS_PASSPORT));
                client.setEmail(resSet.getString(Const.CLIENTS_EMAIL));
                client.setId(resSet.getString(Const.CLIENTS_ID));
                client.setCountry(resSet.getString(Const.CLIENTS_COUNTRY));
                client.setCompany(resSet.getString(Const.CLIENTS_COMPANY));
                client.setApprove(resSet.getBoolean(Const.CLIENTS_APPROVE));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return client;
    }
}