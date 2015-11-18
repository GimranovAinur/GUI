package main.Repo;


import main.entities.Category;
import main.utils.Database;
import main.Exceptions.emptyNameException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepo {
    public static void add(Category category) throws SQLException, emptyNameException {
        check(category);
        String s = "insert into categories(name) values(?);";
        PreparedStatement p = Database.getConnection().prepareStatement(s);

        p.setString(1,category.getName());
        p.executeUpdate();
    }


    public static ArrayList<Category> getAll() throws SQLException {
        Connection conn = Database.getConnection();
        String s = "select * from categories;";
        PreparedStatement p = conn.prepareStatement(s);
        ResultSet set = p.executeQuery();
        ArrayList<Category> categories = new ArrayList<>();
        while (set.next()){
            categories.add(new Category(set.getInt(1),set.getString(2)));
        }

        return categories;
    }

    public static String[] getAllCategoriesNames(){
        List<Category> info = null;
        try {
            info = getAll();
        } catch (SQLException e) {
            info = new ArrayList<>();
            e.printStackTrace();
        }
        String [] res = new String[info.size()+1];
        int i = 0;
        for (Category c : info){
            res[i] = c.getName();
            i++;
        }
        res[i] = "Other";
        return res;
    }


    public static Integer getIdByName(String name) throws SQLException {
        String s = "select id from categories where name = ?;";
        PreparedStatement p = Database.getConnection().prepareStatement(s);
        p.setNString(1,name);
        ResultSet set = p.executeQuery();
        if (set.next()){
            return set.getObject(1) == null ? null : (Integer) set.getObject(1);
        }

        return null;
    }

    public static String getNameById(int id) throws SQLException {
        String s = "select name from categories where id = ?;";
        PreparedStatement p = Database.getConnection().prepareStatement(s);
        p.setInt(1,id);
        ResultSet set = p.executeQuery();
        if (set.next()){
            return set.getString(1);
        }
        return "none";
    }

    private static void check(Category category) throws emptyNameException {

        if ( category.getName() == null || ("").equals(category.getName()) ){
            throw new emptyNameException("Category must have not empty name");
        }
    }
}


