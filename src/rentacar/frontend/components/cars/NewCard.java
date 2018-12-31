/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author czakot
 */
public class NewCard extends BaseCard {
    
    JTextField[] firstFiveText;
    JButton lastService;
    JCheckBox inService;
    JButton photoSelector;
    
    @Override
    void setupContent() {
        firstFiveText = new JTextField[5];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        for (gbc.gridy = 0; gbc.gridy < 5;gbc.gridy++) {
            firstFiveText[gbc.gridy] = new JTextField("");         // rendszám, márka, típus, évjárat, napi bérleti díj
            content.add(firstFiveText[gbc.gridy],gbc);
        }
        
        // gbc.gridy = 5;
        lastService = new JButton("éééé-hh-nn");
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
        content.add(photoSelector,gbc);

        placeTitlesOnContent();
        modifyContentEditables();
    }
    
    void setToolTipForNumberPlate(String toolTip) {
        ((JTextField)(content.getComponent(0))).setToolTipText(toolTip);
    }
    
    void reset() {
        for (int i = 0; i < 5; i++) {
            firstFiveText[i].setText("");
        }
        lastService.setText("");
        inService.setSelected(false);
        photo.removeAll();
    }
}
