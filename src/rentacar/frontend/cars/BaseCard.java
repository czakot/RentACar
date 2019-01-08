/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.cars;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static rentacar.RentACar.BACKGROUND_DISABLED;
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
    public final static String PHOTO_SELECTED_PATH = System.getProperty("user.dir") + File.separator + "photos_selected";
    
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
        photo.removeAll();
        if (doIt) {
            BufferedImage preImage = null;
            try {
                preImage = ImageIO.read(new File(photoPath));
            } catch (IOException ex) {
                Logger.getLogger(BaseCard.class.getName()).log(Level.SEVERE, null, ex);
            }
            ShrinkIcon image = new ShrinkIcon(preImage,true);
            JLabel photoLabel = new JLabel("",image,JLabel.CENTER);
            photoLabel.setToolTipText(photoPath);
            photo.add(photoLabel,BorderLayout.CENTER);
        }
        carDetails.validate();
    }
    
    public static String fromSelectedJpgs(String filename) {
        return PHOTO_SELECTED_PATH + File.separator + filename.toLowerCase() + "_0.jpg";
    }
}
