/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.AttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author admin
 */
public class StudentReportController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int stdid = Integer.parseInt(request.getParameter("sid"));
        int gid = Integer.parseInt(request.getParameter("gid"));

        int numberOfAbsent = 0;
        AttendanceDBContext attDB = new AttendanceDBContext();
        ArrayList<Attendance> listAttendence = attDB.getAttendenceReport(stdid, gid);
        for (Attendance a : listAttendence) {
            if (a.isPresent()==false && a.getSession().isAttanded()==true) {
                numberOfAbsent++;
            }
        }
        int percenAbsent = numberOfAbsent * 100 / listAttendence.size();
        
        request.setAttribute("list", listAttendence);
        request.setAttribute("numberOfAbsent", numberOfAbsent);
        request.setAttribute("percentageOfAbsent", percenAbsent);
        request.getRequestDispatcher("../view/student/attendencereport.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
