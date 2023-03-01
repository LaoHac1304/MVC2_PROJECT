/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.cotroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhanvd.registration.RegistrationDAO;
import nhanvd.utils.MyApplicationConstants;

/**
 *
 * @author simnh
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

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
        String username = request.getParameter("txtUsername");
        String newPassword = request.getParameter("txtPassword");
        String admin = request.getParameter("chkRole");
        boolean newRole = false;
        if (admin != null){
            newRole = true;
        }
        String searchValue = request.getParameter("lastSearchValue");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        String urlRewriting = siteMaps.getProperty(MyApplicationConstants.UpdateFeatures.ERROR_PAGE);
        
        try  {
            //1. call DAO
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateAccount(username, newPassword, newRole);
            if (result){
                // refresh page by call previous function (Search)
                urlRewriting = "searchLastnameController?"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch(NamingException ex){
            ex.printStackTrace();
        } finally {
            response.sendRedirect(urlRewriting);
            // ko duoc dung RequestDispatcher
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
