/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.entities;

import java.io.File;
import java.time.LocalDate;
import static rentacar.frontend.cars.BaseCard.fromSelectedJpgs;

/**
 *
 * @author czakot
 */
public class Car extends BareCar {
    
    private Boolean valid;
    private String validationMessage;

    public Car() {
        valid = false;
        validationMessage = "Üres Autó\n";
    }
    
    public Car(BareCar bareCar){
        valid = true;
        validationMessage = "";
        
        setNumberPlate(validateNumberPlate(bareCar.getNumberPlate()));
        setMake(validateNotEmpty("Márka", bareCar.getMake()));
        setModel(validateNotEmpty("Típus", bareCar.getModel()));
        setYearOfManufacturing(validateYearOfManufacturing(bareCar.getYearOfManufacturing()));
        setDailyRentalFee(validateDailyRentalFee(bareCar.getDailyRentalFee()));
        setLastService(validateLastService(bareCar.getLastService()));
        setInService(bareCar.getInService());
        setPhoto(validatePhoto(bareCar.getPhoto()));
        setChoosenPhotoPath(validateChoosenPhoto(bareCar.getChoosenPhotoPath()));
        if (valid) {
            validationMessage = null;
        }
    }
    
    private String validateNumberPlate(String numberPlate) {
        if (!numberPlate.matches("^[ABCDEFGHIJKLMNOPQRSTUVWXYZ]{3}\\d{3}$")) {
            validationMessage += "Rendszám BBB### (B: ékezetmentes nagybetű, #: számjegy) alakú kell legyen!\n";
            valid = false;
        }
        return numberPlate;
    }
    
    private String validateNotEmpty(String fieldName, String field) {
        if (field.isEmpty()) {
            validationMessage += fieldName + " tetszőleges, de nem lehet üres!\n";
            valid = false;
        }
        return field;
    }
    
    private int validateYearOfManufacturing(int yearOfManufacturing) {
        if ( yearOfManufacturing < 1900 || yearOfManufacturing > LocalDate.now().getYear()) {
            validationMessage += "Évjárat 4 számjegyen 1900 és jelenlegi év között érvényes!\n";
            valid = false;
        }
        return yearOfManufacturing;
    }
    
    private int validateDailyRentalFee(int dailyRentalFee) {
        if ( dailyRentalFee < 0) {
            validationMessage += "Napi bérleti díj nemnegatív egész szám kell legyen!\n";
            valid = false;
        }
        return dailyRentalFee;
    }
    
    private LocalDate validateLastService(LocalDate lastService) {
        if (lastService.isAfter(LocalDate.now()) || lastService.getYear() < this.getYearOfManufacturing()) {
            validationMessage += "Utolsó szervizelés dátuma a gyártási év és a mai dátum közé kell essen!\n";
            valid = false;
        }
        return lastService;
    }
    
    private Boolean validatePhoto(Boolean photo) {
        if (photo) {
            File file = new File(fromSelectedJpgs(getNumberPlate()));
            if (!file.canRead() || !file.exists()) {
                validationMessage += "Fotó(korábban kiválasztott) nem létezik vagy nem olvasható!\n";
                valid = false;
            }
        }
        return photo;
    }
    
    private String validateChoosenPhoto(String choosenPhoto) {
        if (choosenPhoto != null) {
            File file = new File(choosenPhoto);
            if (!file.canRead() || !file.exists()) {
                validationMessage += "Fotó(újonnan kiválasztott) nem létezik vagy nem olvasható!\n";
                valid = false;
            } else {
                setPhoto(true);
            }
        }
        return choosenPhoto;
    }
    
    public Boolean isValid() {
        return valid;
    }
    
    public String getValidationMessage() {
        return validationMessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNumberPlate() != null ? getNumberPlate().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.getNumberPlate() == null && other.getNumberPlate() != null) || 
            (this.getNumberPlate() != null && !this.getNumberPlate().equals(other.getNumberPlate()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car[numberPlate=" + getNumberPlate() + "]";
    }
}
