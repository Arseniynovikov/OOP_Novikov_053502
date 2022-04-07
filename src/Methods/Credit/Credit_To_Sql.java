package Methods.Credit;

import DB.Const;
import DB.DatabaseHandler;
import Money.Credit;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Credit_To_Sql {
    public static void Make(Credit credit){
        DatabaseHandler handler = new DatabaseHandler();
        String insert = "INSERT INTO " + Const.CREDIT_TABLE + " (" + Const.CREDIT_NUMBER + "," +
                Const.CREDIT_BIK + "," + Const.CREDIT_CLIENTPASSPORT + "," + Const.CREDIT_COUNT + "," +
                Const.CREDIT_ALLOWED + "," + Const.CREDIT_MONTH + "," + Const.CREDIT_OPEN + "," +
                Const.CREDIT_PERCENT + ")" + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, credit.getNumber());
            prSt.setString(2, credit.getBik());
            prSt.setString(3, credit.getPassport());
            prSt.setDouble(4, credit.getCount());
            prSt.setBoolean(5, credit.getAllowed());
            prSt.setInt(6, credit.getMonth());
            prSt.setBoolean(7, credit.getOpen());
            prSt.setDouble(8, credit.getPercent());


            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
