<%-- 
    Document   : shopping
    Created on : Jun 20, 2022, 9:03:28 PM
    Author     : simnh
--%>

<%@page import="java.util.List"%>
<%@page import="nhanvd.registration.ProductDTO"%>
<%@page import="nhanvd.registration.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="productList" value="${sessionScope.LIST_BOOK}"/>
        <c:if test="${not empty productList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Name</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${productList}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.sku}
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                <form action="addBookController">
                                    <input type="hidden" name="cboBook" 
                                           value="${dto.name}" />
                                    <input type="submit" value="Add Book to Your Cart" 
                                           name="btAction" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        
        <%--
        <%
            ProductDAO dao = new ProductDAO();
            dao.showProductList();
            List<ProductDTO> productList = dao.getProductList();
            if (productList != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (ProductDTO dto : productList) {
                %>
                <tr>
                    <td><%= ++count %></td>
                    <td><%= dto.getSku() %></td>
                    <td><%= dto.getName() %></td>
                    <td><%= dto.getPrice() %></td>
                    <td>
                        <form action="DispatchController">
                            <input type="hidden" name="cboBook" 
                                   value="<%= dto.getName() %>" />
                            <input type="submit" value="Add Book to Your Cart" 
                                   name="btAction" />
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
            }
        %>--%>
        <a href="viewCartPage">View your Cart</a>
    </body>
</html>
