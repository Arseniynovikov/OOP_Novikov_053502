package Methods.Installment_Plan;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Installment_Plan_allowed {
    public static void Allowed(String number) {
        String change = "UPDATE " + Const.INSTALLMENT_PLAN_TABLE + " SET " + Const.INSTALLMENT_PLAN_ALLOWED + "=? WHERE " + Const.INSTALLMENT_PLAN_NUMBER + "=?";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(change);
            prSt.setBoolean(1, true);
            prSt.setString(2, number);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
