/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rentacar.backend.entities.BareCar;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class CarsPanel extends JPanel {
    private final CarsList carsList;
    private final CarDetails carDetails;
    private final CarsButtons carsButtons;

    public CarsPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        carsButtons = new CarsButtons(this);
        carDetails = new CarDetails(this);
        carsList = new CarsList(this);
        
        add(carsList);
        add(carDetails);
        add(carsButtons);
    }
    
    void refreshCarDetails(BareCar car) {
        carDetails.refreshCarDetails(car);
    }
    
    BareCar getSelectedCar() {
        return carsList.getSelectedCar();
    }
    
    void enableModifyCarButton() {
        carsButtons.enableModify();
    }
    
    void disableModifyCarButton() {
        carsButtons.disableModify();
    }
    
    void createNewCar() {
        disableListNewModify();
        carDetails.switchMode(DetailsMode.NEW);
        carsButtons.enableSaveDiscard();
    }
    
    void editSelectedCar() {
        disableListNewModify();
        carDetails.switchMode(DetailsMode.EDITOR);
        carsButtons.enableSaveDiscard();
    }
    
    private void enableListNewModify() {
        carsList.enableRowSelectionChange();
        carsButtons.enableNewModify();
    }
    
    private void disableListNewModify() {
        carsList.disableRowSelectionChange();
        carsButtons.disableNewModify();
    }
    
    void discardNewOrEditor() {
        carsButtons.disableSaveDiscard();
        carDetails.discard();
        enableListNewModify();
    }
    
    void saveNew(ActionEvent e) {
        save(true);
    }

    void saveEdited(ActionEvent e) {
        save(false);
    }
    
    private void save(Boolean trueIfNewFalseIfEdit) {
        BareCar car = ((NewCard)carDetails.getTopCard()).getBareCar();
        carsButtons.disableSaveDiscard();
        if (trueIfNewFalseIfEdit ? GuiManager.storeCar(car) : GuiManager.updateCar(car)) {
            resetCarGui();
        } else {
            doWhenNotSaved();
        }
    }
    
    private void doWhenNotSaved() {
        JOptionPane.showMessageDialog(null, GuiManager.getServiceMessage(),"Service üzenet(ek)", JOptionPane.WARNING_MESSAGE);
        carsButtons.enableSaveDiscard();
    }
        
    private void resetCarGui() {
        carDetails.discard();
        carDetails.switchMode(DetailsMode.EMPTY);
        carsButtons.enableNew();
        carsList.updateCarsTable();
    }
}

