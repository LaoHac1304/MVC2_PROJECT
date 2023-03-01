<%-- 
    Document   : viewCart
    Created on : Jun 15, 2022, 2:31:29 PM
    Author     : simnh
--%>

<%@page import="nhanvd.registration.ProductDTO"%>
<%@page import="nhanvd.registration.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="nhanvd.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart includes</h1>
        <c:set var="cart" value="${sessionScope.CART}" ></c:set>
        <c:if test="${not empty cart.items}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="removeCartController" > 

                        <c:forEach var="entry" items="${cart.items}" varStatus="counter" >
                            <jsp:useBean id="productDAO" class="nhanvd.registration.ProductDAO" 
                                />
                            <c:set var="productName" value="${entry.key}" ></c:set>
                            <c:set var="quantity" value="${entry.value}" ></c:set>
                            <c:set var="product" value="${productDAO.getProductByName(productName)}"></c:set>
                            <tr>
                                <td>${counter.count}</td>
                                <td>${entry.key}</td>
                                <td>${entry.value}</td>
                                <td>${product.price}</td>
                                <td>${product.price*quantity}</td>
                                <td>
                                    <input type="checkbox" name="chkItem" 
                                           value="${entry.key}" />
                                </td>
                            </tr>
                        </c:forEach>
                            <tr>
                                <td colspan="3" >
                                    <a href="shoppingPage">Add More Books to Your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" 
                                           value="Remove Selected Books" name="btAction" />
                                </td>
                            </tr>
                </form>
                </tbody>
            </table>
            <form action="checkoutController">
                <input type="submit" value="Check out" name="btAction" />
            </form>

        </c:if>
        <c:if test="${empty cart.items}">
            <h2>No cart exists</h2>
        </c:if>
        <%--
        <% 
            //1. Cust go to cart place
            if (session != null){
                //2. cus take cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null){
                    //3. check item have existed
                    Map<String, Integer> items = cart.getItems();
                    if (items != null){
                        //4. show items
                        %> 
                        <form action="DispatchController">
                        
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Title</th>
                                        <th>Quantity</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 0;
                                        List<Double> total_price = new ArrayList<>();
                                        for (String key : items.keySet()){
                                            ProductDAO dao = new ProductDAO();
                                            ProductDTO dto = null;
                                            dto = dao.getProductByName(key);
                                            if (dto != null){
                                                double total = cart.getQuantityByBookName(key)
                                                *dto.getPrice();

                                            %> 
                                            <tr>
                                                <td>
                                                    <%= ++count %>
                                                </td>
                                                <td>
                                                    <%= key %>
                                                </td>
                                                <td>
                                                    <%= items.get(key) %>
                                                </td>
                                                <td>
                                                    <%= total %>$
                                                    <%
                                                        total_price.add(total);
                                                    %>
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="chkItem" 
                                                           value="<%= key %>" />
                                                    <input type="hidden" name="checkoutItem" value="<%= key %>" />
                                                </td>
                                            </tr>
                                            <%
                                            }
                                        }//end for
                                        int sum = 0;
                                        for (Double price : total_price) {
                                            sum += price;
                                    }
                                    %>
                                    <tr>
                                        <td colspan="3">
                                            <a href="shopping.jsp">Add More Books to Your Cart.</a>
                                        </td>
                                        <td colspan="2">
                                            Total: <%= sum %>$
                                        </td>
                                        <td>
                                            <input type="submit" 
                                                   value="Remove Selected Books" name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="submit" value="Check out" name="btAction" />
                        </form>       

        <%
            return;
                    }
                }//cart has existed
            }//end session has existed
        %>
        <h2>
            No cart is existed!!!!
        </h2>--%>
    </body>
</html>
