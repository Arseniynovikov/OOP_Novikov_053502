package Registration;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client_registration {
    public static void signUpClient(Client client, String bank_bik) {
        DatabaseHandler handler = new DatabaseHandler();

        ResultSet resSet = null;
        String check = "SELECT * FROM " + Const.CLIENT_BANK_TABLE + " WHERE " + Const.CLIENT_BANK_BANKID + "=? ";
        ArrayList<String> client_id = new ArrayList<>();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(check);
            prSt.setString(1, bank_bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                client_id.add(resSet.getString(Const.CLIENT_BANK_CLIENTID));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(String str : client_id){
            if(client.getPassport().equals(str))
                return;
        }

        String insert_c_b = "INSERT INTO " + Const.CLIENT_BANK_TABLE + " (" + Const.CLIENT_BANK_BANKID + "," +
                Const.CLIENT_BANK_CLIENTID + ")" + "VALUES(?,?)";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert_c_b);
            prSt.setString(1, bank_bik);
            prSt.setString(2, client.getPassport());

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        String insert = "INSERT INTO " + Const.CLIENTS_TABLE + " (" + Const.CLIENTS_NAME + "," +
                Const.CLIENTS_PASSPORT + "," + Const.CLIENTS_ID + "," + Const.CLIENTS_PHONENUMBER + "," +
                Const.CLIENTS_EMAIL + "," + Const.CLIENTS_PASSWORD + "," +
                Const.CLIENTS_COUNTRY + "," + Const.CLIENTS_COMPANY + ")" + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, client.getName());
            prSt.setString(2, client.getPassport());
            prSt.setString(3, client.getId());
            prSt.setString(4, client.getPhone_number());
            prSt.setString(5, client.getEmail());
            prSt.setString(6, client.getPassword());
            prSt.setString(7, client.getCountry());
            prSt.setString(8, client.getCompany());


            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
