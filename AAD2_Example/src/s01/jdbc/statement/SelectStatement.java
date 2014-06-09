/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01.jdbc.statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class SelectStatement {

    public static void main(String[] args) {
        try (Statement stm = DBConnector.getSqlServerConnection().createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Classes");) {
            System.out.printf("| %-10s | %-15s |\n", "Class Name", "Location");
            System.out.printf("+-%-10s-+-%-15s-+\n", "----------", "---------------");
            //Duyet qua ResultSet va hien thi du lieu
            while (rs.next()) {
                System.out.printf("| %-10s | %-15s |\n", rs.getString("ClassName"), rs.getString("Location"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SelectStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
