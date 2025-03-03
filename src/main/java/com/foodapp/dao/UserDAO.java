package com.foodapp.dao;

import java.util.ArrayList;

import com.foodapp.model.User;

public interface UserDAO {

    int Insert(User u);  // Change return type to int
    ArrayList<User> fetchAll();
    User fetchSpecific(int id);
    int update(User u);
    int delete(int id);
    User fetchOnEmail(String Email);
}