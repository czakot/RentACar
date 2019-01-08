/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import rentacar.backend.entities.Rent;

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
        
        textField[0].setText(rent.getNumberPlate());
        textField[1].setText(rent.getMake());
        textField[2].setText(rent.getModel());
        textField[3].setText(Integer.toString(rent.getYearOfManufacturing()));
        textField[4].setText(Integer.toString(rent.getDailyRentalFee()));
        textField[5].setText(rent.getLastService().toString());
        textField[6].setText(rent.getInService() ? "igen" : "nem");
        textField[7].setText(rent.getPhoto() ? "van" : "nincs");
        
        presentPhoto(rent.getPhoto(), fromSelectedJpgs(rent.getNumberPlate()));
    }
}
