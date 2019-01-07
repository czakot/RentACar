/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components.cars;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import rentacar.backend.entities.BareCar;
import rentacar.backend.entities.Car;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class CarsList extends JPanel {
    
    private final CarsPanel carsPanel;
    private final JTable carsTable;
    private String selectedNumberPlate = null;
    private static final String[] COLUMN_NAMES = {"Rendszám", "Márka", "Típus", "Évjárat", "Bérleti díj", "Szervizelés", "Szervizben?", "Fénykép"};

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
// ****** specifikáción kívüli autó törlés ******
         JButton helperDeleteButton = new JButton("Delete Selected");
        helperDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiManager.deleteCar(selectedNumberPlate);
                selectedNumberPlate = null;
                updateCarsTable();
            }
        });
        add(helperDeleteButton,BorderLayout.SOUTH);
// ****** specifikáción kívüli autó törlés ******/

//        carsPanel.refreshCarDetails(getSelectedCarDetails());
    }
    
    BareCar getSelectedCar() {
        final int row = carsTable.getSelectedRow();
        if (row < 0) {
            return null;
        }
        
        BareCar car = new BareCar();
        
        car.setNumberPlate(carsTable.getValueAt(row,0).toString());
        car.setMake(carsTable.getValueAt(row,1).toString());
        car.setModel(carsTable.getValueAt(row,2).toString());
        car.setYearOfManufacturing(Integer.valueOf(carsTable.getValueAt(row,3).toString()));
        car.setDailyRentalFee(Integer.valueOf(carsTable.getValueAt(row,4).toString()));
        String[] dateElements = carsTable.getValueAt(row,5).toString().split("-");
        car.setLastService(LocalDate.of(Integer.valueOf(dateElements[0]),Integer.valueOf(dateElements[1]),Integer.valueOf(dateElements[2])));
        car.setInService(carsTable.getValueAt(row,5).toString().equals("igen"));
        car.setPhoto(carsTable.getValueAt(row,7).toString().equals("van"));
        car.setChoosenPhotoPath("");

        return car;
}
    
    private Object[][] getCars() {
        final int COLUMN_NUMBER = 8;
        List<Car> cars = GuiManager.listCars();
        Object[][] carsObjArray = null;
        if (cars != null) {
            carsObjArray = new Object[cars.size()][COLUMN_NUMBER];
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
        }
        return carsObjArray;
    }
    
    private void actionRowSelectionChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            selectedNumberPlate = carsTable.getValueAt(carsTable.getSelectedRow(),0).toString(); // kiválasztott rendszám
            carsPanel.refreshCarDetails(getSelectedCar());
        }
    }
    
    void updateCarsTable() {
        CarsListTableModel tableModel = (CarsListTableModel)(carsTable.getModel());
        tableModel.setRowCount(0);
//        carsTable.clearSelection();
//        carsTable.getSelectionModel().clearSelection();
        Object[][] carsObjArray = getCars();
        for (int i = 0; i < carsObjArray.length; ++i) {
            tableModel.addRow(carsObjArray[i]);
        }
        enableRowSelectionChange();
        
        if (selectedNumberPlate != null) {
            for (int i = 0; i < carsTable.getRowCount(); i++) {
                if (selectedNumberPlate.equals(carsTable.getValueAt(i, 0).toString())) {
                    carsTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
        carsPanel.refreshCarDetails(getSelectedCar());
    }
    
    void enableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(true);
        carsTable.setEnabled(true);
    }
    
    void disableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(false);
        carsTable.setEnabled(false);
    }
}

