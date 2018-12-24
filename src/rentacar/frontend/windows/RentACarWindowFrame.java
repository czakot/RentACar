/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import rentacar.frontend.components.RentsPanel;
import rentacar.frontend.components.CustomersPanel;
import rentacar.frontend.components.CarsPanel;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author czakot
 */
public class RentACarWindowFrame extends JFrame {
    
    private static JTabbedPane tabbedPane;
    private static CarsPanel carsPanel;
    private static CustomersPanel customersPanel;
    private static RentsPanel rentsPanel;
//    private static JPanel actionPanel;
//    private static DashboardPanel contentPanel;
    private final static String[] TAB_TITLES = {"Autók","Ügyfelek","Bérlések"};
/*
    private final static Object[] PRODUCT_COLUMN_NAMES = new Object[]{"Termék neve", "Termék ára", "Termék mennyiésge"};
    private final static Object[] RETAILER_COLUMN_NAMES = new Object[]{"Kereskedő neve", "Kereskedő címe", "Kereskedő hitelkerete", "Kereskedő telefonszáma"};
    private final static Object[] ORDER_COLUMN_NAMES = new Object[]{"Rendelés azonosító", "Kereskedő neve", "Rendelés Dátuma", "Rendelés ára", "Rendelés státusza"};
    private final static Object[] ORDERLINE_COLUMN_NAMES = new Object[]{"Rendeléssor azonosító", "Rendelés azonosító", "Termék neve", "Rendelt mennyiség", "Rendeléssor ára"};
*/
    public RentACarWindowFrame() throws HeadlessException {
        initScreen();
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
//        contentPanel = new DashboardPanel(this);
//        actionPanel = new JPanel();
//        add(contentPanel, actionPanel);
        add(tabbedPane);
        setTitle("Rent-A-Car");
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
