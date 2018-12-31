/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import rentacar.utility.ShrinkIcon;

/**
 *
 * @author czakot
 */
public abstract class BaseCard extends JPanel{

    JPanel content;
    JPanel photo;
    JPanel buttons;
    final static String[] TITLE_STRINGS = {"Rendszám:","Márka:","Típus:","Évjárat:","Bérleti díj/nap:","Utolsó szerviz:","Most szervizben:","Fotó:"};
    final static Color BACKGROUND_DISABLED = new Color(214, 217, 223);
    final static Color BACKGROUND_ENABLED = new Color(255, 255, 255);
    
    public BaseCard() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        content = new JPanel(new GridBagLayout());
        setupContent();
        
        photo = new JPanel(new BorderLayout());
        photo.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        add(content);
        add(photo);
    }

    abstract void setupContent();
    
    void disableEditingOnTextField(JTextField textField) {
        textField.setOpaque(true);
        textField.setBackground(BACKGROUND_DISABLED);
        textField.setEditable(false);
    }

    void placeTitlesOnContent() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        for (gbc.gridy = 0; gbc.gridy < 8; gbc.gridy++) {
            content.add(new JLabel(TITLE_STRINGS[gbc.gridy] + "  "),gbc); // cimkék: rendszámtól képig
        }
    }

    void modifyContentEditables() {
    }
    
    void resizePhotoHolder() {
        Dimension photoPreferredSize = content.getSize();
        photoPreferredSize.width = photoPreferredSize.height * 4 / 3;
        photo.setPreferredSize(photoPreferredSize);        
    }
    
    void loadInPhoto(String numberPlate) {
//          StretchIcon image = new StretchIcon("photos/" + details[0].toLowerCase() + ".jpg");
            ShrinkIcon image = new ShrinkIcon("photos/" + numberPlate.toLowerCase() + ".jpg",true);
            photo.add(new JLabel("",image,JLabel.CENTER),BorderLayout.CENTER);
    }
}
