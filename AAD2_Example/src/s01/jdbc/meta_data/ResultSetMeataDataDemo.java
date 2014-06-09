/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package s01.jdbc.meta_data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import s01.jdbc.DBConnector;

/**
 *
 * @author SinhNX <sinhnx@fpt.edu.vn>
 */
public class ResultSetMeataDataDemo {
    public static void main(String[] args) {
        try(Connection con = DBConnector.getMySQLConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Classes")){
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i=1; i<=rsmd.getColumnCount(); i++){
                System.out.println(rsmd.getColumnName(i) + " - " + rsmd.getColumnTypeName(i));   
            }
        }catch(SQLException ex){
            
        }
    }
}
