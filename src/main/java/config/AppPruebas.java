package config;

import com.mysql.cj.MysqlConnection;
import db.DbConnection;

import java.sql.SQLException;

public class AppPruebas {

    public static void main(String[]args){

        try {
            DbConnection dbConnection = new DbConnection();
            dbConnection.connect();
            System.out.println("Hello");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
