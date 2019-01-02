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
     * Autó módosítható adatainak megváltoztatása
     * 
     * @param numberPlate
     * @param dailyRentalFee
     * @param dateOfLastService
     * @param inService
     * @param photo
     * @throws rentacar.backend.service.ServiceException
     */
    void modifyCar(String numberPlate, int dailyRentalFee, LocalDate dateOfLastService, Boolean inService, int photo) throws ServiceException;
    
    /**
     * Ügyfelek listázása
     * 
     * @return 
     */
    List<Customer> listCustomer();

    /**
     * Bérlésre jogosult ügyfelek listázása
     * 
     * @return 
     */
    List<Customer> listCustomersEligible4Rent();
    
    /**
     * Új ügyfél felvétele
     * 
     * @param name
     * @param address
     * @param phone
     */
    void addCustomer(String name, String address, String phone);
    
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
