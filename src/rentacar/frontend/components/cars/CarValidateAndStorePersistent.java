/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import rentacar.backend.entities.Car;
import rentacar.frontend.components.DetailsMode;
import static rentacar.frontend.components.cars.BaseCard.FILE_SEPARATOR;

/**
 *
 * @author czakot
 */
public class CarValidateAndStorePersistent {
    
    private final CarsPanel carsPanel;
    private final String numberPlate;
    private final String make;
    private final String model;
    private final String yearOfManufacturing;
    private final String dailyRentalFee;
    private final String lastService;
    private final String inService;
    private final String photo;
    private final String photoPath;
    private final Car car;
    private Boolean successful;
    final JDialog warningMessages;
    private final static String TARGET_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "photos_selected";
    private final static String[] VALUE_TITLES = {"Rendszám: ","Márka: ","Típus: ","Évjárat: ","Napi bérleti díj: ","Utoljára szervizben: ",
                                                  "Fotó: ","Fotó rögzítése: "};

    public CarValidateAndStorePersistent(String[] values,DetailsMode mode,CarsPanel carsPanel) {
        this.carsPanel = carsPanel;
        car = null;
        numberPlate = values[0];
        make = values[1];
        model = values[2];
        yearOfManufacturing = values[3];
        dailyRentalFee = values[4];
        lastService = values[5];
        inService = values[6];
        photo = values[7];
        photoPath = values[8];
        successful = false;
        
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        for (int i = 0; i < 9; i++) {
            textArea.append(VALUE_TITLES[i] + values[i] + "\n");
        }
        Dimension preferredSize = carsPanel.getSize();
        preferredSize.width = preferredSize.width * 3 / 5;
        preferredSize.height = preferredSize.height / 3;
        textArea.setPreferredSize(preferredSize);
        JButton okButton = new JButton("Rendben");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warningMessages.dispose();
            }
        });
        dialogPanel.add(textArea,BorderLayout.CENTER);
        dialogPanel.add(okButton,BorderLayout.SOUTH);
        warningMessages = new JDialog((JFrame)(carsPanel.getTopLevelAncestor()), "", true);
        warningMessages.getContentPane().add(dialogPanel);
        warningMessages.setLocationRelativeTo((JFrame)(carsPanel.getTopLevelAncestor()));
        warningMessages.pack();
        warningMessages.setVisible(true);
        
        Boolean valid = true;
        switch (mode) {
            case NEW:
                warningMessages.setTitle("Rögzíteni kívánt autó adatainak ellenőrzése");
                
                validationsOnlyForNew();
                commonValidationForNewAndEditor();
                updatePhoto();
                
                if (valid) {
                    successful = insertIntoDataBase();
                }
                break;
            case EDITOR:
                warningMessages.setTitle("Módosítani kívánt autóadatok ellenőrzése");

                commonValidationForNewAndEditor();
                updatePhoto();

                if (valid) {
                    successful = updateInDataBase();
                }
                break;
        }
    }
    
    public Boolean isSuccessful() {
        return successful;
    }
    
    private void validationsOnlyForNew() {
        validateNumberPlate(numberPlate);
        validateMake(make);
        validateModel(model);
        validateYearOfManufacturing(yearOfManufacturing);
    }
    
    private void commonValidationForNewAndEditor() {
        validateDailyRentalFee(dailyRentalFee);
        validateLastService(lastService);
        validateInservice(inService,lastService,yearOfManufacturing);
    }
    
    private void updatePhoto() {
                if (photo.equals("új fotó")) {
                    if (photoExists(photoPath)) {
                        if (copyPhotoIntoSelected(photoPath)) {
                            
                        }
                    }
                }
    }
    
    private Boolean insertIntoDataBase() {
        return null;
    }
    
    private Boolean updateInDataBase() {
        return null;
    }

    private void validateNumberPlate(String numberPlate) {
        Service.
    }

    private void validateMake(String make) {
    }

    private void validateModel(String model) {
    }

    private void validateYearOfManufacturing(String value) {
    }

    private void validateDailyRentalFee(String value) {
    }

    private void validateLastService(String value) {
    }

    private void validateInservice(String value, String value0, String value1) {
    }

    private void validatePhotoExists(String value, String value0) {
    }

    private boolean photoExists(String value) {
        return false;
    }

    private boolean copyPhotoIntoSelected(String value) {
        return false;
    }

}
