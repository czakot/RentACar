/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.customers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author czakot
 */
public class CustomersButtons extends JPanel {

    private final CustomersPanel customersPanel;
    private final JButton newCustomer;
    private final JButton save;
    private final JButton discard;
    
    public CustomersButtons(CustomersPanel customersPanel) {
        this.customersPanel = customersPanel;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        newCustomer = new JButton("Új ügyfél felvétele");
        newCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersPanel.createNewCustomer();
            }
        });
        add(newCustomer);
        
        save = new JButton("Mentés");
        save.setVisible(false);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersPanel.saveNew();
            }
        });
        add(save);
        
        discard = new JButton("Eldobás");
        discard.setVisible(false);
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersPanel.discardNew();
            }
        });
        add(discard);
    }
    
    void enableNew() {
        newCustomer.setEnabled(true);
    }
    
    void disableNew() {
        newCustomer.setEnabled(false);
    }
    
    void enableSaveDiscard() {
        save.setVisible(true);
        discard.setVisible(true);
    }

    void disableSaveDiscard() {
        save.setVisible(false);
        discard.setVisible(false);
    }
}
