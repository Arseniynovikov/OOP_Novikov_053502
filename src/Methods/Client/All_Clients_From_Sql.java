package Methods.Client;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class All_Clients_From_Sql {
    public static ArrayList<Client> Take(){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();
        ArrayList<Client> clients = new ArrayList<>();
        String take = "SELECT * FROM " + Const.CLIENTS_TABLE;

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(take);


            resSet = prSt.executeQuery();

            while (resSet.next()) {
                Client client = new Client();
                client.setName(resSet.getString(Const.CLIENTS_NAME));
                client.setPassword(resSet.getString(Const.CLIENTS_PASSWORD));
                client.setPassport(resSet.getString(Const.CLIENTS_PASSPORT));
                client.setEmail(resSet.getString(Const.CLIENTS_EMAIL));
                client.setId(resSet.getString(Const.CLIENTS_ID));
                client.setCountry(resSet.getString(Const.CLIENTS_COUNTRY));
                client.setCompany(resSet.getString(Const.CLIENTS_COMPANY));
                client.setApprove(resSet.getBoolean(Const.CLIENTS_APPROVE));
                clients.add(client);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return clients;
    }
}
