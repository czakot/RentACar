/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import rentacar.backend.entities.Customer;
import rentacar.backend.entities.Rent;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class DetailsCard extends BaseCard {

    private final JTextField[] textField;
    
    public DetailsCard(RentDetails rentDetails) {
        super(rentDetails);
        textField = new JTextField[8];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        for (gbc.gridy = 0; gbc.gridy < 8;gbc.gridy++) {
            textField[gbc.gridy] = new JTextField("");
            disableEditingOnTextField(textField[gbc.gridy]);
            content.add(textField[gbc.gridy],gbc);
        }
    }
    
    void refreshValues(Rent rent) {
        
        textField[0].setText(rent.getIdRent().toString());
        Customer customer = GuiManager.getCustomer(Integer.toString(rent.getIdCustomer()));
        textField[1].setText(customer.getName() + " (ID: " + customer.getIdCustomer() + ")");
        textField[1].setToolTipText("Cím: " + customer.getAddress() + "\nTelefon: " + customer.getPhone());
        textField[2].setText(rent.getNumberPlate());
        textField[3].setText(rent.getBeginningDate().toString());
        textField[4].setText(rent.getExpectedReturnDate().toString());
        textField[5].setText((rent.getReturnDate() == null) ? "Folyamatban lévő bérlés" : rent.getReturnDate().toString());
        textField[6].setText(rent.getDailyRentalFee().toString());
        textField[7].setText(rent.getPaidFee().toString());
    }
}
