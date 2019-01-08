/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.ToolTipManager;
import javax.swing.text.MaskFormatter;
import org.jdatepicker.*;
import rentacar.backend.entities.Car;
import rentacar.backend.entities.Customer;
import rentacar.backend.entities.Rent;
import rentacar.utility.MyTextField;

/**
 *
 * @author czakot
 */
public class NewCard extends BaseCard {

    final MyTextField idRent;
    JComboBox<Customer> customer;
    JComboBox<Car> car;
    final MyTextField beginningDate;
    final JDatePicker expectedReturnDate;
    final MyTextField returnDate;
    final MyTextField dailyRentalFee;
    final MyTextField paidFee;
    
    public static final String TOOLTIP_ID = "Az adatbázis által generált egyéni azonosító";
    public static final String TOOLTIP_BEGINNING_DATE = "Csak a mai naptól indítható bérlés";
    public static final String TOOLTIP_EXPECTED_RETURN_DATE = "Legkésőbb a következő kötelező szerviz előtti napig";
    public static final String TOOLTIP_AT_FINISH = "A bérlés lezárásakor aktualizálódnak";
    public static final String TOOLTIP_DAILY_RENTAL_FEE = "A kiválasztott autó bérleti díja (automatikus)";
    
    public NewCard(RentDetails rentDetails) {
        super(rentDetails);
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        idRent = new MyTextField("");
        idRent.setPreferredSize(new Dimension(303, 27));
        disableEditingOnTextField(idRent);
        idRent.setToolTipText(TOOLTIP_ID);
        content.add(idRent,gbc);
        
        gbc.gridy++;
//        ComboBoxModel<Customer> customerModel = new ComboBoxModel<>()
        customer = new JComboBox<>();
        customer.setPreferredSize(new Dimension(303, 27));
        content.add(customer,gbc);
            
        gbc.gridy++;
//        ComboBoxModel<Car> carModel = new ComboBoxModel<>()
        car = new JComboBox<>();
        car.setPreferredSize(new Dimension(303, 27));
        content.add(car,gbc);
            
        gbc.gridy++;
        beginningDate = new MyTextField(LocalDate.now().toString());
        beginningDate.setPreferredSize(new Dimension(303, 27));
        disableEditingOnTextField(beginningDate);
        content.add(beginningDate,gbc);
            
        gbc.gridy++;
        expectedReturnDate = new JDatePicker(new Date());
        content.add(expectedReturnDate,gbc);

        gbc.gridy++;
        returnDate = new MyTextField("");
        returnDate.setPreferredSize(new Dimension(303, 27));
        disableEditingOnTextField(returnDate);
        content.add(returnDate,gbc);
            
        gbc.gridy++;
        dailyRentalFee = new MyTextField("");
        dailyRentalFee.setPreferredSize(new Dimension(303, 27));
        disableEditingOnTextField(dailyRentalFee);
        content.add(dailyRentalFee,gbc);
            
        gbc.gridy++;
        paidFee = new MyTextField("0");
        paidFee.setPreferredSize(new Dimension(303, 27));
        disableEditingOnTextField(paidFee);
        content.add(paidFee,gbc);
        
        modifyContentEditables();
    }
    
    @Override
    void modifyContentEditables() {
        setDatePickerWithLocaldate(expectedReturnDate, LocalDate.now());
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
        if (this.getClass().getName().equals("rentacar.frontend.components.rents.NewCard")) {
            beginningDate.setToolTipText(TOOLTIP_BEGINNING_DATE);
            expectedReturnDate.setToolTipText(TOOLTIP_EXPECTED_RETURN_DATE);
            returnDate.setToolTipText(TOOLTIP_AT_FINISH);
            dailyRentalFee.setToolTipText(TOOLTIP_DAILY_RENTAL_FEE);
            paidFee.setToolTipText(TOOLTIP_AT_FINISH);
        }
    }
    
    void reset() {
        idRent.setText("");
        // reset customer combo
        // reset car combo
        beginningDate.setText(LocalDate.now().toString());
        setDatePickerWithLocaldate(expectedReturnDate, LocalDate.now());
        returnDate.setText("");
        dailyRentalFee.setText("");
        paidFee.setText("0");
    }
    
    private void setDatePickerWithLocaldate(JDatePicker datePicker, LocalDate localDate) {
        datePicker.getModel().setDate(localDate.getYear(),
                                      localDate.getMonthValue()-1,
                                      localDate.getDayOfMonth());        
    }

    private MaskFormatter createFormatter(String formatString, String validChars) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(formatString);
            formatter.setValidCharacters(validChars);
        } catch (ParseException ex) {
            Logger.getLogger(NewCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formatter;
    }
    
    Rent getRent() {
        Rent rent = new Rent();
        rent.setIdCustomer(Integer.valueOf("customer ComboBox-ból"));
        rent.setNumberPlate("car ComboBox-ból");
        rent.setBeginningDate(LocalDate.now());
        rent.setExpectedReturnDate(LocalDate.of(expectedReturnDate.getModel().getYear(),
                                            expectedReturnDate.getModel().getMonth()+1, 
                                            expectedReturnDate.getModel().getDay()));
        rent.setReturnDate(null);
        rent.setDailyRentalFee(Integer.valueOf(dailyRentalFee.getText()));
        rent.setPaidFee(0);
        
        return rent;
    }
}
