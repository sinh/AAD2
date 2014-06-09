/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class DBConnector {
    private static Connection con;
    
    public static Connection getMySQLConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LabDB", "root", "fat123456");
        } catch (ClassNotFoundException | SQLException ex) {
            con = null;
        }
        return con;
    }
    
    public static Connection getSqlServerConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=LabDB", "sinhnx", "fat123456");
        } catch (ClassNotFoundException | SQLException ex) {
            con = null;
        }
        return con;
    }
}
