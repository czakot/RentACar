/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.customers;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import rentacar.backend.entities.Customer;
import static rentacar.backend.entities.Customer.VALID_CHARS_FOR_PHONE_NUMBER;
import rentacar.utility.MyTextField;

/**
 *
 * @author czakot
 */
public class NewCard extends BaseCard {

    
    final MyTextField idCustomer;
    final MyTextField name;
    final MyTextField address;
    final MyTextField phoneNumber;
    
    public static final String TOOLTIP_ID = "Az adatbázis által generált egyéni azonosító";
    public static final String TOOLTIP_NAME = "Név: tetszőleges szöveg, de legalább 3 karakter hosszú";
    public static final String TOOLTIP_ADDRESS = "Cím: tetszőleges szöveg";
    public static final String TOOLTIP_PHONE = "Telefonszám: számjegyek, '+', '-', '(', ')' és SPACE felhasználásával"; // lásd Customer-ben
    
    public NewCard(CustomerDetails customerDetails) {
        super(customerDetails);
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        idCustomer = new MyTextField("");
        idCustomer.setPreferredSize(new Dimension(303, 27));
        disableEditingOnTextField(idCustomer);
        idCustomer.setToolTipText(TOOLTIP_ID);
        content.add(idCustomer,gbc);
        
        gbc.gridy++;
        name = new MyTextField("");
        name.setPreferredSize(new Dimension(303, 27));
        name.setToolTipText(TOOLTIP_NAME);
        content.add(name,gbc);
            
        gbc.gridy++;
        address = new MyTextField("");
        address.setPreferredSize(new Dimension(303, 27));
        address.setToolTipText(TOOLTIP_ADDRESS);
        content.add(address,gbc);
            
        gbc.gridy++;
        phoneNumber = new MyTextField("");
        phoneNumber.setPreferredSize(new Dimension(303, 27));
        phoneNumber.setToolTipText(TOOLTIP_PHONE);
        
        InputVerifier phoneVerifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                Boolean inputError = false;
                String text = ((MyTextField)input).getText();
                for (int i = 0; i < text.length() && !inputError; i++) {
                    inputError = (VALID_CHARS_FOR_PHONE_NUMBER.indexOf(text.charAt(i)) < 0);
                }
                return !inputError;
            }
        };
        phoneNumber.setInputVerifier(phoneVerifier);

        content.add(phoneNumber,gbc);
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
    }
    
    void reset() {
        idCustomer.setText("");
        name.setText("");
        address.setText("");
        phoneNumber.setText("");
    }
    
    Customer getCustomer() {
        Customer customer = new Customer();
        customer.setName(name.getText());
        customer.setAddress(address.getText());
        customer.setPhone(phoneNumber.getText());
        
        return customer;
    }
}
