package com.cab302qut.java.util;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;

import java.util.ArrayList;

public class StaticVariables {
    public static boolean loginSuccessful;
    public static boolean login;
    public static boolean assetRefresh;
    public static User user; //username, password, usertype, organisation
    public static UserType userType;
    public static Organisation userOrganisation; // organisation information of the user
    public static int organisationCredits; // amount of credits the organisation has
    public static Asset[] assets;
    public static ArrayList<ArrayList<String>> organisationsAssets;// assets and credits for the organisation
    public static ArrayList<ArrayList<String>> organisations;

}
