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
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
import static rentacar.frontend.cars.BaseCard.PHOTO_SELECTED_PATH;

/**
 *
 * @author czakot
 */
public class CarService extends BaseService implements ICarService{
    
    @Override
    public List<Car> listCars() {
        return daoManager.listCars();
    }

    @Override
    public void deleteCar(String numberPlate) {
        daoManager.deleteCar(numberPlate);
    }
    
    @Override
    public boolean addCar(BareCar bareCar) {
        Car car = new Car(bareCar);
        String serviceMessage = car.isValid() ? null : car.getValidationMessage();
        if (serviceMessage == null && getCar(car.getNumberPlate()) == null) {
            if (!daoManager.save(car)) {
                serviceMessage = "DB-be írás sikertelen (részletek Log-ban)";
            } else {
                placeSelectedPhotoIntoSelectedFolder(car);
            }
        }
        setServiceMessage(serviceMessage);
        return serviceMessage == null;
    }
    
    @Override
    public boolean modifyCar(BareCar bareCar) {
        Car car = new Car(bareCar);
        String serviceMessage = car.isValid() ? null : car.getValidationMessage();
        if (serviceMessage == null) {
            if (!daoManager.update(car)) {
                serviceMessage =  "DB-be írás sikertelen (részletek Log-ban)";
            } else {
                placeSelectedPhotoIntoSelectedFolder(car);
            }
        }
        setServiceMessage(serviceMessage);
        return serviceMessage == null;
    }

    @Override
    public List<Car> listCarsAvailable4Rent() {
        return daoManager.listCarsAvailable4Rent();
    }

    private Car getCar(String numberPlate) {
        return daoManager.getCar(numberPlate);
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
            Logger.getLogger(CarService.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CarService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

