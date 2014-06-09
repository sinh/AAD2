/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.meta_data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class DatabaseMetaDataDemo {
    public static void main(String[] args) {
        try (Connection con = DBConnector.getMySQLConnection()){
            DatabaseMetaData dbmd = con.getMetaData();
            //Hiển thị thông tin của CSDL mà ta đã kết nối đến
            System.out.println("Database Information:");
            System.out.println("\tDatabase Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("\tDatabase Product Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("Driver Information:");
            System.out.println("\tDriver Name: " + dbmd.getDriverName());
            System.out.println("\tDriver Version: " + dbmd.getDriverVersion());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMetaDataDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
