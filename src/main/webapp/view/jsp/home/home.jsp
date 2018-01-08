<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/view/css/homecss.css"/>" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../pageHeader.jsp"/>
        <table id="moduleTbl">
            <tr>
                <th class="moduleImg"><a href="${pageContext.request.contextPath}/TimelineController"> <img src="<c:url value="/view/images/time.jpg"/>"> </a> </th>
                <th class="moduleImg"><a href=""><img src="<c:url value="/view/images/balance.jpg"/>"> </a> </th>
            </tr>
        </table>
    </body>
</html>
