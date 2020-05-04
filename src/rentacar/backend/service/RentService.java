/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.util.List;
import rentacar.backend.entities.Rent;

/**
 *
 * @author czakot
 */
public class RentService extends BaseService implements IRentService {

    @Override
    public List<Rent> listRents() {
        return daoManager.listRents();
    }
    
    @Override
    public List<Rent> listRentsFiltered(String numberPlate,String customerName, Boolean onlyPending) {
        return null;
    }
    
    @Override
    public boolean addRent(Rent rent) {
        return daoManager.save(rent);
    }
    
    @Override
    public boolean finishRent(Rent rent) {
        return true;
    }
    
    @Override
    public void deleteRent(String rentId) {
        
    }
    
}
