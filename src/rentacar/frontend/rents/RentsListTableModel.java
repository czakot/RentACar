/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author czakot
 */
class RentsListTableModel extends DefaultTableModel{

    RentsListTableModel(Object[][] cars, String[] columnNames) {
        super(cars, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
