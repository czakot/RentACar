/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author czakot
 */
public class CarsButtons extends JPanel {

    private final CarsPanel carsPanel;
    private final JButton newCar;
    private final JButton modifyCar;
    private final JButton save;
    private final JButton discard;
    private Boolean previousEnableStateModifyCard;
    
    public CarsButtons(CarsPanel carsPanel) {
        this.carsPanel = carsPanel;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        newCar = new JButton("Új autó felvétele");
        newCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carsPanel.createNewCar();
            }
        });
        add(newCar);
        
        modifyCar = new JButton("Autó adatainak módosítása");
        modifyCar.setEnabled(false);
        modifyCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carsPanel.editSelectedCar();
            }
        });
        add(modifyCar);
        
        save = new JButton("Mentés");
        save.setVisible(false);
        save.setEnabled(false);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carsPanel.saveNewOrEditor();
            }
        });
        add(save);
        
        discard = new JButton("Eldobás");
        discard.setVisible(false);
        discard.setEnabled(false);
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carsPanel.discardNewOrEditor();
            }
        });
        add(discard);
    }
    
    void enableModify() {
        modifyCar.setEnabled(true);
    }
    
    void disableModify() {
        modifyCar.setEnabled(false);
    }
    
    void enableNew() {
        newCar.setEnabled(true);
    }
    
    void enableNewModify() {
        newCar.setEnabled(true);
        modifyCar.setEnabled(previousEnableStateModifyCard);
    }
    
    void disableNewModify() {
        newCar.setEnabled(false);
        previousEnableStateModifyCard = modifyCar.isEnabled();
        modifyCar.setEnabled(false);
    }
    
    void enableSaveDiscard() {
        save.setVisible(true);
        save.setEnabled(true);
        discard.setVisible(true);
        discard.setEnabled(true);
    }

    void disableSaveDiscard() {
        save.setVisible(false);
        save.setEnabled(false);
        discard.setVisible(false);
        discard.setEnabled(false);
    }
}
