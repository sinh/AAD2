/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package s01.jdbc.type1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SinhNX
 */
public class JDBC_ODBC_Bridge {
    public static void main(String[] args) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            try (Connection cn = DriverManager.getConnection("jdbc:odbc:type1", "sa", "fat123456@")) {
                System.out.println("Connect to ODBC complete");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(JDBC_ODBC_Bridge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
