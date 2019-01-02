/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static rentacar.RentACar.BACKGROUND_DISABLED;
import static rentacar.RentACar.FILE_SEPARATOR;
import rentacar.utility.ShrinkIcon;

/**
 *
 * @author czakot
 */
public class BaseCard extends JPanel{

    final JPanel content;
    final JPanel photo;
    final CarDetails carDetails;
    final static String[] TITLE_STRINGS = {"Rendszám:","Márka:","Típus:","Évjárat:","Bérleti díj/nap:","Utolsó szerviz:","Most szervizben:","Fotó:"};
    
    public BaseCard(CarDetails carDetails) {
        this.carDetails = carDetails;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        content = new JPanel(new GridBagLayout()) ;
        placeTitlesOnContent();
        
        photo = new JPanel(new BorderLayout());
        photo.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

        add(content);
        add(photo);
    }

    void disableEditingOnTextField(JTextField textField) {
        textField.setOpaque(true);
        textField.setBackground(BACKGROUND_DISABLED);
//        textField.setEditable(false);
        textField.setFocusable(false);
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
    
    void presentPhoto(Boolean doIt,String photoPath) {
//            StretchIcon image = new StretchIcon(photoPath,true);
        Dimension photoPreferredSize = content.getSize();
        photoPreferredSize.width = photoPreferredSize.height * 4 / 3;
        photo.setPreferredSize(photoPreferredSize);        
        if (doIt) {
            ShrinkIcon image = new ShrinkIcon(photoPath,true);
            photo.add(new JLabel("",image,JLabel.CENTER),BorderLayout.CENTER);
        } else {
            photo.removeAll();
        }
        carDetails.validate();
    }
    
    String fromSelectedJpgs(String filename) {
        return "photos_selected" + FILE_SEPARATOR + filename.toLowerCase() + ".jpg";
    }
}
