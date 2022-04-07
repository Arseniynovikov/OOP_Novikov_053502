package Methods.Client;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Handler;

public class Client_Init {
    public static Client Init(Client user){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        Client client = new Client();
        String select = "SELECT * FROM " + Const.CLIENTS_TABLE + " WHERE " +
                Const.CLIENTS_PASSPORT + "=? AND " + Const.CLIENTS_PASSWORD + "=? ";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getPassport());
            prSt.setString(2, user.getPassword());

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
