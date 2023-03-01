/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhanvd.registration.RegistrationDAO;
import nhanvd.registration.RegistrationDTO;
import nhanvd.registration.ResgistrationCreateError;
import nhanvd.utils.MyApplicationConstants;

/**
 *
 * @author simnh
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
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
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        
        ResgistrationCreateError errors = new ResgistrationCreateError();
        boolean foundErr = false;
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.CreateAccountFeatures.CREATE_ACCOUNT_RESULT_PAGE);
        
        
        try  {
            //1. check all user errors 
            if (username.trim().length() < 6 || 
                    username.trim().length() > 20){
                foundErr = true;
                errors.setUsernameLengthErr("Username is required with 6 - 20 chars");
            }
            
            if (password.trim().length() < 6 || 
                    password.trim().length() > 30){
                foundErr = true;
                errors.setPasswordLengthErr("Password is required with 6 - 30 chars");
            } else if (!confirmPassword.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMatched("Comfirm must be match password");
            }
            
            if (fullname.trim().length() < 2 || 
                    fullname.trim().length() > 50){
                foundErr = true;
                errors.setFullNameLengthErr("Full name is required with 2 - 50 chars");
            }
            
            if (foundErr){
                //store err to request, fw to ERRORS PAGE 
                request.setAttribute("CREATE_ERR", errors);
                //co setAttribute nen phai dung RequestDispatcher
                
            }else{
                //insert to DB -- callDAO
                RegistrationDTO dto  = 
                        new RegistrationDTO(username, password, fullname, false);
                //
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(dto);
                
                if (result){
                    //. transfer to login page
                    url = siteMaps.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_PAGE);
                }//end account is created
            }
        } catch(SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL " + ex.getMessage());
            if (errMsg.contains("duplicate")){
                errors.setUsernameIsExisted(username + "is existed");
                request.setAttribute("CREATE_ERR", errors);
            }
        } catch(NamingException ex) {
            //save log
            log("CreateAccountServlet _ Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
