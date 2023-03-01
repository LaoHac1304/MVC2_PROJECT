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
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO dto = null;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Select username, lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                // o day co dau "?" nen chung ta phai thiet lap
                // tham so dua vao trong cau lenh bang ham set

                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username); // thiet lap username vao dau "?"
                stm.setString(2, password);
                //4. Thuc hien SQL Query
                rs = stm.executeQuery(); // boi vi cau lenh sql la Select nen
                // phai dung executeQuery , neu cau lenh la insert, delete,
                //, update thi phai dung executeUpdate

                //5. Process
                if (rs.next()) {
                    String realUsername = rs.getString("username");
                    String realPassword = null;
                    String realLastname = rs.getString("lastname");
                    boolean realRole = rs.getBoolean("isAdmin");
                    dto = new RegistrationDTO(realUsername, realPassword, 
                            realLastname, realRole);
                }
            }// end if connection is existed

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
        return dto;
    }
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Thuc hien SQL Query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get field / collumn
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //Create DTO instance
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password,
                                    lastname, role);
                    // add to accounts list
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    // account is avalible
                    this.accounts.add(dto);
                }//end rs has more than one record
            }// end if connection is existed

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

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Delete From Registration "
                        + "Where username = ?";
                // o day co dau "?" nen chung ta phai thiet lap
                // tham so dua vao trong cau lenh bang ham set

                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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

    public boolean updateAccount(String username, String newPassword, boolean newRole)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connection 
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //3. Tao statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, newPassword);
                stm.setBoolean(2, newRole);
                stm.setString(3, username);
                //4. Thuc hien SQL Query
                int row = stm.executeUpdate();
                // cau lenh sql la Select nen
                // phai dung executeQuery , neu cau lenh la insert, delete,
                //, update thi phai dung executeUpdate

                //5. Process
                if (row > 0) {
                    result = true;
                }
            }//end if connection
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
    
    public  boolean createAccount(RegistrationDTO dto)
        throws SQLException, NamingException {
        if (dto==null){
            return false;
        }//end dto is not existed
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Connection to DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Tao cau lenh SQL
                String sql = "Insert Into Registration(username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                // o day co dau "?" nen chung ta phai thiet lap
                // tham so dua vao trong cau lenh bang ham set
               
                //3. Tao Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername()); // thiet lap username vao dau "?"
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastname());
                stm.setBoolean(4, dto.isRole());
                
               
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
