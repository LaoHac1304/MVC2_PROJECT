/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.cotroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhanvd.cart.CartObject;
import nhanvd.registration.OrderDAO;
import nhanvd.registration.OrderDetailDAO;
import nhanvd.registration.ProductDAO;
import nhanvd.registration.ProductDTO;

/**
 *
 * @author simnh
 */
@WebServlet(name = "CheckOutFromCartServlet", urlPatterns = {"/CheckOutFromCartServlet"})
public class CheckOutFromCartServlet extends HttpServlet {

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
        double toltalPrice = 0;
        try  {
            
            //1. customer go to Cartplace (checkfalse)
            HttpSession session = request.getSession(false);
            //2. customer takes his cart (check exist)
            CartObject cart = (CartObject)session.getAttribute("CART");
            if (cart!=null){
                //3. check item existed
                Map<String,Integer> items = cart.getItems();
                if (items!=null){
                    ProductDAO productDAO = new ProductDAO();
                    for (String item : items.keySet()) {
                        ProductDTO productDTO = new ProductDTO();
                        productDTO = productDAO.getProductByName(item);
                        toltalPrice += (cart.getQuantityByBookName(item))
                                        *productDTO.getPrice();
                    }
                }
                //session = request.getSession();
              
                //session.setAttribute("TOTAL", toltalPrice);
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.addOrder(toltalPrice);
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                int orderID = orderDAO.maxRowInOrder();
                
                    ProductDAO productDAO = new ProductDAO();
                    for (String item : items.keySet()) {
                        ProductDTO productDTO = new ProductDTO();
                        productDTO = productDAO.getProductByName(item);
                        orderDetailDAO.addOrderDetail(productDTO.getSku(), orderID, 
                                productDTO.getQuantity(), productDTO.getPrice(), 
                                productDTO.getQuantity()*productDTO.getPrice());
                    
                    }
                session.removeAttribute("CART");
            }
        } catch(SQLException ex){
            log("CheckoutServlet _ SQL _ " + ex.getMessage());
        } catch(NamingException ex){
            log("CheckoutServlet _ Naming _ " + ex.getMessage());
        } finally{
            String urlRewriting = "ShowBookServlet";
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
