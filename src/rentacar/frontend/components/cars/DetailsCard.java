/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import rentacar.backend.entities.BareCar;

/**
 *
 * @author czakot
 */
public class DetailsCard extends BaseCard {

    private final JTextField[] textField;
    
    public DetailsCard(CarDetails carDetails) {
        super(carDetails);
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
    
    void refreshValues(BareCar car) {
        
        textField[0].setText(car.getNumberPlate());
        textField[1].setText(car.getMake());
        textField[2].setText(car.getModel());
        textField[3].setText(Integer.toString(car.getYearOfManufacturing()));
        textField[4].setText(Integer.toString(car.getDailyRentalFee()));
        textField[5].setText(car.getLastService().toString());
        textField[6].setText(car.getInService() ? "igen" : "nem");
        textField[7].setText(car.getPhoto() ? "van" : "nincs");
        
        presentPhoto(car.getPhoto(), fromSelectedJpgs(car.getNumberPlate()));
    }
}
