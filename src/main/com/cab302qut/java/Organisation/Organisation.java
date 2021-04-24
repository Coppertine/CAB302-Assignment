package main.com.cab302qut.java.Organisation;

import main.com.cab302qut.java.Items.Asset;
import main.com.cab302qut.java.Users.User;
import main.com.cab302qut.java.Users.UserType;

import java.util.ArrayList;
import java.util.List;

public class Organisation {
    private String name;
    private int currentCredits;
    private ArrayList<User> users;
    private ArrayList<OrganisationAsset> assetInventory;

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

    public OrganisationAsset getAsset(String asset) {
        return null;
    }

    public ArrayList<OrganisationAsset> getAsset(String ...asset) {
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

    public ArrayList<OrganisationAsset> getAssetInventory() {
        return assetInventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        currentCredits = credits;
    }

    public User getUserByName(String userName) {
        return null;
    }

    public User getUserByID() {
        return null;
    }

    public User getUserByUsername(String username) {
        return null;
    }

    public ArrayList<User> getUserByUserType(UserType userType) {
        return null;
    }

    public void removeUserByID() {

    }

    public void removeUserByName(String userName) {
    }

    public void removeUserByUsername(String username) {
    }

    public void addAsset(Asset asset) {
    }

    public void addAsset(Asset asset, int quantity) {
    }

    public void addNewAsset(String assetName) {
    }

    public void addNewAsset(String assetName, int quantity) {
    }

    public int getAssetQuantity(OrganisationAsset asset) {
        return 0;
    }

    public int getAssetQuantity(String assetName) {
        return 0;
    }

    public void setAssetQuantity(String assetName, int i) {

    }

    public void removeAsset(OrganisationAsset asset) {

    }

    public void removeAsset(String assetName) {

    }

}
