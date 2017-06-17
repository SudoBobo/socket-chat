package chat.server.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class ConnectionPool {
    private Vector<Connection> availableConns = new Vector<Connection>();
    private Vector<Connection> usedConns = new Vector<Connection>();

    private Properties properties;

//    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test1";


    private static final String url = "jdbc:mysql://localhost:3306/chat_base?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";
    private static final String MAX_POOL = "250"; // set your own limit


    public ConnectionPool(int initialNumberOfConnections ) {
//        try {
//            Class.forName(DATABASE_DRIVER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < initialNumberOfConnections; i++) {
            availableConns.addElement(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, getProperties());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);
        return newConn;
    }

    public synchronized void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    public void closeAll() {

    }
}