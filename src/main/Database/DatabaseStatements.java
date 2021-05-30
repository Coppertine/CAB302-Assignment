package Database;

import java.io.Console;
import java.security.PublicKey;
import java.security.cert.PKIXCertPathBuilderResult;
import java.sql.SQLException;

/***
 * Class containing methods when performing
 * data manipulation.
 *
 * @author Giane
 */
public class DatabaseStatements {
    DatabaseConnection db;

    // helps to ensure correct table name is referenced down the code
    // table names in db currently require no spaces
    enum Tables {
        organisations,
        users,
        assets,
        currentAssets,
        currentTrades,
        tradeHistory

    }

    public DatabaseStatements() throws SQLException {
        this.db = new DatabaseConnection();
    }

    public void InsertIntoOrganisations(String organisationName) throws SQLException{
        //statement.execute("INSERT INTO organisations (name, credits) VALUES('Organisation XYZ','5123');");
        String theStatement = String.format("INSERT INTO `%s` (organisationID) VALUES (%s);", Tables.organisations.toString(),organisationName);
        db.ExecuteStatement(theStatement);
    }

    public void InsertIntoAssets(String assetName) throws SQLException{

        //TODO: allow DB to auto assign asset ID or the organisation to provide

        //statement.execute("INSERT INTO organisations (name, credits) VALUES('Organisation XYZ','5123');");
        String theStatement = String.format("INSERT INTO `%s` (assetName) VALUES (%s);", Tables.assets.toString(),assetName);
        db.ExecuteStatement(theStatement);
    }




}
