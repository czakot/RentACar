/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.time.LocalDate;
import java.util.List;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
import rentacar.backend.entities.Rent;
import rentacar.backend.entities.Customer;

/**
 *
 * @author czakot
 */
public interface IService {

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
    
    /**
     * Ügyfelek listázása
     * 
     * @return 
     */
    List<Customer> listCustomers();

    /**
     * Bérlésre jogosult ügyfelek listázása
     * 
     * @return 
     */
    List<Customer> listCustomersEligible4Rent();
    
    /**
     * Új ügyfél felvétele
     * 
     * @param customer
     * @return 
     */
    Boolean addCustomer(Customer customer);
    
    /**
     * Ügyfél törlése, specifikáción kívüli karbantartáshoz
     *
     * @param idCustomer
     */
    void deleteCustomer(String idCustomer);
    
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
     * @param customerId
     * @param numberPlate
     * @param expectedReturnDate 
     */
    void addRent(int customerId, String numberPlate, LocalDate expectedReturnDate);
    
    /**
     * Függő bérlés lezárása
     * 
     * @param rentId 
     */
    void closeRent(int rentId);
}
