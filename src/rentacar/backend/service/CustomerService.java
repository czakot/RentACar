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
public class CustomerService extends BaseService implements ICustomerService {

    @Override
    public Customer getCustomer(String idCustomer) {
        return daoManager.getCustomer(idCustomer);
    }
    
    @Override
    public List<Customer> listCustomers() {
        return daoManager.listCustomers();
    }

    @Override
    public void deleteCustomer(String idCustomer) {
        daoManager.deleteCustomer(idCustomer);
    }
    
    @Override
    public boolean addCustomer(Customer customer) {
        String serviceMessage = customer.validate() ? null : customer.getValidationMessage();
        if (serviceMessage == null) {
            if (!daoManager.save(customer)) {
                serviceMessage =  "DB-be írás sikertelen (részletek Log-ban)";
            }
        }
        setServiceMessage(serviceMessage);
        return serviceMessage == null;
    }

    @Override
    public List<Customer> listCustomersEligible4Rent() {
        return daoManager.listCustomersEligible4Rent();
    }
}
