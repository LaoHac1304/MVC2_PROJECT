```<%-- 
    Document   : search
    Created on : Jun 6, 2022, 12:29:20 PM
    Author     : simnh
--%>

<%--<%@page import="nhanvd.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome,${sessionScope.USERNAME.lastname}
        </font>
        <h1>Search Page</h1>
        <form action="searchLastnameController">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /><br/>
            <input type="submit" value="Search" name="btAction" /><br/>
        </form><br/>
        
        <form action="logoutController">
            <input type="submit" value="Logout" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>

        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccountController">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername"
                                           value="${dto.username}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                   
                                    <input type="checkbox" name="chkRole" value="ON"
                                           <c:if test="${dto.role}">
                                               checked = "checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="deleteAccountController">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!!
            </h2>
        </c:if>
    </c:if>

    <%--<%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String username = "";
            for (Cookie cookie : cookies) {
                username = cookie.getName();
                if (!username.equals("JSESSIONID")) {
                    break;
                }
            }

        %>
        <font color="red">
        Welcome, <%= username%>
        </font>
        <%
            }
        %>
        <h1>Search Page</h1>
        <form action="DispatchController" method="GET">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%=request.getParameter("txtSearchValue")%>" /><br/>
            <input type="submit" value="Search" name="btAction" /><br/>
            <input type="submit" value="Logout" name="btAction" />
        </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                // luu y khi dung getAttribute phai ep kieu tuong minh
                // vi no thuoc kieu Object
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchController"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchController" method="POST">

                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername"
                               value="<%= dto.getUsername()%>"
                    </td>

                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkRole" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked = "checked"
                               <%
                                                    }//end admin role is true
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>   
            <%
                }// end traverse search result
            %>
        </tbody>
    </table>

    <%
    } else {// no result
    %>
    <h2>
        No record is matched!!!
    </h2>
    <%
            }
        }//end if search Value has valid
%>--%>
</body>
</html>
