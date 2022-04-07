package Methods.Installment_Plan;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Installment_Plan_Delete {
    public static void Delete(String number) {
        DatabaseHandler handler = new DatabaseHandler();
        String delete = "DELETE FROM " + Const.INSTALLMENT_PLAN_TABLE + " WHERE " + Const.INSTALLMENT_PLAN_NUMBER + "=?";

        PreparedStatement prSt = null;
        try {
            prSt = handler.getDbConnection().prepareStatement(delete);

            prSt.setString(1, number);

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
