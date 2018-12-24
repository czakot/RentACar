/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import rentacar.backend.entities.Car;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class CarsPanel extends JPanel {
    private final JPanel listPanel;
    private final JPanel detailsPanel;
    private final JPanel buttonsPanel;


    public CarsPanel() {
        setLayout(new BorderLayout());
        
        listPanel = new JPanel();
        detailsPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        setupListPanel(listPanel);
        setupDetailsPanel(detailsPanel);
        setupButtonsPanel(buttonsPanel);

        // csak a láthatóság kedvéért
        listPanel.setBackground(Color.RED);
        detailsPanel.setBackground(Color.YELLOW);
        buttonsPanel.setBackground(Color.GREEN);
        
        
        add(listPanel,BorderLayout.NORTH);
        add(detailsPanel,BorderLayout.CENTER);
        add(buttonsPanel,BorderLayout.SOUTH);
    }

    private void setupListPanel(JPanel listPanel) {
        setLayout(new BorderLayout());
        setPanelPrefHeight(listPanel,.5);
        
        final String[] columnNames = {"Rendszám", "Márka", "Típus", "Évjárat", "Bérleti díj", "Szervizelés dátuma", "Szervizben van?", "Fénykép"};
        Object[][] cars = getCars();
        DefaultTableModel defaultTableModel = new DefaultTableModel(cars, columnNames);
        JTable tableCars = new JTable(defaultTableModel);
        listPanel.add(tableCars);
    }

    private void setupDetailsPanel(JPanel dp) {
        setLayout(new BorderLayout());
        setPanelPrefHeight(dp,.4);
    }

    private void setupButtonsPanel(JPanel bp) {
        setLayout(new BorderLayout());
        setPanelPrefHeight(bp,.1);
    }

    private void setPanelPrefHeight(JPanel panel, double ratio) {
        Dimension dim = new Dimension(panel.getMaximumSize());
        dim.height = (int)(dim.height * ratio);
        panel.setPreferredSize(dim);
    }

    private Object[][] getCars() {
        final int COLUMN_NUMBER = 8;
        List<Car> carList = GuiManager.listCars();
        Object[][] carTable = new Object[carList.size()][COLUMN_NUMBER];
        int rowIdx = 0;
        for (Car car : carList) {
            carTable[rowIdx][0] = car.getNumberPlate();
            carTable[rowIdx][1] = car.getMake();
            carTable[rowIdx][2] = car.getModel();
            carTable[rowIdx][3] = car.getYearOfManufacturing();
            carTable[rowIdx][4] = car.getDailyRentalFee();
            carTable[rowIdx][5] = car.getLastService();
            carTable[rowIdx][6] = car.getInService();
            carTable[rowIdx][7] = car.getPhoto();
            rowIdx++;
        }
        return carTable;
    }
}
