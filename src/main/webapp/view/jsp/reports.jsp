<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/view/css/reportsCss.css" />" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="pageHeader.jsp"/>
        <a id="back_btn" href="${pageContext.request.contextPath}/HomeController"> Back <--</a>
        <a id="title_btn" href="${pageContext.request.contextPath}/ReportController"> Cafe Reports </a>
        <div id="wrapper">    
            <div id="leftColumn" class="columns">
                <div id="customerColumn">
                    <p id="reportTitles_lbl">Report Titles</p>
                    <hr>
                    <table id="reportLabels_tbl">
                        <tbody>
                            <c:forEach items="${reportsProp}" var="_reportsProp">
                                <tr class="rows">
                                    <td id="<c:out value="${_reportsProp.reportId}"/>"><c:out value="${_reportsProp.reportLabel}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="upperColumn" class="columns">
                <div class="nav">
                    <ul id="timeline_ul">
                        <li id="timeLineMain">Timeline(Unused)
                            <ul>
                                <li>Timeline(ALL)</li>
                                <li>Timeline(Unpaid)</li>
                                <li>Timeline(Paid)</li>
                            </ul>
                        </li>   
                        <li>Transaction</li>
                        <li>Reports</li>
                    </ul>
                </div>
            </div>
            <div id="rightColumn" class="columns">
                <form action="${pageContext.request.contextPath}/ReportController" id="generate_frm">
                    <div id="elements">
                        <c:out value="${reportElements}" escapeXml="false"/>
                    </div>
                    <br> <br>
                    <button type="submit" id="generateReport_btn">Generate</button>
                </form>
                
            </div>
        </div>
    </body>
    <script src="<c:url value="/view/js/jquery321.js"/>"></script>
    <script src="<c:url value="/view/js/report_js.js"/>"></script>
</html>
