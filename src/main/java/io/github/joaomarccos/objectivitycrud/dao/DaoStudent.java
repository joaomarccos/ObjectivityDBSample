package io.github.joaomarccos.objectivitycrud.dao;

import com.objy.db.DatabaseClosedException;
import com.objy.db.DatabaseNotFoundException;
import com.objy.db.DatabaseOpenException;
import com.objy.db.app.Iterator;
import com.objy.db.app.oo;
import com.objy.db.app.ooObj;
import io.github.joaomarccos.objectivitycrud.entidades.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaomarcos
 */
public class DaoStudent {

    private DBUtil util;

    public boolean save(Student obj, String key) {
        try {
            util = new DBUtil();
            util.getBd().nameObj(obj, key);
            util.closeConnection();
            return true;
        } catch (DatabaseNotFoundException | DatabaseOpenException | DatabaseClosedException ex) {
            Logger.getLogger(DaoStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Student find(String key) {
        try {
            util = new DBUtil();
            Student lookupObj = (Student) util.getBd().lookupObj(key);

            Student newStudent = new Student(lookupObj.getName(), lookupObj.getMatriculation());
            util.closeConnection();
            return newStudent;

        } catch (DatabaseNotFoundException | DatabaseClosedException | DatabaseOpenException ex) {
            Logger.getLogger(DaoStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean delete(String key) {
        try {
            util = new DBUtil();
            Object lookupObj = util.getBd().lookupObj(key);
            ((ooObj) lookupObj).delete();
            util.closeConnection();
            return true;
        } catch (DatabaseNotFoundException | DatabaseClosedException | DatabaseOpenException ex) {
            Logger.getLogger(DaoStudent.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Student> list() {
        List<Student> students = new ArrayList<>();
        try {
            util = new DBUtil();
            Iterator scan = util.getBd().scan("io.github.joaomarccos.objectivitycrud.entidades.Student");

            while (scan.hasNext()) {
                Student next = (Student) scan.next();
                students.add(new Student(next.getName(), next.getMatriculation()));
            }
            
            util.closeConnection();
            return students;
        } catch (DatabaseNotFoundException | DatabaseClosedException | DatabaseOpenException ex) {
            Logger.getLogger(DaoStudent.class.getName()).log(Level.SEVERE, null, ex);
            return students;
        }        
    }
    
    public boolean update(Student student) {
        try {
            util = new DBUtil();
            Student lookupObj = (Student) util.getBd().lookupObj(student.getMatriculation());
            lookupObj.lock(oo.WRITE);
            lookupObj.setName(student.getName());
            lookupObj.setMatriculation(student.getMatriculation());
            lookupObj.updateIndexes();
            util.closeConnection();
            return true;

        } catch (DatabaseNotFoundException | DatabaseClosedException | DatabaseOpenException ex) {
            Logger.getLogger(DaoStudent.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }
    
    
}
