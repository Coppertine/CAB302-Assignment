package com.cab302qut.java.test;


import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Organisation.OrganisationAsset;
import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.Trades.TradeException;
import com.cab302qut.java.Trades.TradeType;
import com.cab302qut.java.Users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTrade {
    private Asset testItem1;
    private Trade testTrade1;
    private User testUser1;
    private User testUser2;
    private Organisation testOrganisation1;
    private Organisation testOrganisation2;

    @BeforeEach
    void init() {
        testItem1 = new OrganisationAsset(); // TODO: Add additional constructors once they are implemented.
        testUser1 = new User(); // TODO: Add additional constructors once they are implemented.
        testUser2 = new User(); // TODO: Add additional constructors once they are implemented.
        testOrganisation1 = new Organisation("organistaion1");
        testOrganisation2 = new Organisation("organistaion2");
    }

    // Add open trade
    @Test
    public void addOpenTrade() {
        // Trade type should be OPEN as default.

        testTrade1 = new Trade(testItem1, testUser1);
        assertEquals((testTrade1.getTradeType()), TradeType.OPEN.toString());
    }

    // Add closed trade
    @Test
    public void addClosedTrade() {
        testTrade1 = new Trade(testItem1, testUser1, testUser2, Date.valueOf(LocalDate.now()), TradeType.CLOSED);
        assertEquals(Date.valueOf(LocalDate.now()),testTrade1.getTradeDate());
        assertEquals(testUser1,testTrade1.getBuyingUser());
        assertEquals( testUser2,testTrade1.getSellingUser());
        assertEquals(testItem1,testTrade1.getTradeAsset());
        assertEquals(TradeType.CLOSED,testTrade1.getTradeType());
    }

    // Set trade as closed
    @Test
    public void setTradeClosed() {

        testTrade1 = new Trade(testItem1, testUser1);
        try {
            testTrade1.setTradeType(TradeType.CLOSED);
        } catch (TradeException e) {
            e.printStackTrace();
        }
        assertEquals(TradeType.CLOSED,testTrade1.getTradeType());
    }

    // Set trade as open
    @Test
    public void setTradeOpen() {
        testTrade1 = new Trade(testItem1, testUser1, testUser2, Date.valueOf(LocalDate.now()), TradeType.CLOSED);

        assertThrows(TradeException.class, () -> testTrade1.setTradeType(TradeType.OPEN));
    }

    // Get price from trade
    @Test
    public void getTotalTradePrice() {

    }

    // Get quantity from trade
    @Test
    public void getQuantity() {

    }

    // Get price per asset from trade
    @Test
    public void getPricePerAsset() {

    }

    // Add date to trade
    @Test
    public void addDate() {

    }

    // Get date to trade
    @Test
    public void getDate() {}

    // Complete trade transaction of trade between organisations.
    @Test
    public void completeTransaction() {

    }

    // Check balance of organisations if trade can be processed.
    @Test
    public void getBalanceOfOrganisations() {

    }
    // Set asset to trade
    @Test
    public void setAsset() {

    }

    // Set buying/selling user/organisation
    @Test
    public void setBuyingUser() {

    }

    @Test
    public void setSellingUser() {

    }

    @Test
    public void setBuyingOrganisation() {

    }
    @Test
    public void setSellingOrganisation() {

    }
    // Get trade status
    @Test
    public void getTradeStatus() {

    }
}
