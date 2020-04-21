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
     * Egy autó lekérdezése rendszám (kulcs) alapján
     * 
     * @param numberPlate
     * @return 
     */
    Car getCar(String numberPlate);

    /**
     * Autók listázása
     * 
     * @return 
     */
    List<Car> listCars();

    /**
     * Bérlésre rendelkezésre álló autók listázása
     * 
     * @return 
     */
    List<Car> listCarsAvailable4Rent();

    /**
     * Új autó felvétele
     * 
     * @param bareCar
     * @return
     */
    Boolean addCar(BareCar bareCar);
    
    /**
     * Autó törlése, specifikáción kívüli karbantartáshoz
     * 
     * @param numberPlate
     */
    void deleteCar(String numberPlate);
    
    /**
     * Autó módosítható adatainak megváltoztatása
     * 
     * @param bareCar
     * @return 
     */
    Boolean modifyCar(BareCar bareCar);
}
