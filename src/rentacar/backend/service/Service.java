/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rentacar.backend.dao.DaoManager;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
import rentacar.backend.entities.Customer;
import rentacar.backend.entities.Rent;
import static rentacar.frontend.cars.BaseCard.PHOTO_SELECTED_PATH;

/**
 *
 * @author czakot
 */
public class Service implements IService {
    
    DaoManager daoManager;
    private String serviceMessage;
    
    public Service() {
        daoManager = new DaoManager();
        serviceMessage = null;
    }
    
    public void closeDB() {
        daoManager.closeDB();
    }

    @Override
    public Car getCar(String numberPlate) {
        return daoManager.getCar(numberPlate);
    }

    @Override
    public List<Car> listCars() {
        return daoManager.listCars();
    }

    @Override
    public List<Car> listCarsAvailable4Rent() {
        return null;
    }

    @Override
    public Boolean addCar(BareCar bareCar) {
        Car car = new Car(bareCar);
        serviceMessage = car.isValid() ? null : car.getValidationMessage();
        if (serviceMessage == null && getCar(car.getNumberPlate()) == null) {
            if (!daoManager.save(car)) {
                serviceMessage =  "DB-be írás sikertelen (részletek Log-ban)";
            } else {
                placeSelectedPhotoIntoSelectedFolder(car);
            }
        }
        return serviceMessage == null;
    }
    
    @Override
    public void deleteCar(String numberPlate) {
        daoManager.deleteCar(numberPlate);
    }
    
    @Override
    public Boolean modifyCar(BareCar bareCar) {
        Car car = new Car(bareCar);
        serviceMessage = car.isValid() ? null : car.getValidationMessage();
        if (serviceMessage == null) {
            if (!daoManager.update(car)) {
                serviceMessage =  "DB-be írás sikertelen (részletek Log-ban)";
            } else {
                placeSelectedPhotoIntoSelectedFolder(car);
            }
        }
        return serviceMessage == null;
    }
    
    @Override
    public List<Customer> listCustomers() {
        return daoManager.listCustomers();
    }

    @Override
    public List<Customer> listCustomersEligible4Rent() {
        return null;
    }
    
    @Override
    public Boolean addCustomer(Customer customer) {
        serviceMessage = customer.validate() ? null : customer.getValidationMessage();
        if (serviceMessage == null) {
            if (!daoManager.save(customer)) {
                serviceMessage =  "DB-be írás sikertelen (részletek Log-ban)";
            }
        }
        return serviceMessage == null;
    }
    
    @Override
    public Customer getCustomer(String idCustomer) {
        return daoManager.getCustomer(idCustomer);
    }

    @Override
    public void deleteCustomer(String idCustomer) {
        daoManager.deleteCustomer(idCustomer);
    }
    
    @Override
    public List<Rent> listRents() {
        return null;
    }
    
    @Override
    public List<Rent> listRentsFiltered(String numberPlate,String customerName, Boolean onlyPending) {
        return null;
    }
    
    @Override
    public Boolean addRent(Rent rent) {
        return true;
    }
    
    @Override
    public Boolean finishRent(Rent rent) {
        return true;
    }

    public void deleteRent(String idRent) {
        daoManager.deleteRent(idRent);
    }
    
    public String getServiceMessage() {
        return serviceMessage;
    }

    private void placeSelectedPhotoIntoSelectedFolder(Car car) {
        if (car.getChoosenPhotoPath() == null) {
            return;
        }
        String destinationPathNoExt = PHOTO_SELECTED_PATH + File.separator + car.getNumberPlate().toLowerCase();
        moveDestinationFile(0,destinationPathNoExt);
        Path source = new File(car.getChoosenPhotoPath()).toPath();
        Path destination = new File(destinationPathNoExt + "_0.jpg").toPath();
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void moveDestinationFile(int serial, String destinationPathNoExt) {
        File destinationFile = new File(destinationPathNoExt + "_" + Integer.toString(serial) + ".jpg");
        if (destinationFile.exists()) {
            moveDestinationFile(serial+1, destinationPathNoExt);
            Path sourcePath = destinationFile.toPath();
            Path destinationPath = new File(destinationPathNoExt + "_" + Integer.toString(serial+1) + ".jpg").toPath();
            try {
                Files.move(sourcePath,destinationPath,StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
