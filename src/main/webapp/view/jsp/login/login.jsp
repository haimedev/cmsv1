<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="all" href="../../css/logincss.css">
        <title>JSP Page</title>
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
                        <td><input class="loginTxt" type="text" name="userName"/></td>
                        <td><input class="loginTxt" type="password" name="passWord"/></td>
                    </tr>
                    <tr>
                        <td><input class="loginBtn" type="submit" name="login_btn"  value="login"/></td>
                        <td><input class="loginBtn" type="submit" name="clear_btn" value="clear"/></td>
                    </tr>
                </table>
            </form>
        </div>
        
    </body>
</html>
