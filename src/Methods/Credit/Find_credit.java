package Methods.Credit;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Find_credit {
    public static Client Find(String str){
        ResultSet resSet = null;
        Client client = new Client();
        DatabaseHandler handler = new DatabaseHandler();
        String passport = null;
        String[] str_buff = str.split("\t\t\t");
        String name = str_buff[0];
        try {
            String take = "SELECT * FROM " + Const.CREDIT_TABLE + " WHERE " + Const.CREDIT_NUMBER + "=? ";

            PreparedStatement prSt = handler.getDbConnection().prepareStatement(take);

            prSt.setString(1, name);

            resSet = prSt.executeQuery();


            while (resSet.next()) {
                passport = resSet.getString(Const.CREDIT_CLIENTPASSPORT);
            }

            String take_client = "SELECT * FROM " + Const.CLIENTS_TABLE + " WHERE " + Const.CLIENTS_PASSPORT + "=? ";
            prSt = handler.getDbConnection().prepareStatement(take_client);

            prSt.setString(1, passport);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                client.setName(resSet.getString(Const.CLIENTS_NAME));
                client.setPassword(resSet.getString(Const.CLIENTS_PASSWORD));
                client.setPassport(resSet.getString(Const.CLIENTS_PASSPORT));
                client.setEmail(resSet.getString(Const.CLIENTS_EMAIL));
                client.setPhone_number(resSet.getString(Const.CLIENTS_PHONENUMBER));
                client.setId(resSet.getString(Const.CLIENTS_ID));
                client.setCountry(resSet.getString(Const.CLIENTS_COUNTRY));
                client.setCompany(resSet.getString(Const.CLIENTS_COMPANY));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return client;
    }
}
