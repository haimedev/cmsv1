<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/view/css/logincss.css" />" rel="stylesheet">
        <title>Cafe Management System</title>
    </head>
    <body>
        
        <div id="loginDiv">
            <h1 id="companyName">Cafe Management System</h1>
            <form action="${pageContext.request.contextPath}/LoginController">
                <table id="loginTable">
                    <tr>
                        <th>
                            Username
                        </th>
                        <th>
                            Password
                        </th>
                        <th>
                            
                        </th>
                    </tr>
                    <tr>
                        <td><input class="loginTxt" type="text" name="userName" required/></td>
                        <td><input class="loginTxt" type="password" name="passWord" required/></td>
                    </tr>
                    <tr>
                        <td><input class="loginBtn" type="submit" name="login_btn"  value="login"/></td>
                        <td><input class="loginBtn" type="reset" name="clear_btn" value="clear"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <c:choose>
            <c:when test="${isUserValid == 'false'}">
                <script>
                    alert("invalid credentials");
                </script>
            </c:when>
            <c:when test="${condition2}">
              ...
            </c:when>
            <c:otherwise>
              ...
            </c:otherwise>
        </c:choose>
    </body>
</html>
