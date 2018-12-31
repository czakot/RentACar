/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import rentacar.frontend.components.DetailsMode;

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
    
    void refreshCarDetails(String[] details) {
        carDetails.refreshCarDetails(details);
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
}
