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
public class BareCar {
    private String numberPlate;
    private String make;
    private String model;
    private int yearOfManufacturing;
    private int dailyRentalFee;
    private LocalDate lastService;
    private Boolean inService;
    private Boolean photo;
    private String photoPath;

    public BareCar() {
    }

    public BareCar(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public BareCar(String numberPlate, int dailyRentalFee, LocalDate lastService, Boolean inService, Boolean photo, String photoPath) {
        this.numberPlate = numberPlate;
        this.dailyRentalFee = dailyRentalFee;
        this.lastService = lastService;
        this.inService = inService;
        this.photo = photo;
        this.photoPath = photoPath;
    }

    public BareCar(String numberPlate, String make, String model, int yearOfManufacturing, 
               int dailyRentalFee, LocalDate lastService, Boolean inService, Boolean photo, String photoPath) {
        this.numberPlate = numberPlate;
        this.make = make;
        this.model = model;
        this.yearOfManufacturing = yearOfManufacturing;
        this.dailyRentalFee = dailyRentalFee;
        this.lastService = lastService;
        this.inService = inService;
        this.photo = photo;
        this.photoPath = photoPath;
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
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
