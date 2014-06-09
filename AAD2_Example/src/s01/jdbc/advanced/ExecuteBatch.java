/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.advanced;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class ExecuteBatch {
    public static void main(String[] args) {
        
        Connection con = DBConnector.getSqlServerConnection();
        try {
            con.setAutoCommit(false);
            try (Statement stm = con.createStatement()) {
                
                stm.addBatch("INSERT INTO Classes VALUES('T1212L', 'FAT3');");
                stm.addBatch("INSERT INTO Classes VALUES('T1211L', 'FAT3');");
                stm.addBatch("INSERT INTO Classes VALUES('T1210L', 'FAT3');");
                stm.executeBatch();
            }
            
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ExecuteBatch.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteBatch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
