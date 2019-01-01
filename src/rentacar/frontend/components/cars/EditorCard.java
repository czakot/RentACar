/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import javax.swing.JTextField;

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
    
    void setEditorContent(String[] text) {
        numberPlate.setText(text[0]);
        make.setText(text[1]);
        model.setText(text[2]);
        yearOfManufacturing.setText(text[3]);
        dailyRentalFee.setText(text[4]);
        String[] dateElements = text[5].split("-");
        lastService.getModel().setYear(Integer.valueOf(dateElements[0]));
        lastService.getModel().setMonth(Integer.valueOf(dateElements[1]));
        lastService.getModel().setDay(Integer.valueOf(dateElements[2]));
        inService.setText(text[6]);
        inService.setSelected(text[6].equals("igen"));
        photoSelector.setText("Fotó választás");
        
        presentPhoto(text[7].equals("van"), fromSelectedJpgs(numberPlate.getText()));
    }
}
