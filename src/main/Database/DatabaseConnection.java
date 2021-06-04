package Database;

import javax.swing.plaf.nimbus.State;
import  java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    Statement statement;
    Connection connection;

    public DatabaseConnection() throws SQLException {
        Connect();
    }

    public void Connect() throws SQLException {
        // for now,assumes local machine running server has already created the database
        this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/tradeserver","root","passwordCAB302");
        this.statement = connection.createStatement();
    }

    public void CloseConnection() throws SQLException {
        connection.close();
    }

    public void ExecuteStatement(String aStatement) throws SQLException{
        statement.execute((aStatement));

    }

}
