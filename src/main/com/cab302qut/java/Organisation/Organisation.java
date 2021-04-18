package com.cab302qut.java.Organisation;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Users.User;

import java.util.ArrayList;
import java.util.List;

public class Organisation {
    private String name;
    private int currentCredits;
    private ArrayList<User> users;
    private ArrayList<Asset> assetInventory;

    public Organisation(String name) {
        this.name = name;
        this.currentCredits = 0;
        this.users = new ArrayList<>();
        this.assetInventory = new ArrayList<>();
    }

    public void addUser(User user) {

    }

    public void removeUser(User user) {

    }

    public User getUser(String name) {
        return null;
    }

    public Asset getAsset(String asset) {
        return null;
    }

    public int getCredits() {
        return currentCredits;
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {}

    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Asset> getAssetInventory() {
        return assetInventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        currentCredits = credits;
    }
}
