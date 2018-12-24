/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class RentACar {
 
    /** Az adatbáziskapcsolódáshoz szükséges adatok */
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    
    //private static final String URL = "jdbc:derby:progtech2;create=true";
    private static final String PROTOCOL = "jdbc:derby:";
    private static final String DATABASE = "rentacarDB";
    
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    
    private static final String SCRIPT_PATH = "init.sql";
    private static final String DELIMITER = "(;(\r)?\n)|(--\n)";

    private static Connection connection;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        /* Létrehozza az adatbázis schema-t és a táblákat, ha a schema nem létezik
        */
        initDB();
        
        GuiManager.start();
    }
    
        private static void initDB() throws Exception {
        openConnection();
        if (!exist_DB(USER)) {
            executeScript();
        }
        closeConnection();

/*        
        Statement stmnt = conn.createStatement();
        String sql = "SELECT * FROM \"USERNAME\".\"CARS\"";
        ResultSet rs = stmnt.executeQuery(sql);
        while (rs.next()) {
            String np = rs.getString("number_plate");
            System.out.println(np);
        }        
*/
    }
        
    private static Boolean exist_DB(String schemaName) throws SQLException{
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT COUNT(*) FROM SYS.SYSSCHEMAS " + 
                                              "WHERE SCHEMANAME = \'" + schemaName.toUpperCase() + "\'");
        rs.next();
        return rs.getInt(1) > 0;
    }

    private static void openConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName(DRIVER).newInstance();
        connection = DriverManager.getConnection(PROTOCOL + DATABASE + ";create=true", USER, PASSWORD);
    }
    
    private static void closeConnection() {
        try {
        DriverManager.getConnection(PROTOCOL + ";shutdown=true");
        } catch (SQLException sqle) {
            if ( sqle.getErrorCode() == 50000) {
                System.err.println("Normal DB leállás: " + sqle.getCause());
            }
        }
    }

    private static void executeScript() throws SQLException, FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File(SCRIPT_PATH)));
        scanner.useDelimiter(DELIMITER);
        try(Statement st = connection.createStatement()){
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (line.contains(";")) {
                    line = line.replace(";", "");
                }
                st.execute(line);
            }
        }
    }
}
