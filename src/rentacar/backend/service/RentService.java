/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.time.LocalDate;
import java.util.List;
import rentacar.backend.entities.Rent;

/**
 *
 * @author czakot
 */
public class RentService extends AService implements IRentService {

    @Override
    public List<Rent> listRents() {
        return null;
    }
    
    @Override
    public List<Rent> listRentsFiltered(String numberPlate,String customerName, Boolean onlyPending) {
        return null;
    }
    
    @Override
    public boolean addRent(int customerId, String numberPlate, LocalDate expectedReturnDate) {
    }
    
    @Override
    public void closeRent(int rentId) {
    }
    
}
