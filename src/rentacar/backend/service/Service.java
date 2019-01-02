/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import java.time.LocalDate;
import java.util.List;
import rentacar.backend.dao.DaoManager;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
import rentacar.backend.entities.Customer;
import rentacar.backend.entities.Rent;

/**
 *
 * @author czakot
 */
public class Service implements IService {
    
    DaoManager daoManager;
    
    public Service() {
        daoManager = new DaoManager();
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
        daoManager.save(car);
        return car.getValid();
    }
    
    @Override
    public Boolean modifyCar(BareCar bareCar) {
        Car car = new Car(bareCar);
        daoManager.update(car);
        return car.getValid();
    }
    
    @Override
    public List<Customer> listCustomer() {
        return null;
    }

    @Override
    public List<Customer> listCustomersEligible4Rent() {
        return null;
    }
    
    @Override
    public void addCustomer(String name, String address, String phone) {
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
    public void addRent(int customerId, String numberPlate, LocalDate expectedReturnDate) {
    }
    
    @Override
    public void closeRent(int rentId) {
    }
}
