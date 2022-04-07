package Methods.Salary_project;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Salary_project_to_Sql {
    public static void Make(Client client, double count, String bik, String account_name) {
        DatabaseHandler handler = new DatabaseHandler();
        String name = client.getName() + "TO" + client.getCompany();


        String insert = "INSERT INTO " + Const.SALARY_PROJECT_TABLE + " (" + Const.SALARY_PROJECT_NAME + "," +
                Const.SALARY_PROJECT_BIK + "," + Const.SALARY_PROJECT_COMPANY + "," + Const.SALARY_PROJECT_PASSPORT + "," +
                Const.SALARY_PROJECT_COUNT + "," + Const.SALARY_PROJECT_ACCOUNT_NAME + ")" + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, bik);
            prSt.setString(3, client.getCompany());
            prSt.setString(4, client.getPassport());
            prSt.setDouble(5, count);
            prSt.setString(6, account_name);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
