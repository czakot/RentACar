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
    
    JTextField[] textField;
    
    @Override
    protected void setupContent() {
        textField = new JTextField[8];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        for (gbc.gridy = 0; gbc.gridy < 8;gbc.gridy++) {
            textField[gbc.gridy] = new JTextField("");
            disableEditingOnTextField(textField[gbc.gridy]);
            content.add(textField[gbc.gridy],gbc);
        }

        placeTitlesOnContent();
    }
    
    String[] getDetailsContent() {
        String[] text = new String[8];
        for (int i = 0; i < 8; i++) {
            text[i] = textField[i].getText();
        }
        return text;
    }
    
    void refreshDetailsContent(String[] details) {
        for (int i = 0; i < 8;++i) {
            textField[i].setText(details[i]);
        }
        
        resizePhotoHolder();
        if (details[7].equals("van")) {
            loadInPhoto(details[0]);
        }
    }
}
