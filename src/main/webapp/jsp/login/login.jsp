<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/LoginController"
              style="
                    margin-left: 20px;
                    font-size: 20px;
                    ">
            <h1 style="color: red;">Cafe Management System</h1>
            Username: <input type="text" name="userName" style="font-size: 15px; margin-bottom: 5px; border: 2px solid black; padding: 4px 4px 4px 4px"/>
            <br/>
            Password: &nbsp<input type="password" name="passWord" style="font-size: 15px; margin-bottom: 5px; border: 2px solid black; padding: 4px 4px 4px 4px"/>
            <br/>
            <input type="submit" name="login_btn"  value="login" style="font-size: 15px; margin-left: 100px; border: none; color: white; background-color: gray"/>
            <input type="submit" name="clear_btn" value="clear" style="font-size: 15px; border: none; color: white; background-color: gray"/>
            
        </form>
    </body>
</html>
