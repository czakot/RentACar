/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author czakot
 */
public class RentsButtons extends JPanel {

    private final RentsPanel rentsPanel;
    private final JButton newRent;
    private final JButton finishRent;
    private final JButton save;
    private final JButton discard;
    private Boolean previousEnableStateFinishButton;
    private ActionListener ActionListenerForSave;
    
    public RentsButtons(RentsPanel rentsPanel) {
        this.rentsPanel = rentsPanel;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        newRent = new JButton("Új bérlés indítása");
        newRent.addActionListener(this::createNewRent);
        add(newRent);
        
        finishRent = new JButton("Bérlés lezárása");
        finishRent.setEnabled(false);
        finishRent.addActionListener(this::finishRent);
        add(finishRent);
        
        save = new JButton("Rögzítés");
        save.setVisible(false);
        add(save);
        
        discard = new JButton("Eldobás");
        discard.setVisible(false);
        discard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentsPanel.discardNewOrFinish();
            }
        });
        add(discard);
    }
    
    private void createNewRent(ActionEvent e) {
        ActionListenerForSave = rentsPanel::saveNew;
        rentsPanel.createNewRent();
    }
    
    private void finishRent(ActionEvent e) {
        ActionListenerForSave = rentsPanel::saveFinished;
        rentsPanel.finishSelectedRent();
    }
    
    private void removeSaveActionListeners() {
        for (ActionListener actionListener : save.getActionListeners()) {
            save.removeActionListener(actionListener);
        }
    }
    
    void enableFinish() {
        finishRent.setEnabled(true);
    }
    
    void disableFinish() {
        finishRent.setEnabled(false);
    }
    
    void enableNew() {
        newRent.setEnabled(true);
    }
    
    void enableNewFinish() {
        newRent.setEnabled(true);
        finishRent.setEnabled(previousEnableStateFinishButton);
    }
    
    void disableNewFinish() {
        newRent.setEnabled(false);
        previousEnableStateFinishButton = finishRent.isEnabled();
        finishRent.setEnabled(false);
    }
    
    void enableSaveDiscard() {
        save.setVisible(true);
        save.addActionListener(ActionListenerForSave);
        discard.setVisible(true);
    }

    void disableSaveDiscard() {
        save.setVisible(false);
        discard.setVisible(false);
        removeSaveActionListeners();
    }
}
