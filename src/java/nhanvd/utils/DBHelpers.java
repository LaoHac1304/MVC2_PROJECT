/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author simnh
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection()
        throws /*ClassNotFoundException, SQLException*/ NamingException, SQLException{
        
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource datasource = (DataSource)tomcatContext.lookup("DS007");
        Connection connection = datasource.getConnection();
        
        return connection;
        //1 load driver
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create Connection String
        //String url = "jdbc:sqlserver://localhost:1433;databaseName=registration;instanceName=LAOHAC";
        //3. Open Connection
        //Connection con = DriverManager.getConnection(url, "sa", "13042002");
        
        //return con;
    }
    
    public static void getSiteMaps(ServletContext context) 
            throws IOException{
        String siteMapFile = context.getInitParameter("SITEMAPS_PATH");
        
        InputStream is = null;
        Properties properties = new Properties();
        try{
            is = context.getResourceAsStream(siteMapFile);
            properties.load(is);
            context.setAttribute("SITEMAPS", properties);
        } finally {
            if (is != null){
                is.close();
            }
        }
    }
}
