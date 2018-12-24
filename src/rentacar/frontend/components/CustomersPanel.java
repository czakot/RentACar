/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author czakot
 */
public class CustomersPanel  extends JPanel {
    JLabel fillerCustomer = new JLabel("Tab2 = Ügyfelek");

    public CustomersPanel() {
        fillerCustomer.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new GridLayout(1, 1));
        add(fillerCustomer);
    }
}
