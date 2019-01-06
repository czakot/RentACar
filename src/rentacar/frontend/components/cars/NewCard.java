/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import org.jdatepicker.*;
import rentacar.backend.entities.BareCar;
import rentacar.utility.MyFormattedTextField;
import rentacar.utility.MyTextField;

/**
 *
 * @author czakot
 */
public class NewCard extends BaseCard {

    final MyFormattedTextField numberPlate;
    final MyTextField make;
    final MyTextField model;
    final MyFormattedTextField yearOfManufacturing;
    final MyFormattedTextField dailyRentalFee;
    final JDatePicker lastService;
    final JCheckBox inService;
    final JButton photoSelector;
    Boolean initialPhoto;
    final JFileChooser fileChooser;
    String choosenPhotoFullPath;
    final static String PHOTO_SOURCES_START_PATH = System.getProperty("user.dir") + File.separator + "photos_container";
    public static final int THIS_YEAR = LocalDate.now().getYear();
    public static final String VALID_CHARS_FOR_NUMBER_PLATE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String MASK_FOR_NUMBER_PLATE = "UUU###";
    public static final String TOOLTIP_NUMBER_PLATE = "Rendszám: BBB### (BBB: betű, #: számjegy) alakban";
    public static final String TOOLTIP_MAKE = "Márka: tetszőleges szöveg";
    public static final String TOOLTIP_MODEL = "Típus: tetszőleges szöveg";
    public static final String TOOLTIP_YEAR = "Évjárat: 4 számjeggyel írva [1900-tól " + THIS_YEAR + "-ig]";
    public static final String TOOLTIP_DAILY = "Bérleti díj összege: legyen nem negatív egész";
    
    public NewCard(CarDetails carDetails) {
        super(carDetails);
        initialPhoto = false;
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JPG formátumú képek", "jpg"));
        choosenPhotoFullPath = null;
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        numberPlate = new MyFormattedTextField(createFormatter(MASK_FOR_NUMBER_PLATE,VALID_CHARS_FOR_NUMBER_PLATE));
        content.add(numberPlate,gbc);
        
        gbc.gridy++;
        make = new MyTextField("");
        content.add(make,gbc);
            
        gbc.gridy++;
        model = new MyTextField("");
        content.add(model,gbc);
            
        gbc.gridy++;
        yearOfManufacturing = new MyFormattedTextField();
        NumberFormatter defaultYearFormatter = new NumberFormatter(new DecimalFormat("#;"));
        defaultYearFormatter.setValueClass(Integer.class);
        DefaultFormatterFactory yearFactory = new DefaultFormatterFactory(defaultYearFormatter);
        yearOfManufacturing.setFormatterFactory(yearFactory);
        resetDefaultYearOfManufacturing();
        
        InputVerifier yearVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JFormattedTextField ftf = (JFormattedTextField)input;
                Boolean inputError = false;
                try {
                    yearOfManufacturing.commitEdit();
                } catch (ParseException ex) {
                    inputError = true;
                }
                int ftfint = (Integer)(ftf.getValue());
                inputError = inputError || ftfint < 1900 || ftfint > THIS_YEAR;
                return !inputError;
            }
        };
        yearOfManufacturing.setInputVerifier(yearVerifier);

        content.add(yearOfManufacturing,gbc);
        
        gbc.gridy++;
        dailyRentalFee = new MyFormattedTextField();
        // esetleg lehetne az eddigi átlag/minimum/maximum bérleti díjat adni kezdőértéknek
        NumberFormatter defaultDailyFormatter = new NumberFormatter(new DecimalFormat("#;"));
        defaultDailyFormatter.setValueClass(Integer.class);
        DefaultFormatterFactory dailyFactory = new DefaultFormatterFactory(defaultDailyFormatter);
        dailyRentalFee.setFormatterFactory(dailyFactory);
        resetDefaultDailyRentalFee();
        
        InputVerifier dailyVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JFormattedTextField ftf = (JFormattedTextField)input;
                Boolean inputError = false;
                try {
                    dailyRentalFee.commitEdit();
                } catch (ParseException ex) {
                    inputError = true;
                }
                int ftfint = (Integer)(ftf.getValue());
                inputError = inputError || ftfint < 0;
                return !inputError;
            }
        };
        dailyRentalFee.setInputVerifier(dailyVerifier);
        
        dailyRentalFee.setToolTipText(TOOLTIP_DAILY);
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
            numberPlate.setToolTipText(TOOLTIP_NUMBER_PLATE);
            make.setToolTipText(TOOLTIP_MAKE);
            model.setToolTipText(TOOLTIP_MODEL);
            yearOfManufacturing.setToolTipText(TOOLTIP_YEAR);
        }
    }
    
    void reset() {
        numberPlate.setText("");
        make.setText("");
        model.setText("");
        resetDefaultYearOfManufacturing();
        resetDefaultDailyRentalFee();
        setDatePickerWithLocaldate(lastService, LocalDate.now().plusDays(1)); // holnap => nem érvényes => kelljen szerkeszteni
        inService.setSelected(false);
        photo.removeAll();
        initialPhoto = false;
        choosenPhotoFullPath = null;
    }
    
    private void resetDefaultYearOfManufacturing() {
        yearOfManufacturing.setValue(0);
        yearOfManufacturing.setText("");
    }
    
    private void resetDefaultDailyRentalFee() {
        dailyRentalFee.setValue(-1);
        dailyRentalFee.setText("");
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
    
    private void photoSelection(ActionEvent e) {
        fileChooser.setCurrentDirectory(new File(PHOTO_SOURCES_START_PATH));
        int returnValue = fileChooser.showDialog(null, "Fotó kiválasztása");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
            choosenPhotoFullPath = fileChooser.getSelectedFile().getCanonicalFile().toString();
            } catch (IOException ex) {
                Logger.getLogger(NewCard.class.getName()).log(Level.SEVERE, null, ex);
            }
            presentPhoto(Boolean.TRUE, choosenPhotoFullPath);
        }
        this.getTopLevelAncestor().validate();
    }
    
    BareCar getBareCar() {
        BareCar bareCar = new BareCar(numberPlate.getText());
        bareCar.setMake(make.getText());
        bareCar.setModel(model.getText());
        bareCar.setYearOfManufacturing((Integer)yearOfManufacturing.getValue());
        bareCar.setDailyRentalFee((Integer)dailyRentalFee.getValue());
        bareCar.setLastService(LocalDate.of(lastService.getModel().getYear(),
                                            lastService.getModel().getMonth()+1, 
                                            lastService.getModel().getDay()));
        bareCar.setInService(inService.isSelected());
        bareCar.setPhoto(initialPhoto);
        bareCar.setChoosenPhotoPath(choosenPhotoFullPath);
        
        return bareCar;
    }
}
