/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import rentacar.backend.entities.Car;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class CarsList extends JPanel {

    private final CarsPanel carsPanel;
    private final JTable carsTable;
    private String selected = null;
    private static final String[] COLUMN_NAMES = {"Rendszám", "Márka", "Típus", "Évjárat", "Bérleti díj", "Szervizelés:", "Szervizben?", "Fénykép"};

    public CarsList(CarsPanel carsPanel) {
        this.carsPanel = carsPanel;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(4, 4, 3, 4));
        
        Object[][] cars = getCars();
        CarsListTableModel tableModel = new CarsListTableModel(cars, COLUMN_NAMES);
        
        carsTable = new JTable(tableModel);
        carsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carsTable.setColumnSelectionAllowed(false);
        carsTable.getSelectionModel().addListSelectionListener(this::actionRowSelectionChanged);
        add(new JScrollPane(carsTable),BorderLayout.CENTER);

//        carsPanel.refreshCarDetails(getSelectedCarDetails());
    }
    
    private String[] getSelectedCarValues() {
        final int row = carsTable.getSelectedRow();
        if (row < 0) {
            return null;
        }
        
        final int columns = carsTable.getColumnCount();
        String[] selectedCarValues = new String[columns];
        
        for (int column = 0; column < columns;++column) {
            selectedCarValues[column] = carsTable.getValueAt(row,column).toString();
        }
        return selectedCarValues;
    }
    
    private Object[][] getCars() {
        final int COLUMN_NUMBER = 8;
        List<Car> cars = GuiManager.listCars();
        Object[][] carsObjArray = new Object[cars.size()][COLUMN_NUMBER];
        int rowIdx = 0;
        for (Car car : cars) {
            carsObjArray[rowIdx][0] = car.getNumberPlate();
            carsObjArray[rowIdx][1] = car.getMake();
            carsObjArray[rowIdx][2] = car.getModel();
            carsObjArray[rowIdx][3] = car.getYearOfManufacturing();
            carsObjArray[rowIdx][4] = car.getDailyRentalFee();
            carsObjArray[rowIdx][5] = car.getLastService();
            carsObjArray[rowIdx][6] = (car.getInService() ? "igen" : "nem");
            carsObjArray[rowIdx][7] = (car.getPhoto() ? "van" : "nincs");
            rowIdx++;
        }
        return carsObjArray;
    }
    
    private void actionRowSelectionChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            selected = carsTable.getValueAt(carsTable.getSelectedRow(),0).toString(); // kiválasztott rendszám
            carsPanel.refreshCarDetails(getSelectedCarValues());
        }
    }
    
    void updateCarsTable() {
        CarsListTableModel tableModel = (CarsListTableModel)(carsTable.getModel());
        tableModel.setRowCount(0);
        Object[][] carsObjArray = getCars();
        for (int i = 0; i < carsObjArray.length; ++i) {
            tableModel.addRow(carsObjArray[i]);
        }
        enableRowSelectionChange();
        
        if (selected != null) {
            for (int i = 0; i < carsTable.getRowCount(); i++) {
                if (selected.equals(carsTable.getValueAt(i, 0).toString())) {
                    carsTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
    }
    
    void enableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(false);
        carsTable.setEnabled(true);
    }
    
    void disableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(false);
        carsTable.setEnabled(false);
    }
}

