package Registration;

import Company.Company;
import DB.Const;
import DB.DatabaseHandler;
import User.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Company_registration {
    public static void Registration(Company company) {
        DatabaseHandler handler = new DatabaseHandler();
        ArrayList<String> bank_bik = new ArrayList<String>();
        ResultSet resSet = null;
        String check = "SELECT * FROM " + Const.COMPANY_TABLE + " WHERE " + Const.COMPANY_UNP + "=? ";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(check);
            prSt.setString(1, company.getUnp());

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                bank_bik.add(resSet.getString(Const.COMPANY_NAME));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(bank_bik.isEmpty()) {
            String insert = "INSERT INTO " + Const.COMPANY_TABLE + "(" + Const.COMPANY_NAME + "," +
                    Const.COMPANY_TYPE + "," + Const.COMPANY_UNP + "," + Const.COMPANY_ADRES + ","
                    + Const.COMPANY_PASSWORD + "," + Const.COMPANY_BIK + ")" + "VALUES(?,?,?,?,?,?)";


            try {
                PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);

                prSt.setString(1, company.getName());
                prSt.setString(2, company.getType());
                prSt.setString(3, company.getUnp());
                prSt.setString(4, company.getAdres());
                prSt.setString(5, company.getPassword());
                prSt.setString(6, company.getBik());

                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
