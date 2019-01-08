/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.customers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import rentacar.backend.entities.Customer;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class CustomersList extends JPanel {
    
    private final CustomersPanel customersPanel;
    private final JTable customersTable;
    private String selectedIdCustomer = null;
    private static final String[] COLUMN_NAMES = {"ID", "Név", "Cím", "Telefonszám"};

    public CustomersList(CustomersPanel customersPanel) {
        this.customersPanel = customersPanel;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(4, 4, 3, 4));
        
        Object[][] customers = getCustomers();
        CustomersListTableModel tableModel = new CustomersListTableModel(customers, COLUMN_NAMES);
        
        customersTable = new JTable(tableModel);
        customersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customersTable.setColumnSelectionAllowed(false);
        customersTable.getSelectionModel().addListSelectionListener(this::actionRowSelectionChanged);
        add(new JScrollPane(customersTable),BorderLayout.CENTER);
// ****** specifikáción kívüli autó törlés ******
         JButton helperDeleteButton = new JButton("Delete Selected");
        helperDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiManager.deleteCustomer(selectedIdCustomer);
                selectedIdCustomer = null;
                updateCarsTable();
            }
        });
        add(helperDeleteButton,BorderLayout.SOUTH);
// ****** specifikáción kívüli autó törlés ******/

//        carsPanel.refreshCarDetails(getSelectedCarDetails());
    }
    
    Customer getSelectedCustomer() {
        final int row = customersTable.getSelectedRow();
        if (row < 0) {
            return null;
        }
        
        Customer customer = new Customer();
        
        customer.setIdCustomer(Integer.valueOf(customersTable.getValueAt(row,0).toString()));
        customer.setName(customersTable.getValueAt(row,1).toString());
        customer.setAddress(customersTable.getValueAt(row,2).toString());
        customer.setPhone(customersTable.getValueAt(row,3).toString());

        return customer;
}
    
    private Object[][] getCustomers() {
        final int COLUMN_NUMBER = 4;
        List<Customer> customers = GuiManager.listCustomers();
        Object[][] customersObjArray = null;
        if (customers != null) {
            customersObjArray = new Object[customers.size()][COLUMN_NUMBER];
            int rowIdx = 0;
            for (Customer customer : customers) {
                customersObjArray[rowIdx][0] = customer.getIdCustomer();
                customersObjArray[rowIdx][1] = customer.getName();
                customersObjArray[rowIdx][2] = customer.getAddress();
                customersObjArray[rowIdx][3] = customer.getPhone();
                rowIdx++;
            }
        }
        return customersObjArray;
    }
    
    private void actionRowSelectionChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            selectedIdCustomer = customersTable.getValueAt(customersTable.getSelectedRow(),0).toString(); // kiválasztott ügyfél
            customersPanel.refreshCustomerDetails(getSelectedCustomer());
        }
    }
    
    void updateCarsTable() {
        CustomersListTableModel tableModel = (CustomersListTableModel)(customersTable.getModel());
        tableModel.setRowCount(0);
        Object[][] customersObjArray = getCustomers();
        for (int i = 0; i < customersObjArray.length; ++i) {
            tableModel.addRow(customersObjArray[i]);
        }
        enableRowSelectionChange();
        
        if (selectedIdCustomer != null) {
            for (int i = 0; i < customersTable.getRowCount(); i++) {
                if (selectedIdCustomer.equals(customersTable.getValueAt(i, 0).toString())) {
                    customersTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
        customersPanel.refreshCustomerDetails(getSelectedCustomer());
    }
    
    void enableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(true);
        customersTable.setEnabled(true);
    }
    
    void disableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(false);
        customersTable.setEnabled(false);
    }
}

