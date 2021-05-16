
package com.cab302qut.java.test;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Organisation.OrganisationAsset;
import com.cab302qut.java.Organisation.OrganisationException;

import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestOrganisation {
    private Asset testAsset1;
    private OrganisationAsset testOrgAsset1;
    private User testUser1;
    private User testUser2;
    private Organisation testOrg1;
    private Organisation testOrg2;

    // Add organisation
    @BeforeEach @Test
    public void addOrg() {
        testOrg1 = new Organisation("Test1");
        testOrg2 = new Organisation("Test2");
    }

    // Get name of organisation
    @Test
    public void getOrgName() {
        assertEquals(testOrg1.getName(), "Test1", "Failed to retrieve Organisation name");
    }

    // Set name of organisation
    @Test
    public void setOrgName() {
        testOrg1.setName("New Name");
        assertEquals(testOrg1.getName(), "New Name", "Failed setting new Organisation name.");
    }

    // Remove Organisation

    // Add credits
    @Test
    public void addCredits() throws OrganisationException {
        testOrg1.addCredits(10);
        assertEquals(testOrg1.getCredits(), 10, "Failed to add credits");
    }

    // Add negative credits
    @Test
    public void addNegativeCredits() {
        assertThrows(OrganisationException.class, () -> testOrg1.addCredits(-10));
    }

    // Get balance of credits
    @Test
    public void getCredits() throws OrganisationException {
        testOrg1.addCredits(10);
        assertEquals(testOrg1.getCredits(), 10, "Failed to retrieve Organisation's credits");
    }

    // Remove credits
    @Test
    public void removeCredits() throws OrganisationException {
        testOrg1.addCredits(10);
        testOrg1.removeCredits(5);
        assertEquals(testOrg1.getCredits(), 5, "Failed to remove credits.");
    }

    // Remove negative credits
    @Test
    public void removeNegativeCredits() throws OrganisationException {
        testOrg1.addCredits(10);
        assertThrows(OrganisationException.class, () -> testOrg1.removeCredits(-5));
    }

    // Remove too many credits
    @Test
    public void removeTooManyCredits() throws OrganisationException {
        testOrg1.addCredits(10);
        assertThrows(OrganisationException.class, () -> testOrg1.removeCredits(15));
    }

    // Add User through organisation
    // Should new users have an org at time of creation?
    // Should a user be able to join multiple organisations? or should adding a user to an organisation
    // change what organisation they are in?
    @Test
    public void addUserOrg() throws OrganisationException {
        testUser1 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testOrg1.addUser(testUser1);
        ArrayList<User> orgUserList = new ArrayList<>();
        orgUserList.add(testUser1);
        assertEquals(orgUserList, testOrg1.getUsers(), "Failed to add user to organisation");
    }

    // Get user by id, display name, username
    @Test
    public void getUserByField() throws OrganisationException {
        testUser1 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testUser2 = new User("adminUser", testOrg1, "adminUserName", "adminPassword", UserType.Administrator);
        User testUser3 = new User("newUser", testOrg1, "newUserName", "newPassword", UserType.Default);
        testOrg1.addUser(testUser1);
        testOrg1.addUser(testUser2);
        testOrg1.addUser(testUser3);
        ArrayList<User> orgDefaultUserList = new ArrayList<>();
        orgDefaultUserList.add(testUser1);
        orgDefaultUserList.add(testUser3);
        assertEquals(testUser1, testOrg1.getUserByName("testUser"), "Failed to get user by name");
        //assertEquals(testUser2, testOrg1.getUserByID(testUser2.ID), "Failed to get user by ID"); // user ID not implemented yet
        assertEquals(testUser1, testOrg1.getUserByUsername("testUserName"), "Failed to get user by user name");
        //assertEquals(orgDefaultUserList, testOrg1.getUserByUserType(UserType.Default), "Failed to get users by user type");
    }

    // Get multiple users
    @Test
    public void getUserList() throws OrganisationException {
        testUser1 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testUser2 = new User("adminUser", testOrg1, "adminUserName", "adminPassword", UserType.Administrator);
        testOrg1.addUser(testUser1);
        testOrg1.addUser(testUser2);
        ArrayList<User> orgUserList = new ArrayList<>();
        orgUserList.add(testUser1);
        orgUserList.add(testUser2);
        assertEquals(orgUserList, testOrg1.getUsers(), "Failed to get organisation users");
    }

    // Delete user by id, display name, username
    @Test
    public void deleteUser() throws OrganisationException {
        testUser1 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testUser2 = new User("adminUser", testOrg1, "adminUserName", "adminPassword", UserType.Administrator);
        testOrg1.addUser(testUser1);
        testOrg1.addUser(testUser2);
        ArrayList<User> orgUserList1 = new ArrayList<>();
        orgUserList1.add(testUser1);
        ArrayList<User> orgUserList2 = new ArrayList<>();
        orgUserList2.add(testUser2);

        testOrg1.removeUser(testUser1);
        assertEquals(orgUserList2, testOrg1.getUsers(), "Failed to delete user from organisation");

        //testOrg1.addUser(testUser1);
        //testOrg1.removeUserByID(testUser2.ID); // user ID not implemented yet
        //assertEquals(orgUserList1, testOrg1.getUsers(), "Failed to delete user by ID");

        //testOrg1.addUser(testUser2);
        testOrg1.removeUserByName("testUser");
        assertEquals(orgUserList2, testOrg1.getUsers(), "Failed to delete user by name");

        testOrg1.addUser(testUser1);
        testOrg1.removeUserByUsername("adminUserName");
        assertEquals(orgUserList1, testOrg1.getUsers(), "Failed to delete user by username");
    }

    // Add duplicate user
    // Adding the same user object to an organisation should not be possible
    // Adding two users with identical fields should not be possible
    @Test
    public void addDuplicateUser() throws OrganisationException {
        testUser1 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testUser2 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testOrg1.addUser(testUser1);
        assertThrows(OrganisationException.class, () -> testOrg1.addUser(testUser1));
        assertThrows(OrganisationException.class, () -> testOrg1.addUser(testUser2));
    }

    // Add organisation asset (must be one from Asset)
    @Test
    public void addExistingAsset() throws OrganisationException {
        testAsset1 = new Asset("test");
        testOrgAsset1 = new OrganisationAsset(testAsset1);
        testOrg1.addAsset(testAsset1);
        assertNotNull(testOrg1.getAsset("test"), "Failed to add asset to organisation.");
    }

    // if new organisation asset not found in asset listings, add asset into listings
    @Test
    public void addNewAsset() throws OrganisationException {
        testOrg1.addNewAsset("test");
        assertFalse(testOrg1.getAssetInventory().isEmpty(), "Failed to add new asset to organisation");
    }

    // Get list of organisation assets
    @Test
    public void getOrgAssetList() throws OrganisationException {
        testAsset1 = new Asset("test");
        testOrgAsset1 = new OrganisationAsset("test");
        ArrayList<OrganisationAsset> orgAssets = new ArrayList<>();
        orgAssets.add(testOrgAsset1);
        testOrg1.addAsset(testAsset1);
        assertArrayEquals(orgAssets.toArray(), testOrg1.getAssetInventory().toArray(), "Failed to retrieve asset inventory of organisation");
    }

    // Get organisation asset
    @Test
    public void getOrgAsset() throws OrganisationException {
        testAsset1 = new Asset("test");
        testOrgAsset1 = new OrganisationAsset(testAsset1);
        testOrg1.addAsset(testAsset1);
        assertNotNull(testOrg1.getAsset("test"), "Failed to retrieve organisation asset");
    }

    // Get multiple organisation assets
    @Test
    public void getMultipleAssets() throws OrganisationException {
        testAsset1 = new Asset("test1");
        Asset testAsset2 = new Asset("test2");
        testOrgAsset1 = new OrganisationAsset("test1");
        OrganisationAsset testOrgAsset2 = new OrganisationAsset("test2");
        ArrayList<OrganisationAsset> orgAssets = new ArrayList<>();
        orgAssets.add(testOrgAsset1);
        orgAssets.add(testOrgAsset2);
        testOrg1.addAsset(testAsset1);
        testOrg1.addAsset(testAsset2);
        assertArrayEquals(orgAssets.toArray(), testOrg1.getAsset("test1", "test2").toArray(), "Failed to retrieve specified assets from organisation");
    }

    // Get quantity of organisation asset
    @Test
    public void getAssetQuantity() throws OrganisationException {
        testAsset1 = new Asset("test");
        testOrgAsset1 = new OrganisationAsset("test", 10);
        testOrg1.addAsset(testAsset1, 10);
        assertEquals(testOrgAsset1.getQuantity(), testOrg1.getAssetQuantity("test"), "Failed to retrieve asset quantity from organisation");
    }

    // Set quantity of organisation asset
    @Test
    public void setAssetQuantity() throws OrganisationException {
        testAsset1 = new Asset("test");
        testOrgAsset1 = new OrganisationAsset("test", 10);
        testOrg1.addAsset(testAsset1, 5);
        testOrg1.setAssetQuantity("test", 10);
        assertEquals(testOrgAsset1.getQuantity(), testOrg1.getAssetQuantity("test"), "Failed to set new asset quantity");
    }

    // Remove specific organisation asset
    @Test
    public void removeOrgAsset() throws OrganisationException {
        testAsset1 = new Asset("test");
        testOrg1.addAsset(testAsset1);
        assertFalse(testOrg1.getAssetInventory().isEmpty());
        testOrg1.removeAsset("test");
        assertTrue(testOrg1.getAssetInventory().isEmpty(), "Failed to remove asset from organisation inventory");
    }

    // Get price of organisation asset (must be same as the Asset's most recent purchase)
    @Test
    public void getOrgAssetPrice() {
        // Is this function more relevant to the Asset class? the price of an organisation asset should be the same as
        // the price of the actual asset.
    }
}
