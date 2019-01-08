/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import rentacar.backend.entities.Rent;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class RentsList extends JPanel {
    
    private final RentsPanel rentsPanel;
    private final JTable rentsTable;
    private String selectedIdRent = null;
    private static final String[] COLUMN_NAMES = {"ID", "Ügyfélazonosító", "Rendszám", "Kezdés", "Tervezett befejezés", "Befejezés", "Napi bérleti díj", "Fizetett"};

    public RentsList(RentsPanel rentsPanel) {
        this.rentsPanel = rentsPanel;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(4, 4, 3, 4));
        
        Object[][] rents = getRents();
        RentsListTableModel tableModel = new RentsListTableModel(rents, COLUMN_NAMES);
        
        rentsTable = new JTable(tableModel);
        rentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rentsTable.setColumnSelectionAllowed(false);
        rentsTable.getSelectionModel().addListSelectionListener(this::actionRowSelectionChanged);
        add(new JScrollPane(rentsTable),BorderLayout.CENTER);
// ****** specifikáción kívüli autó törlés ******
         JButton helperDeleteButton = new JButton("Delete Selected");
        helperDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiManager.deleteRent(selectedIdRent);
                selectedIdRent = null;
                updateRentsTable();
            }
        });
        add(helperDeleteButton,BorderLayout.SOUTH);
// ****** specifikáción kívüli autó törlés ******/

//        carsPanel.refreshCarDetails(getSelectedCarDetails());
    }
    
    Rent getSelectedRent() {
        final int row = rentsTable.getSelectedRow();
        if (row < 0) {
            return null;
        }
        
        Rent rent = new Rent();
        
        rent.setIdRent(Integer.valueOf(rentsTable.getValueAt(row,0).toString()));
        rent.setIdCustomer(Integer.valueOf(rentsTable.getValueAt(row,1).toString()));
        rent.setNumberPlate(rentsTable.getValueAt(row,2).toString());
        try {
            rent.setBeginningDate(LocalDate.parse(rentsTable.getValueAt(row,3).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
        } catch (DateTimeParseException dtpe) {
            rent.setBeginningDate(null);
        }
        try {
            rent.setExpectedReturnDate(LocalDate.parse(rentsTable.getValueAt(row,4).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
        } catch (DateTimeParseException dtpe) {
            rent.setExpectedReturnDate(null);
        }
        try {
            rent.setReturnDate(LocalDate.parse(rentsTable.getValueAt(row,5).toString(), DateTimeFormatter.ISO_LOCAL_DATE));
        } catch (DateTimeParseException dtpe) {
            rent.setReturnDate(null);
        }
        rent.setDailyRentalFee(Integer.valueOf(rentsTable.getValueAt(row,6).toString()));
        rent.setPaidFee(Integer.valueOf(rentsTable.getValueAt(row,7).toString()));

        return rent;
}
    
    private Object[][] getRents() {
        final int COLUMN_NUMBER = 8;
        List<Rent> rents = GuiManager.listRents();
        Object[][] rentsObjArray = null;
        if (rents != null) {
            rentsObjArray = new Object[rents.size()][COLUMN_NUMBER];
            int rowIdx = 0;
            for (Rent rent : rents) {
                rentsObjArray[rowIdx][0] = rent.getIdRent();
                rentsObjArray[rowIdx][1] = rent.getIdCustomer();
                rentsObjArray[rowIdx][2] = rent.getNumberPlate();
                rentsObjArray[rowIdx][3] = rent.getBeginningDate();
                rentsObjArray[rowIdx][4] = rent.getExpectedReturnDate();
                rentsObjArray[rowIdx][5] = rent.getReturnDate();
                rentsObjArray[rowIdx][6] = rent.getDailyRentalFee();
                rentsObjArray[rowIdx][7] = rent.getPaidFee();
                rowIdx++;
            }
        }
        return rentsObjArray;
    }
    
    private void actionRowSelectionChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            selectedIdRent = rentsTable.getValueAt(rentsTable.getSelectedRow(),0).toString(); // kiválasztott bérlés
            rentsPanel.refreshRentDetails(getSelectedRent());
        }
    }
    
    void updateRentsTable() {
        RentsListTableModel tableModel = (RentsListTableModel)(rentsTable.getModel());
        tableModel.setRowCount(0);
//        rentsTable.clearSelection();
//        rentsTable.getSelectionModel().clearSelection();
        Object[][] rentsObjArray = getRents();
        for (int i = 0; i < rentsObjArray.length; ++i) {
            tableModel.addRow(rentsObjArray[i]);
        }
        enableRowSelectionChange();
        
        if (selectedIdRent != null) {
            for (int i = 0; i < rentsTable.getRowCount(); i++) {
                if (selectedIdRent.equals(rentsTable.getValueAt(i, 0).toString())) {
                    rentsTable.setRowSelectionInterval(i, i);
                    break;
                }
            }
        }
        rentsPanel.refreshRentDetails(getSelectedRent());
    }
    
    void enableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(true);
        rentsTable.setEnabled(true);
    }
    
    void disableRowSelectionChange() {
//        carsTable.setRowSelectionAllowed(false);
        rentsTable.setEnabled(false);
    }
}

