package com.cab302qut.java.Users;

import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Users.UserType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
     *
     * @param name         The user's display name.
     * @param organisation The organisation the user is a part of.
     * @param username     The username of the said user.
     * @param password     The password of the said user, hashed using ...
     * @param userType     The type of user assigned.
     */
    public User(String name, Organisation organisation, String username, String password, UserType userType) {
        this.name = name;
        this.organisation = organisation;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public User(String name, String username, String password, UserType userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
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
        String hashedPassword = HashPassword(password);
        this.password = hashedPassword;
    }

    /**
     * @return The current display name of specified user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
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

    public UserType getUserType() {
        return userType;
    }
    public UserType setUserType(UserType userType) {
        this.userType = userType;
        return null;
    }

    /**
     * hashes the password with the MD5 algorithm
     *
     * @param unhashed the users string input password
     * @return the hashed password
     */
    public String HashPassword(String unhashed) {
        //Needs to actually hash the password
        String hashedPassword = null;
        try {
            //creates message digest object needed.
            //built in class to help hash passwords
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(unhashed.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            System.out.println(md.digest());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hashedPassword = sb.toString();
            //prints to console hashed password
            System.out.println(hashedPassword);
            return hashedPassword;

            //catches if an algorithm doesn't exist
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
