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
import rentacar.backend.entities.Customer;

/**
 *
 * @author czakot
 */
public class CustomerDao extends GenericDao<Customer, String> implements ICustomerDao {

    public CustomerDao(Connection conn) {
        super(conn, "customers", "idCustomer");
    }

    @Override
    public Boolean save(Customer entity){
        Boolean success = true;
        String sql = "INSERT INTO USERNAME.CUSTOMERS (NAME, ADDRESS, PHONE) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getPhone());
            statement.executeUpdate();
        } catch (SQLException ex) {
            success = false;
            System.out.println(ex.toString());
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return success;
    }
    
    @Override
    public Boolean update(Customer customer) {
        return true;
    }
    
    @Override
    public void delete(String idCustomer) {
        String sql = "DELETE FROM USERNAME.CUSTOMERS WHERE ID_CUSTOMER = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, idCustomer);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
    }

    @Override
    public Customer findById(String idCustomer){
        String sql = "SELECT * FROM USERNAME.CUSTOMERRS WHERE ID_CUSTOMER = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, idCustomer);

            resultSet = statement.executeQuery();
            Customer result = null;
            while (resultSet.next()) {
                result = setCustomer(resultSet);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return null;
    }
    
    @Override
    public List<Customer> findAll(){
        String sql = "SELECT * FROM USERNAME.CUSTOMERS";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            List<Customer> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(setCustomer(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(statement, resultSet);
        }
        return null;
    }
    
    @Override
    public List<Customer> listCustomersEligible4Rent(){
        // EZT A SELECTET MÉG MEG KELL ÍRNI !!!
        String sql = "SELECT * FROM USERNAME.CARS " + 
                         "WHERE IN_SERVICE != TRUE AND NUMBER_PLATE NOT IN " +                                      // nincs szervizben
                         "(SELECT NUMBER_PLATE FROM RENTS WHERE PAID_FEE == 0) AND " +                              // nincs kibérelve
                         "({fn TIMESTAMPDIFF(SQL_TSI_YEAR, CURRENT_DATE, YEAR_OF_MANUFACTURING)} < 10 AND " +       // 10 évnél fiatalabb és
                         "{fn TIMESTAMPDIFF(SQL_TSI_YEAR, CURRENT_DATE, YEAR_OF_MANUFACTURING)} >= 5 AND " +        // 5 évnél idősebb és    
                         "{fn TIMESTAMPDIFF(SQL_TSI_DAY, CURRENT_DATE, LAST_SERVICE)} < 182) OR " +     // szervizig legalább 1 napja van vagy
                         "({fn TIMESTAMPDIFF(SQL_TSI_YEAR, CURRENT_DATE, YEAR_OF_MANUFACTURING)} < 5 AND " +   // 5 évnél fiatalabb és leg-
                         "{fn TIMESTAMPDIFF(SQL_TSI_DAY, CURRENT_DATE, LAST_SERVICE)} < 364)";                  // alább 1 napja van szervizig
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            List<Customer> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(setCustomer(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Customer setCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setIdCustomer(resultSet.getInt("ID_CUSTOMER"));
        customer.setName(resultSet.getString("NAME"));
        customer.setAddress(resultSet.getString("ADDRESS"));
        customer.setPhone(resultSet.getString("PHONE"));
        return customer;
    }
}
