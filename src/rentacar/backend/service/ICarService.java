/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.util.List;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;

/**
 *
 * @author czakot
 */
public interface ICarService {

    /**
     * Autók listázása
     * 
     * @return 
     */
    List<Car> listCars();

    /**
     * Autó törlése, specifikáción kívüli karbantartáshoz
     * 
     * @param numberPlate
     */
    void deleteCar(String numberPlate);

    /**
     * Új autó felvétele
     * 
     * @param bareCar
     * @return
     */
    boolean addCar(BareCar bareCar);
    
    /**
     * Autó módosítható adatainak megváltoztatása
     * 
     * @param bareCar
     * @return 
     */
    boolean modifyCar(BareCar bareCar);
    
    /**
     * Bérlésre rendelkezésre álló autók listázása
     * 
     * @return 
     */
    List<Car> listCarsAvailable4Rent();
}
