package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {


    }

    public void createUsersTable() {
        String sql = "create table if not exists aisalbek(id serial, name varchar(30),lastName varchar(32),age int)";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "drop table if exists aisalbek";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "insert into aisalbek (name,lastName,age) values (?,?,?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "delete from aisalbek where id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(remove)) {
            ResultSet resultSet = statement.executeQuery(remove);
            User user = new User();
            resultSet.getLong(1);
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String getAll = "select * from aisalbek";
        ArrayList<User> arrayList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(getAll);
            while (res.next()) {
                User user = new User();
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));
                arrayList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void cleanUsersTable() {
        String clertable = "truncate aisalbek";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
             statement.executeUpdate(clertable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}