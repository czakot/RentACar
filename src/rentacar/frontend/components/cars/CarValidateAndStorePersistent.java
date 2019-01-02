/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import rentacar.frontend.components.DetailsMode;
import static rentacar.frontend.components.cars.BaseCard.FILE_SEPARATOR;

/**
 *
 * @author czakot
 */
public class CarValidateAndStorePersistent {
    
    CarsPanel carsPanel;
    private Boolean successful;
    final JDialog warningMessages;
    private final static String TARGET_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "photos_selected";

    public CarValidateAndStorePersistent(String[] carValues,DetailsMode mode,CarsPanel carsPanel) {
        this.carsPanel = carsPanel;
        successful = true;
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append("1. A teszt első sora\n");
        textArea.append("2. A teszt második sora\n");
        Dimension preferredSize = carsPanel.getSize();
        preferredSize.width = preferredSize.width * 3 / 5;
        preferredSize.height = preferredSize.height / 3;
        textArea.setPreferredSize(preferredSize);
        JButton okButton = new JButton("Rendben");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warningMessages.dispose();
            }
        });
        dialogPanel.add(textArea,BorderLayout.CENTER);
        dialogPanel.add(okButton,BorderLayout.SOUTH);
        warningMessages = new JDialog((JFrame)(carsPanel.getTopLevelAncestor()), "Figyelmeztetés(ek) hibás/hiányos autóadatokra", true);
        warningMessages.getContentPane().add(dialogPanel);
        warningMessages.setLocationRelativeTo((JFrame)(carsPanel.getTopLevelAncestor()));
        warningMessages.pack();
        warningMessages.setVisible(true);
    }
    
    public Boolean isSuccessful() {
        return successful;
    }

}
