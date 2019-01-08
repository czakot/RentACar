/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author czakot
 */
public class FinishCard extends NewCard {

    public FinishCard(RentDetails rentDetails) {
        super(rentDetails);
    }
    
    @Override
    void modifyContentEditables() {
    final MyTextField idRent;
    JComboBox<Customer> customer;
    JComboBox<Car> car;
    final MyTextField beginningDate;
    final JDatePicker expectedReturnDate;
    final MyTextField returnDate;
    final MyTextField dailyRentalFee;
    final MyTextField paidFee;
    
        disableEditingOnTextField(numberPlate);
        disableEditingOnTextField(make);
        disableEditingOnTextField(model);
        disableEditingOnTextField(yearOfManufacturing);
    }
    
    void setEditorContent(Rent rent) {
        numberPlate.setText(rent.getNumberPlate());
        make.setText(rent.getMake());
        model.setText(rent.getModel());
        yearOfManufacturing.setText(Integer.toString(rent.getYearOfManufacturing()));
        dailyRentalFee.setText(Integer.toString(rent.getDailyRentalFee()));
        try {
            yearOfManufacturing.commitEdit();
            dailyRentalFee.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(FinishCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalDate lS = rent.getLastService();
        lastService.getModel().setYear(lS.getYear());
        lastService.getModel().setMonth(lS.getMonthValue()-1);
        lastService.getModel().setDay(lS.getDayOfMonth());
        inService.setSelected(rent.getInService());
        initialPhoto = rent.getPhoto();
        photoSelector.setText("Fotó választás");
        
        presentPhoto(rent.getPhoto(), fromSelectedJpgs(rent.getNumberPlate()));
    }
}
