/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rentacar.backend.entities.Rent;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class RentsPanel extends JPanel {
    private final RentsList rentsList;
    private final RentDetails rentDetails;
    private final RentsButtons rentsButtons;

    public RentsPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        rentsButtons = new RentsButtons(this);
        rentDetails = new RentDetails(this);
        rentsList = new RentsList(this);
        
        add(rentsList);
        add(rentDetails);
        add(rentsButtons);
    }
    
    void refreshRentDetails(Rent rent) {
        rentDetails.refreshRentDetails(rent);
    }
    
    Rent getSelectedRent() {
        return rentsList.getSelectedRent();
    }
    
    void enableFinishRentButton() {
        rentsButtons.enableFinish();
    }
    
    void disableFinishRentButton() {
        rentsButtons.disableFinish();
    }
    
    void createNewRent() {
        disableListNewFinish();
        rentDetails.switchMode(DetailsMode.NEW);
        rentsButtons.enableSaveDiscard();
    }
    
    void finishSelectedRent() {
        disableListNewFinish();
        rentDetails.switchMode(DetailsMode.FINISHER);
        rentsButtons.enableSaveDiscard();
    }
    
    private void enableListNewFinish() {
        rentsList.enableRowSelectionChange();
        rentsButtons.enableNewFinish();
    }
    
    private void disableListNewFinish() {
        rentsList.disableRowSelectionChange();
        rentsButtons.disableNewFinish();
    }
    
    void discardNewOrFinish() {
        rentsButtons.disableSaveDiscard();
        rentDetails.discard();
        enableListNewFinish();
    }
    
    void saveNew(ActionEvent e) {
        save(true);
    }

    void saveFinished(ActionEvent e) {
        save(false);
    }
    
    private void save(Boolean trueIfNewFalseIfFinish) {
        Rent rent = ((NewCard)rentDetails.getTopCard()).getRent();
        rentsButtons.disableSaveDiscard();
        if (trueIfNewFalseIfFinish ? GuiManager.storeRent(rent) : GuiManager.updateRent(rent)) {
            resetRentGui();
        } else {
            doWhenNotSaved();
        }
    }
    
    private void doWhenNotSaved() {
        JOptionPane.showMessageDialog(null, GuiManager.getServiceMessage(),"Service üzenet(ek)", JOptionPane.WARNING_MESSAGE);
        rentsButtons.enableSaveDiscard();
    }
        
    private void resetRentGui() {
        rentDetails.discard();
        rentDetails.switchMode(DetailsMode.EMPTY);
        rentsButtons.enableNew();
        rentsList.updateRentsTable();
    }
}

