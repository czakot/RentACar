/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.EmbeddedDriver;
import rentacar.backend.entities.Car;
import rentacar.backend.entities.Customer;
import rentacar.backend.entities.Rent;

/**
 *
 * @author czakot
 */
public class DaoManager {

    /** Az adatbáziskapcsolódáshoz szükséges adatok */
    private static final String PROTOCOL = "jdbc:derby:";
    private static final String DATABASE = "rentacarDB";
    
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    
    private static final String SCRIPT_PATH = "init.sql";
    private static final String DELIMITER = "(;(\r)?\n)|(--\n)";

    private static Connection connection;
    
    private final CarDao carDao;
    private final CustomerDao customerDao;
    private final RentDao rentDao;


    public DaoManager() {

        initDB();
        carDao = new CarDao(connection);
        customerDao = new CustomerDao(connection);
        rentDao = new RentDao(connection);
    }
    
    public Car getCar(String numberPlate) {
        openConnection();
        carDao.setConnection(connection);
        Car car = carDao.findById(numberPlate);
        closeConnection();
        return car;
    }
    

    public List<Car> listCars() {
        openConnection();
        carDao.setConnection(connection);
        List<Car> cars = carDao.findAll();
        closeConnection();
        return cars;
    }
    
    public void deleteCar(String numberPlate){
        openConnection();
        carDao.setConnection(connection);
        carDao.delete(numberPlate);
        closeConnection();
    }
    
    public boolean save(Car car){
        openConnection();
        carDao.setConnection(connection);
        boolean successfulDataBaseWriting = carDao.save(car);
        closeConnection();
        return successfulDataBaseWriting;
    }
    
    public boolean update(Car car){
        openConnection();
        carDao.setConnection(connection);
        boolean successfulDataBaseUpdating =  carDao.update(car);
        closeConnection();
        return successfulDataBaseUpdating;
    }
    
    public List<Car> listCarsAvailable4Rent() {
        openConnection();
        carDao.setConnection(connection);
        List<Car> cars = carDao.listCarsAvailable4Rent();
        closeConnection();
        return cars;
    }
    
    public Customer getCustomer(String idCustomer) {
        openConnection();
        customerDao.setConnection(connection);
        Customer customer = customerDao.findById(idCustomer);
        closeConnection();
        return customer;
    }
    
    public List<Customer> listCustomers() {
        openConnection();
        customerDao.setConnection(connection);
        List<Customer> customers = customerDao.findAll();
        closeConnection();
        return customers;
    }
    
    public void deleteCustomer(String idCustomer){
        openConnection();
        customerDao.setConnection(connection);
        customerDao.delete(idCustomer);
        closeConnection();
    }

    public boolean save(Customer customer){
        openConnection();
        customerDao.setConnection(connection);
        boolean successfulDataBaseWriting = customerDao.save(customer);
        closeConnection();
        return successfulDataBaseWriting;
    }
    
    public List<Rent> listRents() {
        openConnection();
        rentDao.setConnection(connection);
        List<Rent> rents = rentDao.findAll();
        closeConnection();
        return rents;
    }
    
    public void deleteRent(String idRent){
        openConnection();
        rentDao.setConnection(connection);
        rentDao.delete(idRent);
        closeConnection();
    }
    
    public boolean save(Rent rent){
        openConnection();
        rentDao.setConnection(connection);
        boolean successfulDataBaseWriting = rentDao.save(rent);
        closeConnection();
        return successfulDataBaseWriting;
    }
    
    public List<Customer> listCustomersEligible4Rent() {
        openConnection();
        customerDao.setConnection(connection);
        List<Customer> customers = customerDao.listCustomersEligible4Rent();
        closeConnection();
        return customers;
    }
    
    public void closeDB() {
        try {
            DriverManager.getConnection(PROTOCOL + ";shutdown=true", USER, PASSWORD);
        } catch (SQLException ex) {
            // ignore ex: Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openConnection(){
        try {
            connection = DriverManager.getConnection(PROTOCOL + DATABASE + ";create=true", USER, PASSWORD);
        } catch (SQLException ex) {
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
    
    private void initDB() {
        System.setProperty("derby.system.home", "data");
        try {
            DriverManager.registerDriver(new EmbeddedDriver()); // DRIVER: org.apache.derby.jdbc.EmbeddedDriver
        } catch (SQLException ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        openConnection();
        if (!exist_DB(USER)) {
            executeScript();
        }
        closeConnection();
    }

    private Boolean exist_DB(String schemaName){
            Statement stmnt;
            ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("SELECT COUNT(*) FROM SYS.SYSSCHEMAS " +
                    "WHERE SCHEMANAME = \'" + schemaName.toUpperCase() + "\'");
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void executeScript() {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileInputStream(new File(SCRIPT_PATH)));
            scanner.useDelimiter(DELIMITER);
            try(Statement st = connection.createStatement()){
                int lnr = 1;
                while (scanner.hasNext()) {
                    String line = scanner.next();
                    if (line.contains(";")) {
                        line = line.replace(";", "");
                    }
                    st.execute(line);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DaoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

