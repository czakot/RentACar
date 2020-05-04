/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rentacar.backend.entities.Rent;

/**
 *
 * @author czakot
 */
public class RentDao extends GenericDao<Rent, String> implements IRentDao {

    public RentDao(Connection connection) {
        super(connection, "rents", "idRent");
    }

    @Override
    public Boolean save(Rent entity) {
        Boolean success = true;
        String sql = "INSERT INTO USERNAME.RENTS " + 
                         "(ID_CUSTOMER, NUMBER_PLATE, BEGINNING_DATE, EXPECTED_RETURN_DATE, RETURN_DATE, DAILY_FEE, PAID_FEE) " + 
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getIdCustomer());
            statement.setString(2, entity.getNumberPlate());
            statement.setDate(3, java.sql.Date.valueOf(entity.getBeginningDate()));
            statement.setDate(4, java.sql.Date.valueOf(entity.getExpectedReturnDate()));
            statement.setDate(5, java.sql.Date.valueOf(entity.getReturnDate()));
            statement.setInt(6, entity.getDailyRentalFee());
            statement.setInt(7, entity.getPaidFee());
            statement.executeUpdate();
        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(RentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return success;
    }

    @Override
    public void delete(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean update(Rent entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rent findById(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rent> findAll() {
        String sql = "SELECT * FROM USERNAME.RENTS";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            List<Rent> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(setRent(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(RentDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Rent setRent(ResultSet resultSet) throws SQLException {
        Rent rent = new Rent();
        rent.setIdRent(resultSet.getInt("ID_RENT"));
        rent.setIdCustomer(resultSet.getInt("ID_CUSTOMER"));
        rent.setNumberPlate(resultSet.getString("NUMBER_PLATE"));
        rent.setBeginningDate(resultSet.getDate("BEGINNING_DATE").toLocalDate());
        rent.setExpectedReturnDate(resultSet.getDate("EXPECTED_RETURN_DATE").toLocalDate());
        Date date = resultSet.getDate("RETURN_DATE");
        rent.setReturnDate(date == null ? null : date.toLocalDate());
        rent.setDailyRentalFee(resultSet.getInt("DAILY_FEE"));
        rent.setPaidFee(resultSet.getInt("PAID_FEE"));
        return rent;
    }
}