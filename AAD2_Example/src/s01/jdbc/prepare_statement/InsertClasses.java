/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.prepare_statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import s01.jdbc.DBConnector;
import s01.jdbc.statement.SelectStatement;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class InsertClasses {
    public static void main(String[] args) {
        try (PreparedStatement pstm = DBConnector.getSqlServerConnection().prepareStatement("INSERT INTO Classes(ClassName, Location) VALUES(?, ?)")) {
            pstm.setString(1, "GC0903");
            pstm.setString(2, "FU-HN");
            if(pstm.executeUpdate()>0){
                System.out.println("Insert complete!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SelectStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
