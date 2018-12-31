/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.rents;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author czakot
 */
public class RentsPanel  extends JPanel {
    JLabel fillerRent = new JLabel("Tab3 = Bérlések");

    public RentsPanel() {
        fillerRent.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new GridLayout(1, 1));
        add(fillerRent);
    }
}
