/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.util.List;
import rentacar.backend.entities.Customer;

/**
 *
 * @author czakot
 */
public interface ICustomerDao {

    /**
     *  Ügyfélhez Generic-en kívüli Dao 
     * @return 
     */
    List<Customer> listCustomersEligible4Rent();
}
