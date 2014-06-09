/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.statement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class InsertStatement {
    public static void main(String[] args) {
        try (Statement stm = DBConnector.getSqlServerConnection().createStatement()) {
            int rt = stm.executeUpdate("INSERT INTO Classes(ClassName, Location) VALUES('GC0902', 'FU-HN')");
            if(rt>0){
                System.out.println("INSERT complete!");
            }else{
                System.out.println("Can't insert to table");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SelectStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
