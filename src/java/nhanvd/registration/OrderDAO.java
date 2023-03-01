/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhanvd.utils.DBHelpers;

/**
 *
 * @author simnh
 */
public class OrderDAO implements Serializable{
    public boolean addOrder(double total)throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Insert Into [order](date, total) "
                        + "Values(CAST(GETDATE() as date), ?)";
                // o day co dau "?" nen chung ta phai thiet lap
                // tham so dua vao trong cau lenh bang ham set
               
                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setDouble(1, total);
                
               
                //4. Thuc hien SQL Query
                int row = stm.executeUpdate();
                // cau lenh sql la Select nen
                // phai dung executeQuery , neu cau lenh la insert, delete,
                //, update thi phai dung executeUpdate

                //5. Process
                if (row > 0) {
                    result = true;
                }

            }// end if connection is existed

        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public int maxRowInOrder()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int result = 0;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "SELECT id "
                        + "FROM [order]";
                // o day co dau "?" nen chung ta phai thiet lap
                // tham so dua vao trong cau lenh bang ham set
               
                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                
                //4. Thuc hien SQL Query
                int row = stm.executeUpdate();
                // cau lenh sql la Select nen
                // phai dung executeQuery , neu cau lenh la insert, delete,
                //, update thi phai dung executeUpdate

                //5. Process
                if (row > 0) {
                    result = row;
                }

            }// end if connection is existed

        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    } 
}
