package com.cab302qut.java.Trades;

/**
 * A {@link Throwable} {@link Exception} when a trade has encountered an issue in the process of
 * either said transaction or the creation / modification of the trade.
 * @author Nicholas Bishop
 */
public class TradeException extends Exception {
    public TradeException(String message) {
        super(message);
    }
}
