package main.com.cab302qut.java.Organisation;

import main.com.cab302qut.java.Items.Asset;
import main.com.cab302qut.java.Users.User;

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
        return 0;
    }

    public void addCredits(int credits) {
    }

    public void removeCredits(int credits) {}

    public String getName() {
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Asset> getAssetInventory() {
        return assetInventory;
    }

    public void setName(String name) {

    }

    public void setCredits()
    {

    }
}
