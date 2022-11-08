<%-- 
    Document   : attendencereport
    Created on : Nov 8, 2022, 11:14:27 PM
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
            <table border="1px" width="80%">
                <tr>
                    <td>NO</td>
                    <td>DATE</td>
                    <td>SLOT</td>
                    <td>ROOM</td>
                    <td>Group</td>
                    <td>Lecture</td>
                    <td>ATTENDENCE STATUS</td>
                </tr>

                <c:forEach items="${requestScope.list}" var="attendence">
                    <tr>
                        <td >${attendence.session.id}</td>
                        <td class="date">${attendence.session.date}</td>
                        <td>${attendence.session.slot.id}</td>
                        <td >${attendence.session.room.name}</td>
                        <td>${attendence.session.group.name}</td>
                        <td>${attendence.session.lecturer.name}</td>
                                                 
                            <c:if test="${attendence.present==true}">
                                <td style="color:green;">V</td>
                            </c:if>
                            <c:if test="${attendence.present==false && attendence.session.attanded == true}">
                                <td style="color:red;">X</td>
                                <c:set var="count" value="${count+1}"/>
                            </c:if>
                            <c:if test="${attendence.present==false && attendence.session.attanded == false}">
                                <td>-</td>
                            </c:if>
                            
                        </tr>
                </c:forEach>    
                <tr>
                    <td colspan="5"><h2>Absent ${requestScope.numberOfAbsent}: (${requestScope.percentageOfAbsent})% so far</h2></td>
                </tr>

            </table>
    </body>
</html>
