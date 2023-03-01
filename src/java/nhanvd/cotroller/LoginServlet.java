/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhanvd.registration.RegistrationDAO;
import nhanvd.registration.RegistrationDTO;
import nhanvd.utils.DBHelpers;
import nhanvd.utils.MyApplicationConstants;

/**
 *
 * @author simnh
 */
public class LoginServlet extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //get 03 parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.LoginFeatures.INVALID_PAGE); // set default trang la invalid

        try {

            //1. call DAO -> new DAO object va call method cua DAO
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);

            if (result!=null) {
                url = siteMaps.getProperty(MyApplicationConstants.LoginFeatures.SEARCH_PAGE);
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", result);
                //get full name from username thong qua DAO
                //session.setAttribute("FULLNAME", fullname);
                
                /*Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60*3);
                response.addCookie(cookie);*/
            }

        } catch (SQLException ex) {
            log("LoginServlet _ SQL _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet _ Naming _ " + ex.getMessage());
        } finally {
            response.sendRedirect(url); // container -> server -> browser (send response)
            //RequestDispatcher rd = request.getRequestDispatcher(url);
            //rd.forward(request, response);
               
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
