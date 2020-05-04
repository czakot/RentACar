/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.util.List;
import rentacar.backend.entities.Customer;

/**
 *
 * @author czakot
 */
public interface ICustomerService {

    /**
     *
     * @param idCustomer
     * @return
     */
    Customer getCustomer(String idCustomer);
    
    /**
     * Ügyfelek listázása
     * 
     * @return 
     */
    List<Customer> listCustomers();

    /**
     * Ügyfél törlése, specifikáción kívüli karbantartáshoz
     *
     * @param idCustomer
     */
    void deleteCustomer(String idCustomer);
    
    /**
     * Új ügyfél felvétele
     * 
     * @param customer
     * @return 
     */
    boolean addCustomer(Customer customer);
    /**
     * Bérlésre jogosult ügyfelek listázása
     * 
     * @return 
     */
    List<Customer> listCustomersEligible4Rent();
}
