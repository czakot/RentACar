/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rentacar.backend.entities.Car;

/**
 *
 * @author czakot
 */
public class DaoManager {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private static final String PROTOCOL = "jdbc:derby:";
    private static final String DATABASE = "rentacarDB";
    
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    private static Connection connection;
    
    private final CarDao carDao;


    public DaoManager() {
                carDao = new CarDao(connection);
    }
    
    public List<Car> listCars() {
        openConnection();
        carDao.setConnection(connection);
        List<Car> Cars = carDao.findAll();
        closeConnection();
        return Cars;
    }
    
/*
    save
    update
    findAll
    listCarsAvailable4Rent
    */    
/*
    private void open() {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    private void openConnection(){
        try {
            // move this line into the constructor, it is enough to call once to implicitly register the driver
            // anyway in Java 8 and above it is not neccessery at all
//            Class.forName(new DRIVER)/*.newInstance()*/;
            connection = DriverManager.getConnection(PROTOCOL + DATABASE + ";create=true", USER, PASSWORD);
        } catch (SQLException /*| ClassNotFoundException*/ ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void closeConnection(){
        try {
            if ((connection != null) && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*    
    private void closeConnection() {
        try {
            if ((connection != null) && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeConnection() {
        try {
        DriverManager.getConnection(PROTOCOL + ";shutdown=true");
        } catch (SQLException sqle) {
            if ( sqle.getErrorCode() == 50000) {
                System.err.println("Normal DB leállás: " + sqle.getCause());
            }
        }
    }
*/
}

