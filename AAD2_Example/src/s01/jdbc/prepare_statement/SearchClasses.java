/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.prepare_statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class SearchClasses {
    public static void main(String[] args) {
        try(PreparedStatement pstm = 
                DBConnector.getSqlServerConnection().prepareStatement("SELECT * FROM Classes WHERE ClassName=?")){
            pstm.setString(1, "GC0909");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                System.out.println("Class Name: " + rs.getString("ClassName"));
                System.out.println("Location: " + rs.getString("Location"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
