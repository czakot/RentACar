/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.util.List;
import rentacar.backend.entities.Car;

/**
 *
 * @author czakot
 */
public interface ICarDao {

    /**
     *  Autóhoz Generic-en kívüli Dao 
     * @return 
     */
    List<Car> listCarsAvailable4Rent();
}
