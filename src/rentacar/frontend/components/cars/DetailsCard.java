/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

/**
 *
 * @author czakot
 */
public class DetailsCard extends BaseCard {
    
    @Override
    protected void setupContent() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridy = 0;
        gbc.gridx = 1;
        JTextField textField;
        for (int i = 0; i < 7;++i) {
            textField = new JTextField("");
            disableEditingOnTextField(textField);
            content.add(textField,gbc);
            gbc.gridy++;
        }

        placeTitlesOnContent();
    }
    
    String[] getDetailsContent() {
        String[] text = new String[8];
        for (int i = 0; i < 5; i++) {
            text[i] = ((JTextField)(content.getComponent(i))).getText();
        }
        text[5] = ((JTextField)(content.getComponent(5))).getText();
        text[6] = ((JTextField)(content.getComponent(6))).getText();
        text[7] = ((photo.getComponentCount() == 0) ? "nincs" : "van");
        return text;
    }
    
    void refreshDetailsContent(String[] details) {
        for (int i = 0; i < 7;++i) {
            ((JTextField)(content.getComponent(i))).setText(details[i]);
        }
        
        resizePhotoHolder();
        if (details[7].equals("van")) {
            loadInPhoto(details[0]);
        }
    }
}
