package dao;

import java.sql.SQLException;

public class ChecklineDao {

    public static void addCheckLine(int checkid, int goodid, double goodcost, int goodcapacity) throws SQLException, ClassNotFoundException {

        String insertStatement = "insert into cashtest.checklines(checkid, goodid, goodcapacity, checklinesummary) values ("
                + "'" + checkid + "', '" + goodid + "', '" + goodcapacity + "', " + (goodcost*goodcapacity) + ")";
        System.out.println(insertStatement);
        DatabaseManager.updateQuery(insertStatement);
        System.out.println("executed :"+ insertStatement);

    }
}
