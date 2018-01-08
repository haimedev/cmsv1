<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/view/css/timelinecss.css" />" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../pageHeader.jsp"/>
        <div id="wrapper">
            <div id="leftColumn" class="columns">
                <div id="customerColumn">
                    <form action="TimelineController">   
                        <table id="rowClick">
                            <tr>
                                <td>
                                    Name:
                                </td>
                                <td>
                                    <input type="textbox" name="custName_txt" required>
                                </td>
                                <td>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Time:
                                </td>
                                <td>
                                    <input type="number" name="timeHour_txt" required style="width: 50px;"><p style="font-size: 15px; display:inline;">hour</p>
                                    <input type="number" name="timeMinute_txt" required style="width: 50px"><p style="font-size: 15px; display:inline;">min</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    
                                </td>
                                <td>
                                    <input class="customerBtn_cls" type="submit" name="addBalance_btn" value="add">
                                    <input class="customerBtn_cls" type="reset" name="clearBalance_btn" value="clear">
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" name="balance20Mins_btn" value="20 min" onClick="setTime(this)">
                                    <input type="button" name="balance30Mins_btn" value="30 min" onClick="setTime(this)">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" name="balance40Mins_btn" value="40 min" onClick="setTime(this)">
                                    <input type="button" name="balance60Mins_btn" value="60 min" onClick="setTime(this)">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" name="balance80Mins_btn" value="80 min" onClick="setTime(this)">
                                    <input type="button" name="balance120Mins_btn" value="120 min" onClick="setTime(this)">
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div id="upperColumn" class="columns">
                <table id="upperLabels">
                    <tr>
                        <th class="upperLabelsTH">
                            <a href="">Timeline</a>
                        </th>
                        <th class="upperLabelsTH">
                            <a href="">Transaction</a>
                        </th>
                        <th class="upperLabelsTH">
                            <a href="">Reports</a>
                        </th>
                    </tr>
                </table>
            </div>
            <div id="rightColumn" class="columns">
                <table id="timeTable">
                    <thead>
                        
                    <tr>
                        <th style="display:none">ID</th>
                        <th>Customer</th>
                        <th>Balance Time</th>
                        <th>Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${timeProp}" var="_timeProp">
                        <tr>
                            <td style="display:none"><c:out value="${_timeProp.tb_id}"/></td>
                            <td><c:out value="${_timeProp.tb_customername}"/></td>
                            <td><c:out value="${_timeProp.tb_time}"/></td>
                            <td><c:out value="${_timeProp.tb_date}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
    </body>
        <script src="<c:url value="/view/js/timeline.js"/>"></script>
</html>
