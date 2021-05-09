package test;

import com.cab302qut.java.Users.User;
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

    private User[] users = new User[3];
    private String username = "johnSmith";
    private String name = "john";
    private String password = "Test";
    private User testUser;
    private User testUser1;
    private Organisation testOrganisation1;
    private Organisation testOrganisation2;
    private UserType userType1;

    @BeforeEach
    void init() {
        testOrganisation1 = new Organisation("organisation1");
        testOrganisation2 = new Organisation("organisation2");
        userType1 = UserType.Administrator;
        testUser = new User(name, testOrganisation1, username, password, userType1);
        testUser1 = new User("John1", testOrganisation1, "JohnSmith1", "", userType1);
    }

    @Test
    //maybe change
    public void AddUser() {
        users[0] = testUser;
        assertEquals(users[0].getName(), "john");
        assertEquals(users[0].getOrganisation(), testOrganisation1);
        assertEquals(users[0].getUsername(), "johnSmith");
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
    public void GetUsername() {
        assertEquals(testUser.getUsername(), "johnSmith");
    }

    @Test
    public void ModifyUsername() {
        testUser.setUsername("newUsername");
        assertEquals(testUser.getUsername(), "newUsername");
    }

    @Test
    public void SetPassword() {

        //assertEquals(testUser.getPassword(), "");
        testUser1.setPassword("newpassword");
        assertEquals(testUser1.getPassword(), "5e9d11a14ad1c8dd77e98ef9b53fd1ba");
    }

    //I think this test is unneeded as the password is hashed when set password is called.
    @Test
    public void HashPassword() {

    }

    //I think this test is also unneeded.
    @Test
    public void GetHashedPassword() {

    }

    @Test
    public void GetOrganisation() {
        testUser1.getOrganisation();
        assertEquals(testUser1.getOrganisation(), testOrganisation1);
    }

    @Test
    public void SetOrganisation() {
        //testUser.setOrganisation(testOrganisation1);
        testUser1.setOrganisation(testOrganisation2);
        assertEquals(testUser1.getOrganisation(), testOrganisation2);
    }

    @Test
    public void RemoveOrganisation() {
//will throw exception as user must have organisation
    }

    @Test
    public void GetUserType() {
        assertEquals(testUser.getUserType(), UserType.Administrator);
    }

    @Test
    public void SetUserType() {

    }


}
