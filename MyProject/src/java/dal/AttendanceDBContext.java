/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;

/**
 *
 * @author admin
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public ArrayList<Attendance> getAttsBySessionID(int sesid) {
        ArrayList<Attendance> atts = new ArrayList<>();
        String sql = "SELECT s.stdid,s.stdname,s.imageURL,\n"
                + "g.gname,l.lid, l.lname,\n"
                + "ses.sesid,ses.[date],ses.[index],ses.attanded ,\n"
                + "ISNULL(a.present,0) present,ISNULL( a.[description],'') [description]\n"
                + "FROM [Session] ses \n"
                + "INNER JOIN [Group] g ON ses.gid = g.gid\n"
                + "INNER JOIN Lecturer l ON g.lid = l.lid\n"
                + "INNER JOIN Student_Group sg ON g.gid = sg.gid\n"
                + "INNER JOIN Student s ON sg.stdid = s.stdid\n"
                + "LEFT JOIN Attendance a ON a.sesid=ses.sesid AND a.stdid = s.stdid\n"
                + "WHERE ses.sesid =?;";
        try {
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, sesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Student s = new Student();
                att.setStudent(s);
                Session ses = new Session();
                att.setSession(ses);

                Group g = new Group();
                ses.setGroup(g);

                Lecturer l = new Lecturer();
                ses.setLecturer(l);

                att.setPresent(rs.getBoolean("present"));
                att.setDescription(rs.getString("description"));
                s.setId(rs.getInt("stdid"));
                s.setName(rs.getString("stdname"));
                s.setImage(rs.getString("imageURL"));
                g.setName(rs.getString("gname"));
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                ses.setId(sesid);
                ses.setDate(rs.getDate("date"));
                ses.setIndex(rs.getInt("index"));
                ses.setAttanded(rs.getBoolean("attanded"));

                atts.add(att);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
