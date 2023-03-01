/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhanvd.utils.DBHelpers;

/**
 *
 * @author simnh
 */
public class OrderDetailDAO implements Serializable{
    
    public boolean addOrderDetail(String productID, int orderID, int quantity, double price, double total)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Insert Into [orderDetail](id, orderID, quantity, price, total) "
                        + "Values(?,?,?,?,?)";
                // o day co dau "?" nen chung ta phai thiet lap
                // tham so dua vao trong cau lenh bang ham set
               
                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setInt(2, orderID);
                stm.setInt(3, quantity);
                stm.setDouble(4, price);
                stm.setDouble(5, total);
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
    
    
    
}
