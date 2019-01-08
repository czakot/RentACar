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
import rentacar.backend.entities.Rent;

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
        disableEditingOnTextField(idRent);
        // * JComboBox<Customer> customer;
        // * JComboBox<Car> car;
        disableEditingOnTextField(beginningDate);
        // * JDatePicker expectedReturnDate
        // - final MyTextField returnDate;
        disableEditingOnTextField(dailyRentalFee);
        disableEditingOnTextField(paidFee);
    }
    
    void setEditorContent(Rent rent) {
        idRent.setText(rent.getIdRent().toString());
        // customer ComboBox <= rent.getIdCustomer()
        // car ComboBox <= rent.getNumberPlate()
  /*      
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
*/        
    }
}
