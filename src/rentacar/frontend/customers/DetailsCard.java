/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.customers;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import rentacar.backend.entities.Customer;

/**
 *
 * @author czakot
 */
public class DetailsCard extends BaseCard {

    private final JTextField[] textField;
    
    public DetailsCard(CustomerDetails customerDetails) {
        super(customerDetails);
        textField = new JTextField[4];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        for (gbc.gridy = 0; gbc.gridy < 4;gbc.gridy++) {
            textField[gbc.gridy] = new JTextField("");
            disableEditingOnTextField(textField[gbc.gridy]);
            content.add(textField[gbc.gridy],gbc);
        }
    }
    
    void refreshValues(Customer customer) {
        
        textField[0].setText(Integer.toString(customer.getIdCustomer()));
        textField[1].setText(customer.getName());
        textField[2].setText(customer.getAddress());
        textField[3].setText(customer.getPhone());
    customerDetails.validate();
    }
}
