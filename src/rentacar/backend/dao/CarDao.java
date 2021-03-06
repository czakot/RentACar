/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rentacar.backend.entities.Car;

/**
 *
 * @author czakot
 */
public class CarDao extends GenericDao<Car, String> implements ICarDao {

    public CarDao(Connection conn) {
        super(conn, "cars", "numberPlate");
    }

    @Override
    public Boolean save(Car entity){
        Boolean success = true;
        String sql = "INSERT INTO USERNAME.CARS " + 
                         "(NUMBER_PLATE, MAKE, MODEL, YEAR_OF_MANUFACTURING, DAILY_RENTAL_FEE, LAST_SERVICE, IN_SERVICE, PHOTO) " + 
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getNumberPlate());
            statement.setString(2, entity.getMake());
            statement.setString(3, entity.getModel());
            statement.setDate(4, java.sql.Date.valueOf(String.valueOf(entity.getYearOfManufacturing()) + "-12-31"));
            statement.setInt(5, entity.getDailyRentalFee());
            statement.setDate(6, java.sql.Date.valueOf(entity.getLastService()));
            statement.setBoolean(7, entity.getInService());
            statement.setBoolean(8, entity.getPhoto());
            statement.executeUpdate();
        } catch (SQLException ex) {
            success = false;
            System.out.println(ex.toString());
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return success;
    }
    
    @Override
    public void delete(String numberPlate) {
        String sql = "DELETE FROM USERNAME.CARS WHERE NUMBER_PLATE = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, numberPlate);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public Boolean update(Car entity){
            Boolean success = true;
            String sql = "UPDATE USERNAME.CARS " +
                    "SET DAILY_RENTAL_FEE = ?, LAST_SERVICE = ?, IN_SERVICE = ?, PHOTO = ? " +
                    "WHERE NUMBER_PLATE = ?";
            PreparedStatement statement = null;
            ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getDailyRentalFee());
            statement.setDate(2, java.sql.Date.valueOf(entity.getLastService()));
            statement.setBoolean(3, entity.getInService());
            statement.setBoolean(4, entity.getPhoto());
            statement.setString(5, entity.getNumberPlate());
            statement.executeUpdate();
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return success;
    }

    @Override
    public Car findById(String numberPlate){
        String sql = "SELECT * FROM USERNAME.CARS WHERE NUMBER_PLATE = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, numberPlate);

            resultSet = statement.executeQuery();
            Car result = null;
            while (resultSet.next()) {
                result = setCar(resultSet);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return null;
    }
    
    @Override
    public List<Car> findAll(){
        String sql = "SELECT * FROM USERNAME.CARS";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            List<Car> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(setCar(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return null;
    }
    
    @Override
    public List<Car> listCarsAvailable4Rent(){
        // nincs szervizben és nincs kibérelve és 10 évnél fiatalabb és
        // ((5 évnél idősebb és féléves szervizig legalább 1 napja van) vagy
        //  (5 évnél fiatalabb és éves szervizig legalább 1 napja van))
        String sql = 
            "SELECT * FROM USERNAME.CARS " +
            "WHERE IN_SERVICE != TRUE AND NUMBER_PLATE NOT IN " +
            "(SELECT NUMBER_PLATE FROM USERNAME.RENTS WHERE PAID_FEE = 0) AND " +
            "{fn TIMESTAMPDIFF(SQL_TSI_YEAR, YEAR_OF_MANUFACTURING, CURRENT_DATE)} < 10 AND "  +
            "( ( {fn TIMESTAMPDIFF(SQL_TSI_YEAR, YEAR_OF_MANUFACTURING, CURRENT_DATE)} >= 5 AND " +
            "    {fn TIMESTAMPDIFF(SQL_TSI_DAY, LAST_SERVICE, CURRENT_DATE)} < 182 ) OR " +
            "  ( {fn TIMESTAMPDIFF(SQL_TSI_YEAR, YEAR_OF_MANUFACTURING, CURRENT_DATE)} < 5 AND " +
            "    {fn TIMESTAMPDIFF(SQL_TSI_DAY, LAST_SERVICE, CURRENT_DATE)} < 364 ) )";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            List<Car> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(setCar(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return null;
    }
    
    private void close(PreparedStatement statement, ResultSet resultSet){
        try {
            if (!(statement == null || statement.isClosed())) {
                statement.close();
            }
            if (!(resultSet == null || resultSet.isClosed())) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Car setCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setNumberPlate(resultSet.getString("NUMBER_PLATE"));
        car.setMake(resultSet.getString("MAKE"));
        car.setModel(resultSet.getString("MODEL"));
        car.setYearOfManufacturing(resultSet.getDate("YEAR_OF_MANUFACTURING").toLocalDate().getYear());
        car.setDailyRentalFee(resultSet.getInt("DAILY_RENTAL_FEE"));
        car.setLastService((resultSet.getDate("LAST_SERVICE")).toLocalDate());
        car.setInService(resultSet.getBoolean("IN_SERVICE"));
        car.setPhoto(resultSet.getBoolean("PHOTO"));
        return car;
    }
}
