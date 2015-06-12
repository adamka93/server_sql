package com.jundev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.Person.model.*;
import com.jundev.util.*;

public class PersonDao {
	
    private Connection connection;

    public PersonDao() {
        connection = DbUtil.getConnection();
    }
    
    public void addUser(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name,password,email,address,groupID) values (?, ?, ?, ?, ? )");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getPass());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.setString(5, person.getGroupID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from user where ID=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    public void updateUser(Person person) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update user set name=?, password=?, email=?, address=?, groupID=?" +
                            "where userid=?");
            // Parameters start with 1
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getPass());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getAddress());
            preparedStatement.setString(5, person.getGroupID());
            preparedStatement.setInt(6, person.getUserID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<Person>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                Person user = new Person();
                user.setUserID(rs.getInt("ID"));
                user.setName(rs.getString("name"));
                user.setPass(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGroupID(rs.getString("groupID"));
                persons.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }
//szunet
    public Person getUserById(int userId) {
        Person user = new Person();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from user where ID=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserID(rs.getInt("ID"));
                user.setName(rs.getString("name"));
                user.setPass(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setGroupID(rs.getString("groupID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}

