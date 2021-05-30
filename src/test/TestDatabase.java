package com.cab302qut.java.test;

import com.cab302qut.java.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public class TestDatabase {

    @BeforeAll
    public void init() {

    }

    public  static  void  main(String[] args) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        try {

            // NOTE: this sequence of statements can only be done properly
            // if the database does not already have ALL the tables,
            // rerunning the statements will conflict with foreign key constraints etc.
            //
            // following creates tables if they do not already exist
            // create independent objects/ tables first
            // ( ` ) back ticks used for identifiers
            //
            // fixable issues experienced: make sure foreign key constraint names are unique

            db.executeStatement("CREATE OR REPLACE TABLE `organisations` " +
                    "( `organisationID` int NOT NULL AUTO_INCREMENT, `organisationName` char(50), `credits` double, " +
                    "PRIMARY KEY (`organisationID`) );");

            db.executeStatement("CREATE OR REPLACE TABLE `assets` " +
                    "( `assetID` int NOT NULL, `assetType` char(50), PRIMARY KEY (`assetID`) );");

            // rest of tables with foreign key constraints
            db.executeStatement("CREATE OR REPLACE TABLE `users` " +
                    "( `userID` int NOT NULL AUTO_INCREMENT, `userName` char(50) NOT NULL, `password` char(50) NOT NULL, " +
                    "`accountType` char(10), `organisationID` int NOT NULL, " +
                    "PRIMARY KEY (`userID`), " +
                    "CONSTRAINT `fk_userOrgID` FOREIGN KEY (`organisationID`) REFERENCES `organisations` (`organisationID`) );");

            db.executeStatement("CREATE OR REPLACE TABLE `currentAssets` " +
                    "( `organisationID` int NOT NULL, `assetID` int NOT NULL, `quantity` int, PRIMARY KEY (`organisationID`, `assetID`), " +
                    "CONSTRAINT `fk_currAssOrgID` FOREIGN KEY (`organisationID`) REFERENCES `organisations` (`organisationID`), " +
                    "CONSTRAINT `fk_currAssAssetID` FOREIGN KEY (`assetID`) REFERENCES `assets` (`assetID`) );");

            db.executeStatement("CREATE OR REPLACE TABLE `currentTrades` " +
                    "( `tradeID` int NOT NULL, `userID` int NOT NULL, `organisationID` int NOT NULL, " +
                    "`assetID` int NOT NULL, `quantity` int, `price` double, `tradeType` char(5), `date` date, " +
                    "PRIMARY KEY (`tradeID`), " +
                    "CONSTRAINT `fk_currTradeUserID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`), " +
                    "CONSTRAINT `fk_currTradeOrganisationID` FOREIGN KEY (`organisationID`) REFERENCES `organisations` (`organisationID`), " +
                    "CONSTRAINT `fk_currTradeAssetID` FOREIGN KEY (`assetID`) REFERENCES `assets` (`assetID`));");

            db.executeStatement("CREATE OR REPLACE TABLE `tradeHistory` " +
                    "( `tradeID` int NOT NULL, `buyerID` int NOT NULL, `sellerID` int NOT NULL, " +
                    "`assetID` int NOT NULL, `quantity` int, `price` double, `tradeType` char(5), `date` date, " +
                    "PRIMARY KEY (`tradeID`), " +
                    "CONSTRAINT `fk_tradHistBuyerID` FOREIGN KEY (`buyerID`) REFERENCES `users` (`userID`), " +
                    "CONSTRAINT `fk_tradHistSellerID` FOREIGN KEY (`sellerID`) REFERENCES `users` (`userID`), " +
                    "CONSTRAINT `fk_tradHistAssetID` FOREIGN KEY (`assetID`) REFERENCES `assets` (`assetID`));");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            db.CloseConnection();
        }
    }
}