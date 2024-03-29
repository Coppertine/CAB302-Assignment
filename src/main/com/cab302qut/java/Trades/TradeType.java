package com.cab302qut.java.Trades;

/**
 * The status of the current trade.
 * @author Nicholas Bishop
 */
public enum TradeType {
    /**
     * The type of trade when the end user is either asking to purchase or is currently selling said asset.
     */
    OPEN,

    /**
     * Specifies that the trade has been completed between the end users.
     */
    CLOSED
}
