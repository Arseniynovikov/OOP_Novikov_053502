package Init;

import Company.Bank;
import Company.Company;
import DB.Const;
import DB.DatabaseHandler;
import User.Client;
import sample.Controllers.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Company_Init {
    public static Company Init(Company company, String bik){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        Company _company = new Company();
        String select = "SELECT * FROM " + Const.COMPANY_TABLE + " WHERE " +
                Const.COMPANY_UNP + "=? AND " + Const.COMPANY_PASSWORD + "=? ";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, company.getUnp());
            prSt.setString(2, company.getPassword());

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                _company.setName(resSet.getString(Const.COMPANY_NAME));
                _company.setPassword(resSet.getString(Const.COMPANY_PASSWORD));
                _company.setUnp(resSet.getString(Const.COMPANY_UNP));
                _company.setAdres(resSet.getString(Const.COMPANY_ADRES));
                _company.setBik(resSet.getString(Const.COMPANY_BIK));
                _company.setType(resSet.getString(Const.COMPANY_TYPE));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(_company.getBik().equals(bik))
            return _company;
        else
            return null;
    }
}
