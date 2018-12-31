/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
            ((JTextField)(content.getComponent(i))).setText(text[i]);
        }
        ((JButton)(content.getComponent(5))).setText(text[5]);
        ((JCheckBox)(content.getComponent(6))).setText(text[6]);
        ((JButton)(content.getComponent(7))).setText("Fotó választás");
        
        resizePhotoHolder();
        if (text[7].equals("van")) {
            loadInPhoto(text[0]);
        } else {
            photo.removeAll();
        }
    }
}
