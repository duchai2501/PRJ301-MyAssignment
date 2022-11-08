/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
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
import model.TimeSlot;

/**
 *
 * @author admin
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public ArrayList<Attendance> getAttendenceReport(int stdid, int gid) {
        ArrayList<Attendance> listAtt = new ArrayList<>();
        try {
            String sql = "SELECT s.stdid,g.gid,\n"
                    + "ses.sesid,ses.[date],ses.[index],ses.attanded,\n"
                    + "l.lid,l.lname,r.rid,r.rname,t.tid,t.[description],\n"
                    + "ISNULL(a.present,0) present, ISNULL(a.[description],'') [description]\n"
                    + "FROM Student s\n"
                    + "LEFT JOIN Student_Group sg ON s.stdid =sg.stdid\n"
                    + "INNER JOIN [Group] g ON g.gid = sg.gid\n"
                    + "INNER JOIN [Session] ses on ses.gid = g.gid\n"
                    + "INNER JOIN Lecturer l ON ses.lid = l.lid\n"
                    + "INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "LEFT JOIN Attendance a ON a.sesid = ses.sesid\n"
                    + "WHERE s.stdid =? AND g.gid =? AND (a.stdid = ? OR a.stdid IS NULL)\n"
                    + "ORDER BY ses.[date]";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, stdid);
            stm.setInt(2, gid);
            stm.setInt(3, stdid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GroupDBContext gDB = new GroupDBContext();
                Group group = gDB.get(rs.getInt("gid"));
                Session ses = new Session();
                ses.setGroup(group);
                ses.setId(rs.getInt("sesid"));
                ses.setDate(rs.getDate("date"));
                ses.setIndex(rs.getInt("index"));
                ses.setAttanded(rs.getBoolean("attanded"));

                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                ses.setLecturer(l);

                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                ses.setSlot(t);

                Student s = new Student();
                s.setId(rs.getInt("stdid"));

                Attendance a = new Attendance();
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                a.setStudent(s);
                a.setSession(ses);
                listAtt.add(a);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAtt;
    }

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

    public static void main(String[] args) {
        AttendanceDBContext db = new AttendanceDBContext();
        System.out.println(db.getAttendenceReport(1, 4));
    }
}
