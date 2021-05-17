package com.cab302qut.java.Organisation;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;

import java.util.ArrayList;

/**
 * An organisation that contains users who can sell assets owned by the organisation and can buy assets for the
 * organisation using the organisations credits
 * @author Brodie
 */
public class Organisation {
    private String name;
    private int currentCredits;
    private ArrayList<User> users;
    private ArrayList<OrganisationAsset> assetInventory;

    /**
     * Creates a new organisation
     * A new organisation will be initialised with an empty list of users, an empty list of assets and 0 credits
     * @param name the name of this organisation
     */
    public Organisation(String name) {
        this.name = name;
        this.currentCredits = 0;
        this.users = new ArrayList<>();
        this.assetInventory = new ArrayList<>();
    }

    /**
     * @return the name of this organisation
     */
    public String getName() {
        return name;
    }

    /**
     * Change the name of this organisation
     * @param name the new name of this organisation
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of users that are in this organisation
     * @return the list of users for this organisation
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Gets a user in this organisation by their name
     * @param name the name of the user being searched for
     * @return the user object with name searched for, null if no user is found
     */
    public User getUserByName(String name) {
        for (User user:users) {
            if(user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByID() {
        return null; // There is no user ID field at the moment
    }

    /**
     * Gets a user in this organisation by their username
     * @param username the users username
     * @return the user with this username, null if there is no user with this user name in this organisation
     */
    public User getUserByUsername(String username) {
        for (User user:users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Gets a list of users in this organisation that have the specified user type
     * @param userType the type of the user(s)
     * @return list of users with this type, null if there are no users with this type
     */
    public ArrayList<User> getUserByUserType(UserType userType) {
        ArrayList<User> usersOfType = new ArrayList<>();
            /*for (User user:users) {
                if(user.getUserType.equals(usertype)) { // TODO: currently no method in User class to get usertype
                    usersOfType.add(user);
                }
            }*/
        return usersOfType;
    }

    /**
     * Adds a new user to the list of users in this organisation
     * @param user the user to be added
     */
    public void addUser(User user) throws OrganisationException {

        if(users.contains(user)) {
            throw new OrganisationException("Cannot add a user that is already a part of this organisation");
        }
        else if(getUserByName(user.getName()) != null && getUserByUsername(user.getUsername()) != null) {
            throw new OrganisationException("Cannot add a user that has the same name and username as an existing user");
        }
        else {
            users.add(user);
        }
    }

    /**
     * Removes a user from the list of users in this organisation
     * @param user the user to be removed
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    public void removeUserByID() {
        // There is no user ID field at the moment
    }

    /**
     * Removes a user from the list of users in this organisation by their name
     * @param name the name of the user to be removed
     */
    public void removeUserByName(String name) {
        users.remove(getUserByName(name));
    }

    /**
     * Removes a user from the list of users in this organisation by their username
     * @param username the username of the user to be removed
     */
    public void removeUserByUsername(String username) {
        users.remove(getUserByUsername(username));
    }

    /**
     * Gets the current amount of credits this organisation has
     * @return this organisations current credits
     */
    public int getCredits() {
        return currentCredits;
    }

    /**
     * Adds credits to this organisations current credits
     * @param credits the amount of credits to be added
     * @throws OrganisationException if the amount of credits to be added is negative
     */
    public void addCredits(int credits) throws OrganisationException {
        if(credits < 0) {
            throw new OrganisationException("Cannot add negative credits");
        }
        else {
            currentCredits += credits;
        }
    }

    /**
     * Removes credits from this organisations current credits
     * @param credits the amount of credits to be removed
     * @throws OrganisationException if the amount of credits to be removed exceeds the amount of credits this
     * organisation has
     */
    public void removeCredits(int credits) throws OrganisationException {
        if(currentCredits - credits < 0) {
            throw new OrganisationException("Cannot remove more credits than the organisation has");
        }
        else if(credits < 0) {
            throw new OrganisationException("Cannot remove negative credits");
        }
        else {
            currentCredits -= credits;
        }
    }

    /**
     * Gets an asset that is controlled by this organisation
     * @param assetName the name of the asset
     * @return the asset, null if the organisation does not contain this asset
     */
    public OrganisationAsset getAsset(String assetName) {
        for (OrganisationAsset asset:assetInventory) {
            if(asset.getAssetName().equals(assetName)) {
                return asset;
            }
        }
        return null;
    }

    /**
     * Overloaded getAsset method with varargs to find multiple assets in this organisation
     * @param assetNames the names of the assets to be found
     * @return ArrayList of assets found within this organisation
     */
    public ArrayList<OrganisationAsset> getAsset(String ...assetNames) {
        ArrayList<OrganisationAsset> foundAssets = new ArrayList<>();
        for (String assetName:assetNames) {
            for (OrganisationAsset assetInInventory:assetInventory) {
                if(assetInInventory.getAssetName().equals(assetName)) {
                    foundAssets.add(assetInInventory);
                }
            }
        }
        return foundAssets;
    }

    /**
     * Gets the current inventory of assets in this organisation
     * @return ArrayList containing assets in inventory of this organisation
     */
    public ArrayList<OrganisationAsset> getAssetInventory() {
        return assetInventory;
    }

    public void addAsset(Asset asset) throws OrganisationException {
        if(getAsset(asset.getAssetName()) != null) {
            throw new OrganisationException("Cannot add an asset that already exists");
        }
        else {
            assetInventory.add(new OrganisationAsset(asset));
        }
    }

    /**
     * Adds a new asset to this organisations inventory
     * @param asset the asset to be added
     * @param quantity the quantity of this asset
     * @throws OrganisationException if the asset is already in this organisations inventory
     */
    public void addAsset(Asset asset, int quantity) throws OrganisationException {
        if(getAsset(asset.getAssetName()) != null) {
            throw new OrganisationException("Cannot add an asset that already exists");
        }
        else {
            assetInventory.add(new OrganisationAsset(asset, quantity));
        }
    }

    /**
     * Adds a new asset to this organisations inventory
     * @param assetName the name of the asset to be added
     * @throws OrganisationException if the asset is already in this organisations inventory
     */
    public void addNewAsset(String assetName) throws OrganisationException {
        if(getAsset(assetName) != null) {
            throw new OrganisationException("Cannot add an asset that already exists");
        }
        else {
            assetInventory.add(new OrganisationAsset(assetName));
        }
    }

    /**
     * Adds a new asset to this organisations inventory
     * @param assetName the name of the asset to be added
     * @param quantity the quantity of this asset
     * @throws OrganisationException if the asset is already in this organisations inventory
     */
    public void addNewAsset(String assetName, int quantity) throws OrganisationException {
        if(getAsset(assetName) != null) {
            throw new OrganisationException("Cannot add an asset that already exists");
        }
        else {
            assetInventory.add(new OrganisationAsset(assetName, quantity));
        }
    }

    /**
     * Gets the quantity of the asset held by this organisation
     * @param asset the asset
     * @return the quantity of this asset
     */
    public int getAssetQuantity(OrganisationAsset asset) {
        return asset.getQuantity();
    }

    /**
     * Gets the quantity of the asset held by this organisation
     * @param assetName the name of the asset
     * @return the quantity of this asset
     */
    public int getAssetQuantity(String assetName) {
        return getAsset(assetName).getQuantity();
    }

    /**
     * Sets the quantity of an asset held by this organisation
     * @param asset the asset
     * @param quantity the new quantity of this asset
     */
    public void setAssetQuantity(OrganisationAsset asset, int quantity) {
        asset.setQuantity(quantity);
    }

    /**
     * Sets the quantity of an asset held by this organisation
     * @param assetName the name of the asset
     * @param quantity the new quantity of this asset
     */
    public void setAssetQuantity(String assetName, int quantity) {
        getAsset(assetName).setQuantity(quantity);
    }

    /**
     * Removes an asset from the organisations inventory
     * @param asset the asset to be removed
     */
    public void removeAsset(OrganisationAsset asset) {
        assetInventory.remove(asset);
    }

    /**
     * Removes an asset from the organisations inventory
     * @param assetName the name of the asset
     */
    public void removeAsset(String assetName) {
        assetInventory.remove(getAsset(assetName));
    }

}
