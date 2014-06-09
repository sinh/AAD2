/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.type4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class ConnectSqlServer {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection cnn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=LabDB", "sinhnx", "fat123456");
            System.out.println("Connect to SQL Server complete!");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectSqlServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}