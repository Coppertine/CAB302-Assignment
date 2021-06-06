package com.cab302qut.java.util;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;

import java.awt.event.ActionEvent;
import java.security.PublicKey;
import java.util.ArrayList;

public class StaticVariables {
    public static boolean loginSuccessful;
    public static boolean login;
    public static boolean assetRefresh;
    public static User user;
    public static UserType userType;
    public static Organisation userOrganisation;
    public static int organisationCredits;
    public static Asset[] assets;
    public static ArrayList<ArrayList<String>> organisationList;
    public static ArrayList<ArrayList<String>> orgsAssets;


    public static ArrayList<String> orgCreditsUpdateMsg;
    public static ArrayList<String> orgAssetNumUpdateMsg;
    public static ArrayList<AssetTableObj> selectedAssetPriceHistory;

    public static ArrayList<ArrayList<String>> pendingTradesData;



}
