package main.utils;


import java.sql.*;


public final class Database {

    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String CONNECTION_URI = "jdbc:mysql://localhost:3306/";
    private final static String DB_NAME = "product_db";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "mysqlpass";

    private static Connection conn;


    public static Connection getConnection(){
        if(conn == null){
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(CONNECTION_URI + DB_NAME, LOGIN, PASSWORD);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return conn;
    }

}
