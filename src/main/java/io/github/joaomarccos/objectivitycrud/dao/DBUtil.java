package io.github.joaomarccos.objectivitycrud.dao;

import com.objy.db.DatabaseClosedException;
import com.objy.db.DatabaseNotFoundException;
import com.objy.db.DatabaseOpenException;
import com.objy.db.app.Connection;
import com.objy.db.app.Session;
import com.objy.db.app.oo;
import com.objy.db.app.ooFDObj;
import com.objy.db.app.storage.ooDBObj;

/**
 *
 * @author joaomarcos
 */
public class DBUtil {

    private Connection conn;
    private Session session;
    private ooFDObj fd;
    private ooDBObj bd;
    private final String dbName = "courses";
    private final String bootfile = "/home/joaomarcos/NetBeansProjects/ObjectivityCrud/data/courses.boot";

    public DBUtil() throws DatabaseNotFoundException, DatabaseOpenException {
        init();
    }

    private void init() throws DatabaseNotFoundException, DatabaseOpenException {
        conn = Connection.open(bootfile, oo.openReadWrite);
        session = new Session(20, 20);
        session.setOpenMode(oo.openReadWrite);
        session.begin();
        
        fd = session.getFD();
        createDB();
    }

    private void createDB() {
        if (!fd.hasDB(dbName)) {
            bd = fd.newDB(dbName);
        }
        bd = fd.lookupDB(dbName);
    }

    public ooDBObj getBd() {
        return bd;
    }
    
    public void closeConnection() throws DatabaseClosedException {
        this.session.commit();
        this.session.terminate();
        this.conn.close();
    }

}
