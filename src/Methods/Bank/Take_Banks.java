package Methods.Bank;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import Money.Bank_Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Take_Banks {
    public static ArrayList<Bank> Take(){
        ArrayList<Bank> banks = new ArrayList<Bank>();
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.BANKS_TABLE;
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (resSet.next()) {
                String name = resSet.getString(Const.BANKS_NAME);
                String bik = resSet.getString(Const.BANKS_BIK);
                String unp = resSet.getString(Const.BANKS_UNP);
                String adres = resSet.getString(Const.BANKS_ADRES);
                String password = resSet.getString(Const.BANKS_PASSWORD);

                banks.add(new Bank("bank" ,name ,unp, bik, adres, password));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return banks;
    }
}
