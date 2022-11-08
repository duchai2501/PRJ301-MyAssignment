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
import model.Group;
import model.Lecturer;
import model.Student;
import model.Subject;

/**
 *
 * @author admin
 */
public class GroupDBContext extends DBContext<Group> {

    @Override
    public void insert(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        try {
            String sql = "SELECT g.gid,g.gname,\n"
                    + "s.stdid,s.stdname,s.imageURL,s.email,\n"
                    + "l.lid,l.lname,l.imageURL,l.email,\n"
                    + "sub.subid,sub.subname\n"
                    + "FROM [Group] g \n"
                    + "INNER JOIN Student_Group sg ON g.gid = sg.gid\n"
                    + "INNER JOIN Student s ON s.stdid =sg.stdid\n"
                    + "INNER JOIN Lecturer l ON l.lid = g.gid\n"
                    + "INNER JOIN [Subject] sub ON g.subid = sub.subid\n"
                    + "WHERE g.gid =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            Group g = null;
            while (rs.next()) {
                if (g == null) {
                    g = new Group();
                    g.setId(rs.getInt("gid"));
                    g.setName(rs.getString("gname"));
                    Subject sub = new Subject();
                    g.setSubject(sub);
                    sub.setId(rs.getInt("subid"));
                    sub.setName(rs.getString("subname"));
                    Lecturer l = new Lecturer();
                    g.setLecturer(l);
                    l.setId(rs.getInt("lid"));
                    l.setName(rs.getString("lname"));
                    l.setImage(rs.getString("imageURL"));
                    l.setEmail(rs.getString("email"));
                } else {
                    Student s = new Student();
                    s.setId(rs.getInt("stdid"));
                    s.setName(rs.getString("stdname"));
                    s.setImage(rs.getString("imageURL"));
                    s.setEmail(rs.getString("email"));

                    g.getStudents().add(s);
                }
            }
            return g;
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
