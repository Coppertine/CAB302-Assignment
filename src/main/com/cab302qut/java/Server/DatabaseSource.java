package com.cab302qut.java.Server;
import com.cab302qut.java.Users.User;

public interface DatabaseSource {
    /**
     * Adds a User to the users list, if they are not already in the list
     *
     * @param u user to add
     */
    void addPerson(User u);

    /**
     * Extracts all the details of a user
     *
     * @param username The username as a String to search for.
     * @param password The password of the user.
     * @return all details in a Person object for the name
     */
    User GetUser(String username, String password);


    /**
     * Deletes a Person from the address book.
     *
     * @param username The name to delete from the address book.
     */
    void deleteUser(String username);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisted.
     */
    void close();

}
