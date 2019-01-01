/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.text.MaskFormatter;
import org.jdatepicker.*;
import rentacar.utility.MyFormattedTextField;
import rentacar.utility.MyTextField;

/**
 *
 * @author czakot
 */
public class NewCard extends BaseCard {

    MyFormattedTextField numberPlate;
    MyTextField make;
    MyTextField model;
    MyFormattedTextField yearOfManufacturing;
    MyFormattedTextField dailyRentalFee;
    JDatePicker lastService;
    JCheckBox inService;
    JButton photoSelector;
    JFileChooser fileChooser = new JFileChooser();
    
    public NewCard(CarDetails carDetails) {
        super(carDetails);
        fileChooser = new JFileChooser(System.getProperty("user.dir") + fileSeparator + "photos_container");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        numberPlate = new MyFormattedTextField(createFormatter("UUU###",
                                                              "0123456789abcdefghijklmnopqrstxyvwzABCDEFGHIJKLMNOPQRSTXYVWZ"));
        content.add(numberPlate,gbc);
        
        gbc.gridy++;
        make = new MyTextField("");
        content.add(make,gbc);
            
        gbc.gridy++;
        model = new MyTextField("");
        content.add(model,gbc);
            
        gbc.gridy++;
        yearOfManufacturing = new MyFormattedTextField(createFormatter("####","0123456789"));
        content.add(yearOfManufacturing,gbc);
        
        gbc.gridy++;
        dailyRentalFee = new MyFormattedTextField(createFormatter("#######","0123456789"));
        dailyRentalFee.setToolTipText("Bérleti díj összege: maximum 7 számjeggyel [csak pozitív vagy 0]");
        content.add(dailyRentalFee,gbc);
        
        gbc.gridy++;
        lastService = new JDatePicker(new Date());
        content.add(lastService,gbc);

        gbc.gridy++;
        inService = new JCheckBox("nem");
        inService.setHorizontalTextPosition(SwingConstants.LEFT);
        inService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inService.setText(inService.isSelected() ? "igen" : "nem");
            }
        });
        content.add(inService,gbc);

        gbc.gridy++;
        photoSelector = new JButton("Fotó választás");
        photoSelector.addActionListener(this::photoSelection);
        content.add(photoSelector,gbc);

        modifyContentEditables();
    }
    
    @Override
    void modifyContentEditables() {
        setDatePickerWithLocaldate(lastService, LocalDate.now().plusDays(1));
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
        if (this.getClass().getName().equals("rentacar.frontend.components.cars.NewCard")) {
            numberPlate.setToolTipText("Rendszám: BBB### (BBB: betű, #: számjegy) alakban");
            make.setToolTipText("Márka: tetszőleges szöveg");
            model.setToolTipText("Típus: tetszőleges szöveg");
            yearOfManufacturing.setToolTipText("Évjárat: 4 számjeggyel írva [1900-tól az idei évig]");
        }
    }
    
    void reset() {
        numberPlate.setText("");
        make.setText("");
        model.setText("");
        yearOfManufacturing.setText("");
        dailyRentalFee.setText("");
        setDatePickerWithLocaldate(lastService, LocalDate.now().plusDays(1));
        inService.setSelected(false);
        photo.removeAll();
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
            formatter.setPlaceholderCharacter('_');
            formatter.setValidCharacters(validChars);
        } catch (ParseException ex) {
            Logger.getLogger(NewCard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formatter;
    }
    
    private void photoSelection(ActionEvent e) {
        int returnValue = fileChooser.showDialog(null, "Fotó kiválasztása");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
            presentPhoto(Boolean.TRUE, fileChooser.getSelectedFile().getCanonicalFile().toString());
            } catch (IOException ex) {
                Logger.getLogger(NewCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        repaint();
    }
}
