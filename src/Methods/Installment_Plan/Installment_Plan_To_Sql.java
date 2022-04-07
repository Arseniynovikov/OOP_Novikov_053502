package Methods.Installment_Plan;

import DB.Const;
import DB.DatabaseHandler;
import Money.Credit;
import Money.Installment_Plan;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Installment_Plan_To_Sql {
    public static void Make(Installment_Plan installment_plan){
        DatabaseHandler handler = new DatabaseHandler();
        String insert = "INSERT INTO " + Const.INSTALLMENT_PLAN_TABLE + " (" + Const.INSTALLMENT_PLAN_NUMBER + "," +
                Const.INSTALLMENT_PLAN_BIK + "," + Const.INSTALLMENT_PLAN_CLIENTPASSPORT + "," + Const.INSTALLMENT_PLAN_COUNT + "," +
                Const.INSTALLMENT_PLAN_ALLOWED + "," + Const.INSTALLMENT_PLAN_MONTH + "," + Const.INSTALLMENT_PLAN_OPEN +
                 ") " + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, installment_plan.getNumber());
            prSt.setString(2, installment_plan.getBik());
            prSt.setString(3, installment_plan.getPassport());
            prSt.setDouble(4, installment_plan.getCount());
            prSt.setBoolean(5, installment_plan.getAllowed());
            prSt.setInt(6, installment_plan.getMonth());
            prSt.setBoolean(7, installment_plan.getOpen());


            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
