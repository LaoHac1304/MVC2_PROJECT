/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhanvd.utils.DBHelpers;

/**
 *
 * @author simnh
 */
public class ProductDAO implements Serializable {

    public ProductDAO() {
    }

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void showProductList()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2 tao cau lenh SQL
                String sql = "Select id, name, quantity, price "
                        + "From product";
                //3 tao statement to set SQL
                stm = con.prepareStatement(sql);
                //4 Thuc hien sql query
                rs = stm.executeQuery();
                //5 Process
                while (rs.next()) {
                    String sku = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getFloat("price");
                    price = (double) Math.round(price * 1000) / 1000;
                    ProductDTO dto = new ProductDTO(sku, name, quantity, price);
                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public ProductDTO getProductByName(String productName)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO product = null;

        try {
            //1.Connect to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create cau lenh SQL
                String sql = "Select id, name, quantity, price "
                        + "From product "
                        + "Where name = ?";
                //3. Tao statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, productName);
                //4. Thuc hien SQL query
                rs = stm.executeQuery();
                //5 Process
                while (rs.next()) {
                    String sku = rs.getString("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getFloat("price");
                    price = (double) Math.round(price * 1000) / 1000;
                    product = new ProductDTO(sku, name, quantity, price);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return product;
    }
}
