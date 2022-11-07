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
        <style>
            table,
            td {
                border: 1px solid rgb(146, 128, 128);
                border-collapse: collapse;

            }
            table {
                width: 1200px;

            }
            td {
                text-align: center;
            }
            #img {
                height: 100px;
                width: 80px;
            }


        </style>
    </head>
    <body>
        <h1>Single activity Attendance</h1>
        <p>Attendance for  ${requestScope.ses.group.subject.name} with lecturer ${requestScope.ses.group.lecturer.name} at Slot${ses.id} on ${requestScope.ses.date} , in room ${requestScope.ses.room.name} at FU-HL</p>
        Attended: ${requestScope.ses.attanded?"Yes":"No"}
        <form action="takeatt" method="POST">
            <input type="hidden" name="sesid" value="${param.id}"/>    
            <table class="table" style="width: 100%;">
                <tr>
                    <td>Student ID</td>
                    <td>Student Name</td>
                    <td>Group</td>
                    <td>Image</td>
                    <td>Present</td>
                    <td>Absent</td>
                    <td>Description</td>
                    <td>Tanker</td>
                    <td>Record Time</td>

                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                    <tr>
                        <td>${a.student.id}
                            <input type="hidden" value="${a.student.id}" name="stdid"/>   
                        </td>
                        <td>${a.student.name}</td>
                        <td>${a.session.group.name}</td>
                        <td><img id="img" src="${a.student.image}"/>
                        </td>
                        <td class="status" id="attend">
                            <input type="radio" 
                                   <c:if test="${a.present}">
                                       checked="checked"
                                   </c:if>
                                   name="present${a.student.id}" value="present" />
                        </td>
                        <td >
                            <input type="radio" 
                                   <c:if test="${!a.present}">
                                       checked="checked"
                                   </c:if>
                                   name="present${a.student.id}" value="absent" />
                        </td>
                        <td><input style="border: none;" type="" name="description${a.student.id}" value="${a.description}"/></td>
                        <td>${a.session.lecturer.name}</td>
                        <td> ${requestScope.ses.date} </td>
                    </tr>                   
                </c:forEach>
            </table>
            <input type="submit" name="Save">
        </form>
    </body>
</html>
