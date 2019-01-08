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
import rentacar.backend.service.Service;
import rentacar.frontend.windows.RentACarWindowFrame;

/**
 *
 * @author czakot
 */
public class GuiManager {
    
    private static RentACarWindowFrame screen;
    private static Service service = new Service();
//    private final static String BIGDECIMAL_REGEXP = "\\d+(\\.0*)?";
//    private final static String INTEGER_REGEXP = "\\d+";

    private GuiManager() {
    }

    public static void start() {
        screen = new RentACarWindowFrame();
        screen.pack();
        screen.setVisible(true);
    }
    
    public static String getServiceMessage() {
        return service.getServiceMessage();
    }
    
    public static void deleteCar(String numberPlate) {
        service.deleteCar(numberPlate);
    }
    
    public static List<Car> listCars() {
        return service.listCars();
    }
    
    public static void closeDB() {
        service.closeDB();
    }
    
    public static Boolean storeCar(BareCar bareCar) {
        return service.addCar(bareCar);
    }
    
    public static Boolean updateCar(BareCar bareCar) {
        return service.modifyCar(bareCar);
    }

    public static List<Customer> listCustomers() {
        return service.listCustomers();
    }

    public static Boolean storeCustomer(Customer customer) {
        return service.addCustomer(customer);
    }

    public static void deleteCustomer(String idCustomer) {
        service.deleteCustomer(idCustomer);
    }

    public static Customer getCustomer(String idCustomer) {
        service.getCustomer(idCustomer);
    }

    public static Boolean storeRent(Rent rent) {
        return service.addRent(rent);
    }
    
    public static Boolean updateRent(Rent rent) {
        return service.finishRent(rent);
    }
    public static void deleteRent(String idRent) {
        service.deleteRent(idRent);
    }

    public static List<Rent> listRents() {
        return service.listRents();
    }
}
