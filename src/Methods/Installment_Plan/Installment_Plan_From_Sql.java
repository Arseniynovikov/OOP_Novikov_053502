package Methods.Installment_Plan;

import DB.Const;
import DB.DatabaseHandler;
import Money.Credit;
import Money.Installment_Plan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Installment_Plan_From_Sql {
    public static ArrayList<Installment_Plan> Take(String passport, String bik) {
        ResultSet resSet = null;
        ArrayList<Installment_Plan> installment_plans = new ArrayList<>();



        DatabaseHandler handler = new DatabaseHandler();
        try {
            if(passport != null && bik != null) {
                String select = "SELECT * FROM " + Const.INSTALLMENT_PLAN_TABLE + " WHERE " + Const.INSTALLMENT_PLAN_CLIENTPASSPORT + "=? AND " +
                        Const.INSTALLMENT_PLAN_BIK + "=? ";

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, passport);
                prSt.setString(2, bik);

                resSet = prSt.executeQuery();
            } else if(bik != null) {//вернуть список всех сетов банка
                String select = "SELECT * FROM " + Const.INSTALLMENT_PLAN_TABLE + " WHERE " +
                        Const.INSTALLMENT_PLAN_BIK + "=?";


                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, bik);
                resSet = prSt.executeQuery();

            } else {//вернуть список вообще ВСЕХ счетов
                String select = "SELECT * FROM " + Const.INSTALLMENT_PLAN_TABLE;


                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                resSet = prSt.executeQuery();
            }

            while (resSet.next()) {
                String number = resSet.getString(Const.INSTALLMENT_PLAN_NUMBER);
                int month = resSet.getInt(Const.INSTALLMENT_PLAN_MONTH);
                double count = resSet.getDouble(Const.INSTALLMENT_PLAN_COUNT);
                boolean allowed = resSet.getBoolean(Const.INSTALLMENT_PLAN_ALLOWED);
                boolean open = resSet.getBoolean(Const.INSTALLMENT_PLAN_OPEN);

                installment_plans.add(new Installment_Plan(number, month, count, passport, bik, allowed, open));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return installment_plans;
    }
}
