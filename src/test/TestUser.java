package test;


import main.com.cab302qut.java.Organisation.Organisation;
import main.com.cab302qut.java.Users.User;
import main.com.cab302qut.java.Users.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUser {
    // Add user
    // Get user
    // Remove user
    // Get username -
    // Modify username -
    // Set password -
    // Hash password
    // Get hashed password
    // Get organisation
    // Set organisation
    // Remove organisation (should throw exception)
    // Get permission role / user type.
    // Set user type.
    // Verify permission to create new user
    // Verify permission to create new organisation
    // Verify permission to create new asset (not OrganisationAsset)
    // Verify permission to create new OrganisationAsset
    // Verify permission to modify other user (username, password, organisation, user type).
    // Verify permission to modify organisation (name, users and Organisation asset)
    //

    private User[] users;
    private String username = "johnSmith";
    private String name = "john";
    private String password = "Test";
    private User testUser;
    private User testUser1;
    private Organisation testOrganisation1;
    private UserType userType1;

    @BeforeEach
    void init() {
        testOrganisation1 = new Organisation("organisation1");
        userType1 = UserType.Administrator;
        testUser = new User(name, testOrganisation1, username, password, userType1);
        testUser1 = new User("John1", testOrganisation1, "JohnSmith1", "", userType1);
    }

    @Test
    //maybe change
    public void AddUser() {
        users[0] = testUser;
        assertEquals(users[0].getName(), "John");
        assertEquals(users[0].getOrganisation(), testOrganisation1);
        assertEquals(users[0].getUsername(), "JohnSmith");
        assertEquals(users[0].getPassword(), "Test");
        assertEquals(users[0].getUserType(), userType1);
    }

    @Test
    public void GetUser() {

    }

    @Test
    public void RemoveUser() {

    }

    @Test
    public void GetUsername(){
        assertEquals(testUser.getUsername(), "JohnSmith");
    }

    @Test
    public void ModifyUsername(){
        testUser.setUsername("newUsername");
        assertEquals(testUser.getUsername(), "newUsername");
    }

    @Test
    public void SetPassword(){

        assertEquals(testUser.getPassword(), "");
        testUser1.setPassword("newpassword");
        assertEquals(testUser.getPassword(), "newpassword");
    }

    @Test
    public void HashPassword(){

    }

    @Test
    public void GetHashedPassword(){

    }

    @Test
    public void GetOrganisation(){

    }
    @Test
    public void SetOrganisation(){

    }

    @Test
    public void RemoveOrganisation(){
//will throw exception as user must have organisation
    }

    @Test
    public void GetUserType(){
        assertEquals(testUser.getUserType(), UserType.Administrator);
    }

    @Test
    public void SetUserType(){

    }


}
