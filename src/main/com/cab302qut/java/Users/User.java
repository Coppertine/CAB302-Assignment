package main.com.cab302qut.java.Users;

import main.com.cab302qut.java.Organisation.Organisation;

/**
 *
 */
public class User {
    private String name;
    private Organisation organisation;
    private String username;
    private String password;
    private UserType userType;
    /**
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

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
