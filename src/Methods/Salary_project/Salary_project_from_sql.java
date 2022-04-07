package Methods.Salary_project;

import Company.Salary_project;
import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Salary_project_from_sql {
    public static ArrayList<Salary_project> Take(String bik){
        DatabaseHandler handler = new DatabaseHandler();
        ResultSet resSet = null;
        ArrayList<Salary_project> salary_projects = new ArrayList<>();
        Salary_project salary_project = new Salary_project();

        String take = "SELECT * FROM " + Const.SALARY_PROJECT_TABLE + " WHERE " + Const.SALARY_PROJECT_BIK + "=? AND " +
                Const.SALARY_PROJECT_APPROVE + "=?";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(take);
            prSt.setString(1, bik);
            prSt.setBoolean(2, false);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString(Const.SALARY_PROJECT_NAME);
                String passport = resSet.getString(Const.SALARY_PROJECT_PASSPORT);
                String account_name = resSet.getString(Const.SALARY_PROJECT_ACCOUNT_NAME);
                double count = resSet.getDouble(Const.SALARY_PROJECT_COUNT);
                String company = resSet.getString(Const.SALARY_PROJECT_COMPANY);
                Boolean approve = resSet.getBoolean(Const.SALARY_PROJECT_APPROVE);

                salary_projects.add(new Salary_project(name, bik, passport, count, account_name, company, approve));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salary_projects;
    }
}
