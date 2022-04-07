package Money;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Money_Transfer {
    public static void Transfer(Bank_Account client1, Bank_Account client2, double count) {
        if(client1.getCount() >= count) {
            DatabaseHandler handler = new DatabaseHandler();
            try {
                String update = "UPDATE " + Const.BANKACCOUNT_TABLE +   " SET " + Const.BANKACCOUNT_COUNT + "=? " + "WHERE "
                        + Const.BANKACCOUNT_NUMBER + "=?";
                PreparedStatement prSt = handler.getDbConnection().prepareStatement(update);

                prSt.setDouble(1, client1.getCount() - count);
                prSt.setString(2, client1.getNumber());

                prSt.executeUpdate();

                update = "UPDATE " + Const.BANKACCOUNT_TABLE +   " SET "+ Const.BANKACCOUNT_COUNT + "=? " + "WHERE "
                        + Const.BANKACCOUNT_NUMBER + "=?";
                prSt = handler.getDbConnection().prepareStatement(update);

                prSt.setDouble(1, client2.getCount() + count);
                prSt.setString(2, client2.getNumber());

                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            client1.setCount(client1.getCount() - count);
            client2.setCount(client2.getCount() + count);
            System.out.printf("%f   %f", client1.getCount(),client2.getCount());
        }
        else {
            System.out.print("\nОшибка операции (недостаточно средств).\n");
        }
    }
}
