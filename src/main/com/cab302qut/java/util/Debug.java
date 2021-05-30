package com.cab302qut.java.util;

public class Debug {
    public static void log(String message)
    {

    }

    public static void log(Exception exception)
    {
        log(exception.getMessage());
    }
}
