package test;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Organisation.OrganisationException;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestOrganisation {
    private Asset testAsset;
    private User testUser1;
    private User testUser2;
    private User testUser3;
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
    public void addCredits() {
        //testOrg1.addCredits(10);
        //assertEquals(testOrg1.getCredits(), 10, "Failed to add credits");
    }

    // Add negative credits
    @Test
    public void addNegativeCredits() {
        assertThrows(OrganisationException.class, () -> testOrg1.addCredits(-10));
    }

    // Get balance of credits
    @Test
    public void getCredits() {
        //testOrg1.addCredits(10);
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
        testUser3 = new User("newUser", testOrg1, "newUserName", "newPassword", UserType.Default);
        testOrg1.addUser(testUser1);
        testOrg1.addUser(testUser2);
        testOrg1.addUser(testUser3);
        ArrayList<User> orgDefaultUserList = new ArrayList<>();
        orgDefaultUserList.add(testUser1);
        orgDefaultUserList.add(testUser3);
        assertEquals(testUser1, testOrg1.getUserByName("testUser"), "Failed to get user by name"); // TODO: implement getUserByName
        //assertEquals(testUser2, testOrg1.getUserByID(testUser2.ID), "Failed to get user by ID"); // TODO: implement getUserByID
        assertEquals(testUser1, testOrg1.getUserByUsername("testUserName"), "Failed to get user by user name"); // TODO: implement getUserByUsername
        assertEquals(orgDefaultUserList, testOrg1.getUserByUserType(UserType.Default), "Failed to get users by user type"); // TODO: implement getUserByUserType
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
        orgUserList2.add(testUser1);

        testOrg1.removeUser(testUser1);
        assertEquals(orgUserList2, testOrg1.getUsers(), "Failed to delete user from organisation");

        testOrg1.addUser(testUser1);
        //testOrg1.removeUserByID(testUser2.ID); // TODO: implement removeUserByID
        assertEquals(orgUserList1, testOrg1.getUsers(), "Failed to delete user by ID");

        testOrg1.addUser(testUser2);
        testOrg1.removeUserByName("testUser"); // TODO: implement removeUserByName
        assertEquals(orgUserList2, testOrg1.getUsers(), "Failed to delete user by name");

        testOrg1.addUser(testUser1);
        testOrg1.removeUserByUsername("adminUserName"); // TODO: implement removeUserByUsername
        assertEquals(orgUserList1, testOrg1.getUsers(), "Failed to delete user by username");
    }

    // Add duplicate user
    // Adding the same user object to an organisation should not be possible
    // Adding two users with identical fields should not be possible
    @Test
    public void addDuplicateUser() {
        testUser1 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
        testUser2 = new User("testUser", testOrg1, "testUserName", "testPassword", UserType.Default);
//        testOrg1.addUser(testUser1);
//        assertThrows(OrganisationException.class, () -> testOrg1.addUser(testUser1));
//        assertThrows(OrganisationException.class, () -> testOrg1.addUser(testUser2));
    }

    // Add organisation asset (must be one from Asset)
    @Test
    public void addExistingAsset() {

    }

    // if new organisation asset not found in asset listings, add asset into listings
    @Test
    public void addNewAsset() {

    }

    // Get list of organisation assets
    @Test
    public void getOrgAssetList() {

    }

    // Get organisation asset
    @Test
    public void getOrgAsset() {

    }

    // Get multiple organisation assets
    @Test
    public void getMultipleAssets() {

    }

    // Get quantity of organisation asset
    @Test
    public void getAssetQuantity() {

    }

    // Remove specific organisation asset
    @Test
    public void removeOrgAsset() {

    }

    // Get price of organisation asset (must be same as the Asset's most recent purchase)
    @Test
    public void getOrgAssetPrice() {

    }
}
