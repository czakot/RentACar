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
    
    @Override
    void modifyContentEditables() {
        for (int i = 0; i < 4; i++) {
            disableEditingOnTextField((JTextField)(content.getComponent(i)));
        }
    }
    
    void setEditorContent(String[] text) {
        for (int i = 0; i < 5; i++) {
            firstFiveText[i].setText(text[i]);
        }
        String[] dateElements = text[5].split("-");
        lastService.getModel().setYear(Integer.valueOf(dateElements[0]));
        lastService.getModel().setMonth(Integer.valueOf(dateElements[1]));
        lastService.getModel().setDay(Integer.valueOf(dateElements[2]));
        inService.setText(text[6]);
        inService.setSelected(text[6].equals("igen"));
        photoSelector.setText("Fotó választás");
        
        resizePhotoHolder();
        if (text[7].equals("van")) {
            loadInPhoto(text[0]);
        } else {
            photo.removeAll();
        }
    }
}
