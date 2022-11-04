<%-- 
    Document   : takeatt
    Created on : Nov 2, 2022, 8:56:13 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${requestScope.ses.id}, class: ${requestScope.ses.group.name}<br/>
        Subject: ${requestScope.ses.group.subject.name}<br/>
        Room: ${requestScope.ses.room.name}
        Time: ${requestScope.ses.date} ${requestScope.ses.slot.description}<br/>
        Attended: ${requestScope.ses.attanded?"Yes":"No"}
        <form action="takeatt" method="POST">
            <input type="hidden" name="sesid" value="${param.id}"/>    
            <table border="1">
                <tr>
                    <td>Student ID</td>
                    <td>Student Name</td>
                    <td>Present</td>
                    <td>Absent</td>
                    <td>Description</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                    <tr>
                        <td>${a.student.id}
                        <input type="hidden" value="${a.student.id}" name="stdid"/>   
                        </td>
                        <td>${a.student.name}</td>
                        <td>
                            <input type="radio" 
                                   <c:if test="${a.present}">
                                       checked="checked"
                                   </c:if>
                                   name="present${a.student.id}" value="present" />
                        </td>
                        <td>
                            <input type="radio" 
                                   <c:if test="${!a.present}">
                                       checked="checked"
                                   </c:if>
                                   name="present${a.student.id}" value="absent" />
                        </td>
                        <td><input type="" name="description${a.student.id}" value="${a.description}"/></td>
                    </tr>                   
                </c:forEach>
            </table>
            <input type="submit" name="Save">
        </form>
    </body>
</html>
