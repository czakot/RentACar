/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import rentacar.frontend.rents.RentsPanel;
import rentacar.frontend.customers.CustomersPanel;
import rentacar.frontend.cars.CarsPanel;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class RentACarWindowFrame extends JFrame {
    
    private static JTabbedPane tabbedPane;
    private static CarsPanel carsPanel;
    private static CustomersPanel customersPanel;
    private static RentsPanel rentsPanel;
    private final static String[] TAB_TITLES = {"Autók","Ügyfelek","Bérlések"};

    public RentACarWindowFrame() throws HeadlessException {
        initScreen();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                GuiManager.closeDB();
            }
        });
    }

    private void initScreen() {
        setLayout(new BorderLayout());
        
        tabbedPane = new JTabbedPane();

        carsPanel = new CarsPanel();
        customersPanel = new CustomersPanel();
        rentsPanel = new RentsPanel();

        tabbedPane.add(TAB_TITLES[0], carsPanel);
        tabbedPane.add(TAB_TITLES[1], customersPanel);
        tabbedPane.add(TAB_TITLES[2], rentsPanel);
        add(tabbedPane);

        setTitle("Rent-A-Car");
        setPreferredSize(new Dimension(800,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
