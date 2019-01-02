/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend;

import java.util.List;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
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
}
