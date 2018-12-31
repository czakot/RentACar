/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.entities;

import java.time.LocalDate;

/**
 *
 * @author czakot
 */
public class Car {

    private String numberPlate;
    private String make;
    private String model;
    private int yearOfManufacturing;
    private int dailyRentalFee;
    private LocalDate lastService;
    private Boolean inService;
    private Boolean photo;

    public Car() {
    }

    public Car(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Car(String numberPlate, String make, String model, int yearOfManufacturing, 
               int dailyRentalFee, LocalDate lastService, Boolean inService, Boolean photo) {
        this.numberPlate = numberPlate;
        this.make = make;
        this.model = model;
        this.yearOfManufacturing = yearOfManufacturing;
        this.dailyRentalFee = dailyRentalFee;
        this.lastService = lastService;
        this.inService = inService;
        this.photo = photo;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public int getDailyRentalFee() {
        return dailyRentalFee;
    }

    public void setDailyRentalFee(int dailyRentalFee) {
        this.dailyRentalFee = dailyRentalFee;
    }

    public LocalDate getLastService() {
        return lastService;
    }

    public void setLastService(LocalDate lastService) {
        this.lastService = lastService;
    }

    public Boolean getInService() {
        return inService;
    }

    public void setInService(Boolean inService) {
        this.inService = inService;
    }

    public Boolean getPhoto() {
        return photo;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numberPlate != null ? numberPlate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.numberPlate == null && other.numberPlate != null) || (this.numberPlate != null && !this.numberPlate.equals(other.numberPlate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car[numberPlate=" + numberPlate + "]";
    }
    
}
