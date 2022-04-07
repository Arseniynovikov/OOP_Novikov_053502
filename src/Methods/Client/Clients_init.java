package Methods.Client;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Clients_init {
    public static ArrayList<Client> Take_clients (String bik, String company_name){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();
        ArrayList<Client> clients = new ArrayList<>();

        Client client = new Client();
        String select = "SELECT * FROM " + Const.CLIENT_BANK_TABLE + " WHERE " +
                Const.CLIENT_BANK_BANKID + "=?";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                client.setPassport(resSet.getString(Const.CLIENT_BANK_CLIENTID));

                ResultSet rs = null;

                String find = "SELECT * FROM " + Const.CLIENTS_TABLE + " WHERE " +
                        Const.CLIENTS_PASSPORT + "=? AND " + Const.CLIENTS_COMPANY + "=?";

                PreparedStatement prSt1 = handler.getDbConnection().prepareStatement(find);

                prSt1.setString(1, client.getPassport());
                prSt1.setString(2, company_name);

                rs = prSt1.executeQuery();

                while (rs.next()) {
                    String name = rs.getString(Const.CLIENTS_NAME);
                    String password = rs.getString(Const.CLIENTS_PASSWORD);
                    String passport = rs.getString(Const.CLIENTS_PASSPORT);
                    String email = rs.getString(Const.CLIENTS_EMAIL);
                    String phone_number = rs.getString(Const.CLIENTS_PHONENUMBER);
                    String id = rs.getString(Const.CLIENTS_ID);
                    String country = rs.getString(Const.CLIENTS_COUNTRY);
                    String company = rs.getString(Const.CLIENTS_COMPANY);
                    Boolean approve = rs.getBoolean(Const.CLIENTS_APPROVE);

                    clients.add(new Client(name, passport, id, phone_number, email, password, country, company, approve));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
