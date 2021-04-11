package com.cab302qut.java.Users;

import com.cab302qut.java.Organisation.Organisation;

/**
 * The account of the program which can buy and/or sell items to other users in other organisations.
 */
public class User {
    private String name;
    private Organisation organisation;
    private String username;
    private String password;
    private UserType userType;

    /**
     * Creates a new user which is to be placed into the Organisation's user list.
     * @param name The user's display name.
     * @param organisation The organisation the user is a part of.
     * @param username The username of the said user.
     * @param password The password of the said user, hashed using ...
     * @param userType The type of user assigned.
     */
    public User(String name, Organisation organisation, String username, String password, UserType userType) {
        this.name = name;
        this.organisation = organisation;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User() {

    }

    /**
     * @return The hashed password of specified user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The hashed password of specified user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The current display name of specified user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The current display name of specified user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The organisation of specified user.
     */
    public Organisation getOrganisation() {
        return organisation;
    }

    /**
     * @param organisation The organisation of specified user.
     */
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
