/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static rentacar.RentACar.BACKGROUND_DISABLED;

/**
 *
 * @author czakot
 */
public class BaseCard extends JPanel{

    final JPanel content;
    final RentDetails rentDetails;
    final static String[] TITLE_STRINGS = {"ID:", "Ügyfélazonosító:", "Rendszám:", "Kezdés:", "Tervezett befejezés:", "Befejezés:", "Napi bérleti díj:", "Fizetett:"};
    
    public BaseCard(RentDetails rentDetails) {
        this.rentDetails = rentDetails;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        content = new JPanel(new GridBagLayout()) ;
        placeTitlesOnContent();
        
        add(content);
    }

    void disableEditingOnTextField(JTextField textField) {
        textField.setOpaque(true);
        textField.setBackground(BACKGROUND_DISABLED);
        textField.setFocusable(false);
    }

    private void placeTitlesOnContent() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        for (gbc.gridy = 0; gbc.gridy < 8; gbc.gridy++) {
            content.add(new JLabel(TITLE_STRINGS[gbc.gridy] + "  "),gbc); // cimkék: idRent-től paidFee-ig
        }
    }

    void modifyContentEditables() {
    }
}
