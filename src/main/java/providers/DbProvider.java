package providers;

import db.DbConnection;

import java.sql.SQLException;

public class DbProvider {

    public DbProvider() {
    }

    public void connection(String sql) throws SQLException {
        DbConnection connection = new DbConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }
}
