package com.cab302qut.java.Server;

import com.cab302qut.java.Users.User;

import java.util.List;

public class DatabaseData {

    DatabaseSource databaseSource;

    List<User> userList;

    public void add(User u){
        if (!userList.contains(u.getUsername())){
            userList.add(u);
            databaseSource.addPerson(u);
        }
    }

    public User get(String username, String password) {
        return databaseSource.GetUser(username, password);
    }

}
