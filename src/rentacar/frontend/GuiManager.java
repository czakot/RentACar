/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend;

import java.util.List;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
import rentacar.backend.entities.Customer;
import rentacar.backend.entities.Rent;
import rentacar.backend.service.CarService;
import rentacar.backend.service.CustomerService;
import rentacar.backend.service.RentService;
import rentacar.backend.service.ServicesCommon;
import rentacar.frontend.windows.RentACarWindowFrame;

/**
 *
 * @author czakot
 */
public class GuiManager {
    
    private static RentACarWindowFrame screen;
    private static final CarService carService = new CarService();
    private static final CustomerService customerService = new CustomerService();
    private static final RentService rentService = new RentService();
//    private final static String BIGDECIMAL_REGEXP = "\\d+(\\.0*)?";
//    private final static String INTEGER_REGEXP = "\\d+";

    private GuiManager() {
    }

    public static void start() {
        screen = new RentACarWindowFrame();
        screen.pack();
        screen.setVisible(true);
    }
    
    public static String getCarServiceMessage() {
        return CarService.getServiceMessage();
    }

    public static String getCustomerServiceMessage() {
        return CustomerService.getServiceMessage();
    }

    public static String getServiceMessage() {
        return RentService.getServiceMessage();
    }
    
    public static List<Car> listCars() {
        return carService.listCars();
    }
    
    public static void deleteCar(String numberPlate) {
        carService.deleteCar(numberPlate);
    }
    
    public static boolean storeCar(BareCar bareCar) {
        return carService.addCar(bareCar);
    }
    
    public static boolean updateCar(BareCar bareCar) {
        return carService.modifyCar(bareCar);
    }

    public static List<Car> listCarsAvailable4Rent() {
        return carService.listCarsAvailable4Rent();
    }
    
    public static void closeDB() {
        ServicesCommon.closeDB();
    }
    
    public static Customer getCustomer(String idCustomer) {
        return customerService.getCustomer(idCustomer);
    }

    public static List<Customer> listCustomers() {
        return customerService.listCustomers();
    }
    
    public static void deleteCustomer(String idCustomer) {
        customerService.deleteCustomer(idCustomer);
    }

    public static boolean storeCustomer(Customer customer) {
        return customerService.addCustomer(customer);
    }

    public static List<Customer> listCustomersEligible4Rent() {
        return customerService.listCustomersEligible4Rent();
    }

    public static boolean storeRent(Rent rent) {
        return rentService.addRent(rent);
    }
    
    public static boolean updateRent(Rent rent) {
        return rentService.finishRent(rent);
    }

    public static void deleteRent(String idRent) {
        rentService.deleteRent(idRent);
    }

    public static List<Rent> listRents() {
        return rentService.listRents();
    }
}
