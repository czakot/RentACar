/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import rentacar.backend.entities.BareCar;

/**
 *
 * @author czakot
 */
public class EditorCard extends NewCard {

    public EditorCard(CarDetails carDetails) {
        super(carDetails);
    }
    
    @Override
    void modifyContentEditables() {
        disableEditingOnTextField(numberPlate);
        disableEditingOnTextField(make);
        disableEditingOnTextField(model);
        disableEditingOnTextField(yearOfManufacturing);
    }
    
    void setEditorContent(BareCar car) {
        numberPlate.setText(car.getNumberPlate());
        make.setText(car.getMake());
        model.setText(car.getModel());
        yearOfManufacturing.setText(Integer.toString(car.getYearOfManufacturing()));
        dailyRentalFee.setText(Integer.toString(car.getDailyRentalFee()));
        try {
            yearOfManufacturing.commitEdit();
            dailyRentalFee.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(EditorCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalDate lS = car.getLastService();
        lastService.getModel().setYear(lS.getYear());
        lastService.getModel().setMonth(lS.getMonthValue()-1);
        lastService.getModel().setDay(lS.getDayOfMonth());
        inService.setSelected(car.getInService());
        initialPhoto = car.getPhoto();
        photoSelector.setText("Fotó választás");
        
        presentPhoto(car.getPhoto(), fromSelectedJpgs(car.getNumberPlate()));
    }
}
