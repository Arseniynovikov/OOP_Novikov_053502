package Methods.Account;

import DB.Const;
import DB.DatabaseHandler;
import Money.Bank_Account;
//import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Take_Account {
    public static ArrayList<Bank_Account> Accounts(String passport, String bik){
        ArrayList<Bank_Account> accounts = new ArrayList<Bank_Account>();
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();
        try {
            if(passport != null && bik != null) {//вернуть список всех счетов одного клиента в одном банке
                String select = "SELECT * FROM " + Const.BANKACCOUNT_TABLE + " WHERE " +
                        Const.BANKACCOUNT_USERPASS + "=? AND " + Const.BANKACCOUNT_BIK + "=?";

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, passport);
                prSt.setString(2, bik);
                resSet = prSt.executeQuery();

            } else if(bik != null) {//вернуть список всех сетов банка
                String select = "SELECT * FROM " + Const.BANKACCOUNT_TABLE + " WHERE " +
                         Const.BANKACCOUNT_BIK + "=?";

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, bik);
                resSet = prSt.executeQuery();

            }else {//вернуть список вообще ВСЕХ счетов
                String select = "SELECT * FROM " + Const.BANKACCOUNT_TABLE;

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                resSet = prSt.executeQuery();
            }



            while (resSet.next()) {
                String name = resSet.getString(Const.BANKACCOUNT_NUMBER);
                double count = resSet.getDouble(Const.BANKACCOUNT_COUNT);
                boolean bloking = resSet.getBoolean(Const.BANKACCOUNT_BLOKING);
                boolean freezing = resSet.getBoolean(Const.BANKACCOUNT_FREEZING);
                accounts.add(new Bank_Account(passport, name, count, bloking, freezing, bik));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
