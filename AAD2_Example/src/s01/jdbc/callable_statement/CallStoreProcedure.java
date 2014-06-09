/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s01.jdbc.callable_statement;

import java.sql.CallableStatement;
import java.sql.SQLException;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class CallStoreProcedure {

    public static void main(String[] args) {
        try (CallableStatement cstm = 
                DBConnector.getSqlServerConnection().prepareCall("{call spInsertClasses(?, ?)}")) {
            cstm.setString(1, "GC0906");
            cstm.setString(2, "FU-HN");
            if (cstm.executeUpdate() > 0) {
                System.out.println("Call Store Procedure complete!");
            }
        } catch (SQLException ex) {
        }
    }
}
