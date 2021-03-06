/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.cars;

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
    private ActionListener ActionListenerForSave;
    
    public CarsButtons(CarsPanel carsPanel) {
        this.carsPanel = carsPanel;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        newCar = new JButton("Új autó felvétele");
        newCar.addActionListener(this::createNewCar);
        add(newCar);
        
        modifyCar = new JButton("Autó adatainak módosítása");
        modifyCar.setEnabled(false);
        modifyCar.addActionListener(this::editSelectedCar);
        add(modifyCar);
        
        save = new JButton("Mentés");
        save.setVisible(false);
        add(save);
        
        discard = new JButton("Eldobás");
        discard.setVisible(false);
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carsPanel.discardNewOrEditor();
            }
        });
        add(discard);
    }
    
    private void createNewCar(ActionEvent e) {
        ActionListenerForSave = carsPanel::saveNew;
        carsPanel.createNewCar();
    }
    
    private void editSelectedCar(ActionEvent e) {
        ActionListenerForSave = carsPanel::saveEdited;
        carsPanel.editSelectedCar();
    }
    
    private void removeSaveActionListeners() {
        for (ActionListener actionListener : save.getActionListeners()) {
            save.removeActionListener(actionListener);
        }
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
        save.addActionListener(ActionListenerForSave);
        discard.setVisible(true);
    }

    void disableSaveDiscard() {
        save.setVisible(false);
        discard.setVisible(false);
        removeSaveActionListeners();
    }
}
