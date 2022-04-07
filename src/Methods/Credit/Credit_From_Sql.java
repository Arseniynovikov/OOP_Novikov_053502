package Methods.Credit;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import Money.Credit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Credit_From_Sql {
    public static ArrayList<Credit> Take(String passport, String bik) {
        ResultSet resSet = null;
        ArrayList<Credit> credits = new ArrayList<>();

        DatabaseHandler handler = new DatabaseHandler();
        try {
            if(passport != null && bik != null) {
                String select = "SELECT * FROM " + Const.CREDIT_TABLE + " WHERE " + Const.CREDIT_CLIENTPASSPORT + "=? AND " +
                        Const.CREDIT_BIK + "=? ";
                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, passport);
                prSt.setString(2, bik);

                resSet = prSt.executeQuery();
            } else if(bik != null) {//вернуть список всех сетов банка
                String select = "SELECT * FROM " + Const.CREDIT_TABLE + " WHERE " +
                        Const.CREDIT_BIK + "=?";


                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, bik);
                resSet = prSt.executeQuery();

            } else {//вернуть список вообще ВСЕХ счетов
                String select = "SELECT * FROM " + Const.CREDIT_TABLE;


                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                resSet = prSt.executeQuery();
            }


            while (resSet.next()) {
                String number = resSet.getString(Const.CREDIT_NUMBER);
                int month = resSet.getInt(Const.CREDIT_MONTH);
                double count = resSet.getDouble(Const.CREDIT_COUNT);
                double percent = resSet.getDouble(Const.CREDIT_PERCENT);
                boolean allowed = resSet.getBoolean(Const.CREDIT_ALLOWED);
                boolean open = resSet.getBoolean(Const.CREDIT_OPEN);

                credits.add(new Credit(number, count, month, passport, percent, bik, allowed, open));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return credits;
    }
}
