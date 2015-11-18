package main.Repo;


import main.entities.Product;
import main.utils.Database;
import main.Exceptions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdRepo {

    public static void add(Product product) throws SQLException, emptyNameException, invalidWeightPriceException {
        check(product);
        StringBuilder s = new StringBuilder("insert into products(name, price, weight, manufacturer, cat_id)")
                .append(" values(?,?,?,?,?);");
        PreparedStatement p = Database.getConnection().prepareStatement(s.toString());
        p.setString(1, product.getName());
        p.setDouble(2, product.getPrice());
        p.setDouble(3, product.getWeight());
        p.setString(4, product.getManufacturer().getCountry());
        p.setInt(5,product.getCategoryId());

        p.executeUpdate();
    }

    public static ArrayList<Product> getAll() throws SQLException{
        Connection conn = Database.getConnection();
        StringBuilder s = new StringBuilder("select * from products;");
        PreparedStatement p = conn.prepareStatement(s.toString());
        ResultSet set = p.executeQuery();
        ArrayList<Product> products = new ArrayList<>();

        while (set.next()){
            int id      = set.getInt(1);
            String name     = set.getString(2);
            double price  = set.getDouble(3);
            double weight = set.getDouble(4);
            Product.Manufacturer manufacturer  = Product.getManufacturer(set.getString(5));
            int cat_id= set.getInt(6);
            products.add(new Product(id,name,price,weight,manufacturer,cat_id));
        }
        return products;
    }

    public static String[][] getTable(){
        try {
            List<Product> res = getAll();
            String [][] data = new String[res.size()][5];
            int i = 0;
            for (Product product : res){
                data[i][0] = product.getName();
                data[i][1] = String.valueOf(product.getPrice());
                data[i][2] = String.valueOf(product.getWeight());
                data[i][3] = product.getManufacturer().getCountry();
                data[i][4] = CategoryRepo.getNameById(product.getCategoryId());
                i++;
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[][]{};
    }

    public static void check(Product product) throws emptyNameException,invalidWeightPriceException {

        if (product.getName() == null || "".equals(product.getName())){
            throw new emptyNameException("Product name must not be empty");
        }
        if (product.getPrice() <= 0 || product.getWeight() <= 0){
            throw new invalidWeightPriceException("Product weight and price must not be less than zero or empty");
        }
    }
}
