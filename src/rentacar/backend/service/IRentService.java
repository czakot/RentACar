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
public interface IRentService {
    /**
     * Bérlések listázása
     * 
     * @return 
     */
    List<Rent> listRents();
    
    /**
     * Bérlések szűrt listázása
     * 
     * @param numberPlate
     * @param customerName
     * @param onlyPending
     * @return 
     */
    List<Rent> listRentsFiltered(String numberPlate,String customerName, Boolean onlyPending);
    
    /**
     * Új bérlés felvitele
     * 
     * @param rent
     * @return  
     */
    boolean addRent(Rent rent);
        
    /**
     *
     * @param rent
     * @return 
     */
    boolean finishRent(Rent rent);
    
    /**
     *
     * @param rentId
     */
    void deleteRent(String rentId);
    
}
