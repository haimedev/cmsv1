<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/view/css/AmountBalancecss.css" />" rel="stylesheet">
        <link href="<c:url value="/view/css/TimelineDesignCss.css" />" rel="stylesheet">
        <title>JSP Page</title>
        <script src="<c:url value="/view/js/jquery321.js"/>"></script>
    </head>
    <body>
        <jsp:include page="../pageHeader.jsp"/>
        <a id="back_btn" href="${pageContext.request.contextPath}/HomeController"> Back <--</a>
        <a id="title_btn" href="${pageContext.request.contextPath}/AmountBalanceController"> Account Balance Management </a>
        <div id="wrapper">    
            <div id="leftColumn" class="columns">
                <div id="customerColumn">
                    <form action="AmountBalanceController" name="addForm">   
                        <table id="rowClick">
                            <tr>
                                <td>
                                    Name:
                                </td>
                                <td>
                                    <input class="inputsText_cls" type="textbox" name="custName_txt" id="custName_txt" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Amount:
                                </td>
                                <td>
                                    <input class="inputsText_cls" type="number" name="amount_txt" id="amount_txt" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    
                                </td>
                                <td>
                                    <input class="customerBtn_cls" type="submit" name="addBalance_btn" value="Add">
                                    <input class="customerBtn_cls" type="reset" name="clearBalance_btn" value="Clear">
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input class="amountButton" type="button" id="amount5_btn" value="P5.00">
                                    <input class="amountButton" type="button" id="amount8_btn" value="P8.00" onClick="setTime(this)">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" id="amount10_btn" class="amountButton" value="P10.00" onClick="setTime(this)">
                                    <input type="button" id="amount15_btn" class="amountButton" value="P15.00" onClick="setTime(this)">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <input type="button" id="amount20_btn" class="amountButton" value="P20.00" onClick="setTime(this)">
                                    <input type="button" id="amount25_btn" class="amountButton" value="P25.00" onClick="setTime(this)">
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
                <table id="timeTable">
                    <thead>
                        
                    <tr>
                        <th style="display:none">ID</th>
                        <th>Customer</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <c:if test = "${timeLineType == 'all'}">
                            <th>Type</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${balanceProp}" var="_balanceProp">
                        <tr>
                            <td style="display:none"><c:out value="${_balanceProp.ab_id}"/></td>
                            <td style="display:none"><c:out value="${_balanceProp.ab_comments}"/></td>
                            <td><c:out value="${_balanceProp.ab_customername}"/></td>
                            <td><c:out value="${_balanceProp.ab_amount}"/></td>
                            <td><c:out value="${_balanceProp.ab_date}"/></td>
                            <c:if test = "${timeLineType =='all'}">
                                <td>${_balanceProp.ab_type}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
        <script src="<c:url value="/view/js/AmountBalance.js"/>"></script>
</html>
