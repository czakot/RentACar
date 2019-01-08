/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.customers;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import rentacar.backend.entities.Customer;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class CustomersPanel  extends JPanel {

    private final CustomersList customersList;
    private final CustomerDetails customerDetails;
    private final CustomersButtons customersButtons;
    
    public CustomersPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        customersButtons = new CustomersButtons(this);
        customerDetails = new CustomerDetails(this);
        customersList = new CustomersList(this);
        
        add(customersList);
        add(customerDetails);
        add(customersButtons);
    }
    
    void refreshCustomerDetails(Customer customer) {
        customerDetails.refreshCarDetails(customer);
    }
    
    Customer getSelectedCustomer() {
        return customersList.getSelectedCustomer();
    }
    
    void createNewCustomer() {
        disableListNew();
        customerDetails.switchMode(DetailsMode.NEW);
        customersButtons.enableSaveDiscard();
    }
    
    private void enableListNew() {
        customersList.enableRowSelectionChange();
        customersButtons.enableNew();
    }
    
    private void disableListNew() {
        customersList.disableRowSelectionChange();
        customersButtons.disableNew();
    }
    
    void discardNew() {
        customersButtons.disableSaveDiscard();
        customerDetails.discard();
        enableListNew();
    }
    
    void saveNew() {
        Customer customer = ((NewCard)customerDetails.getTopCard()).getCustomer();
        customersButtons.disableSaveDiscard();
        if (GuiManager.storeCustomer(customer)) {
            resetCarGui();
        } else {
            doWhenNotSaved();
        }
    }
    
    private void doWhenNotSaved() {
        JOptionPane.showMessageDialog(null, GuiManager.getServiceMessage(),"Service üzenet(ek)", JOptionPane.WARNING_MESSAGE);
        customersButtons.enableSaveDiscard();
    }
        
    private void resetCarGui() {
        customerDetails.discard();
        customerDetails.switchMode(DetailsMode.EMPTY);
        customersButtons.enableNew();
        customersList.updateCarsTable();
    }
}
