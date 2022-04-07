package Methods.Salary_project;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Approve_salary_project {
    public static void Approve(String name) {

        String change = "UPDATE " + Const.SALARY_PROJECT_TABLE + " SET " + Const.SALARY_PROJECT_APPROVE + "=? WHERE " + Const.SALARY_PROJECT_NAME + "=?";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(change);
            prSt.setBoolean(1, true);
            prSt.setString(2, name);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
