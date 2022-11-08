<%-- 
    Document   : timetable
    Created on : Nov 8, 2022, 2:43:14 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Lecturer: <input type="text" readonly="readonly" value="${requestScope.lecturer.name}"/>
        <form action="timetable" method="GET">
            <input type="hidden" name="lid" value="${param.lid}"/>
            From: <input type="date" name="from" value="${requestScope.from}"/>
            To: <input type="date" name="to" value="${requestScope.to}"/>
            <input type="submit" value="View"/> 
        </form>
        <table border="1px">
            <tr>
                <td> </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>${d}<br/>${helper.getDayNameOfWeek(d)}</td>
                    </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slots">
                <tr>
                    <td>${slots.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.slot.id eq slots.id)}">
                                    <a href="att?id=${ses.id}">${ses.group.name}-${ses.group.subject.name}</a>
                                    <br/>
                                    ${ses.room.name}<br>
                                    <c:if test="${ses.attanded}">
                                        ( <a style="color: green">Atteanded</a> )
                                    </c:if>
                                    <c:if test="${!ses.attanded}">
                                       ( <a style="color: red">Not Yet</a> )
                                    </c:if>
                                </c:if> 
                                  
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
