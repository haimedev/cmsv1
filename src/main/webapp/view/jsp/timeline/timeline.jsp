<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/view/css/timelinecss.css" />" rel="stylesheet">
        <link href="<c:url value="/view/css/TimelineDesignCss.css" />" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../pageHeader.jsp"/>
        <a id="back_btn" href="${pageContext.request.contextPath}/HomeController"> Back <--</a>
        <a id="title_btn" href="${pageContext.request.contextPath}/TimelineController"> Account Balance Management </a>
        <div id="wrapper">
            <div id="leftColumn" class="columns">
                <div id="customerColumn">
                    <form action="TimelineController" name="addForm">   
                        <table id="rowClick">
                            <tr>
                                <td>
                                    Name:
                                </td>
                                <td>
                                    <input class="inputsText_cls" type="textbox" name="custName_txt" required style="width: 100%;">
                                </td>
                                <td>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Time:
                                </td>
                                <td>
                                    <input class="inputsText_cls" type="number" name="timeHour_txt"  onblur="onBlur(this)" required style="width: 30%;"><p style="font-size: 15px; display:inline;">hour</p>
                                    <input class="inputsText_cls" type="number" name="timeMinute_txt"  onblur="onBlur(this)" required style="width: 30%;"><p style="font-size: 15px; display:inline;">min</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    
                                </td>
                                <td>
                                    <input class="customerBtn_cls" type="submit" name="addBalance_btn" value="add" onclick="return validation()">
                                    <input class="customerBtn_cls" type="reset" name="clearBalance_btn" value="clear">
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" name="balance20Mins_btn" class="amountButton" value="20 min" onClick="setTime(this)">
                                    <input type="button" name="balance30Mins_btn" class="amountButton" value="30 min" onClick="setTime(this)">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" name="balance40Mins_btn" class="amountButton" value="40 min" onClick="setTime(this)">
                                    <input type="button" name="balance60Mins_btn" class="amountButton" value="60 min" onClick="setTime(this)">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" name="balance80Mins_btn" class="amountButton" value="80 min" onClick="setTime(this)">
                                    <input type="button" name="balance120Mins_btn" class="amountButton" value="120 min" onClick="setTime(this)">
                                </td>
                            </tr>
                        </table>
                        Comment: <textarea class="inputsText_cls" id="comment_txt" name="comment_txt" placeholder="Enter comments specific for this balance..."></textarea>
                    </form>
                </div>
            </div>
            <div id="upperColumn" class="columns">
                <div class="nav">
                    <ul id="timeline_ul">
                        <li id="timeLineMain">Timeline(Unused)
                            <ul>
                                <li>Timeline(ALL)</li>
                                <li>Timeline(Unused)</li>
                                <li>Timeline(Used)</li>
                            </ul>
                        </li>
                        <li>Transaction</li>
                        <li>Reports</li>
                    </ul>
                </div>
            </div>
            <div id="rightColumn" class="columns">
                <table id="timeTable">
                    <thead>
                        
                    <tr>
                        <th style="display:none">ID</th>
                        <th>Customer</th>
                        <th>Balance Time</th>
                        <th>Date</th>
                        <c:if test = "${timeLineType == 'all'}">
                            <th>Type</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${timeProp}" var="_timeProp">
                        <tr>
                            <td style="display:none"><c:out value="${_timeProp.tb_id}"/></td>
                            <td style="display: none;"><c:out value="${_timeProp.tb_comments}"/></td>
                            <td><c:out value="${_timeProp.tb_customername}"/></td>
                            <td><c:out value="${_timeProp.tb_time}"/></td>
                            <td><c:out value="${_timeProp.tb_date}"/></td>
                            <c:if test = "${timeLineType =='all'}">
                                <td>${_timeProp.tb_type}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
    </body>
        <script src="<c:url value="/view/js/jquery321.js"/>"></script>
        <script src="<c:url value="/view/js/timeline.js"/>"></script>
        
</html>
