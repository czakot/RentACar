/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.customers;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author czakot
 */
class CustomersListTableModel extends DefaultTableModel{

    CustomersListTableModel(Object[][] customers, String[] columnNames) {
        super(customers, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
