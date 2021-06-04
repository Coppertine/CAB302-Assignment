package com.cab302qut.java.util;

import java.sql.SQLException;

/***
 * Class containing methods when performing
 * data manipulation.
 *
 * @author Giane
 */
public class DatabaseStatements {
    DatabaseConnection db;

    public static String GetAllTrades() {
        return("SELECT * FROM '" + Tables.currentTrades.toString().toLowerCase() + "' ;");
    }

    public static String GetYearTrades(){
        return("SELECT * FROM `tradeHistory` WHERE YEAR(`date`) = 2020;");
    }
    public static String GetUsers(){
        return("SELECT * FROM `users` ;");
    }

    // helps to ensure correct table name is referenced down the code
    // table names in db currently require no spaces
    public enum Tables {
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
        String statement = String.format("INSERT INTO `%s` (organisationID) VALUES (%s);", Tables.organisations.toString(),organisationName);
        db.executeStatement(statement);
    }

    public void InsertIntoAssets(String assetName) throws SQLException{

        //TODO: allow DB to auto assign asset ID or the organisation to provide

        //statement.execute("INSERT INTO organisations (name, credits) VALUES('Organisation XYZ','5123');");
        String statement = String.format("INSERT INTO `%s` (assetName) VALUES (%s);", Tables.assets.toString(), assetName);
        db.executeStatement(statement);
    }

    public void InsertIntoUsers(String userName) throws SQLException{

    }



}