/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import rentacar.backend.entities.Rent;

/**
 *
 * @author czakot
 */
public class RentDetails extends JPanel {
    
    private final RentsPanel rentsPanel;
    private final JPanel cards;
    private final CardLayout cardsLayout;
    private final JPanel emptyCard;
    private final DetailsCard detailsCard;
    private final NewCard newCard;
//    private final FinishCard finishCard;
    private DetailsMode mode;
    private DetailsMode modeToReturn;
        
    
    public RentDetails(RentsPanel rentsPanel) {
        this.rentsPanel = rentsPanel;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                      BorderFactory.createEmptyBorder(0, 4, 0, 4),
                      BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),"")));
        
        emptyCard = new JPanel(new BorderLayout());
        emptyCard.add(new JLabel("Nincs kiválasztott vagy megjeleníthető elem",JLabel.CENTER),BorderLayout.CENTER);
        
        newCard = new NewCard(this);
//        finishCard = new FinishCard(this);
        detailsCard = new DetailsCard(this);
        
        cardsLayout = new CardLayout();
        cards = new JPanel(cardsLayout);
        cards.add(emptyCard,DetailsMode.EMPTY.toString());
        cards.add(newCard,DetailsMode.NEW.toString());
//        cards.add(finishCard,DetailsMode.FINISHER.toString());
        cards.add(detailsCard,DetailsMode.DETAILS.toString());
        switchMode(DetailsMode.EMPTY);
        
        add(cards, BorderLayout.CENTER);
    }
    
    void switchMode(DetailsMode mode) {
        switch (mode) {
            case EMPTY:
                modeToReturn = null;
                break;
            case DETAILS:
                modeToReturn = null;
                break;
            case NEW:
                modeToReturn = this.mode;
                break;
            case FINISHER:
                modeToReturn = this.mode;
//                finishCard.setEditorContent(rentsPanel.getSelectedRent());
                break;
            default:
                throw new AssertionError();
        }
        this.mode = mode;
        setDetailsTitle(mode);
        cardsLayout.show(cards, mode.toString());
    }
    
    void refreshRentDetails(Rent rent) {
        if (rent == null) {
            rentsPanel.disableFinishRentButton();
            switchMode(DetailsMode.EMPTY);
            return;
        }
        
        detailsCard.refreshValues(rent);
        switchMode(DetailsMode.DETAILS);
        rentsPanel.enableFinishRentButton();
    }
    
    private void setDetailsTitle(DetailsMode mode) {
        ((TitledBorder)(((CompoundBorder)(this.getBorder())).getInsideBorder())).setTitle(mode.getTitle());
        repaint();
    }
    
    void discard() {
        ((NewCard)getTopCard()).reset();
        switchMode(modeToReturn);
    }

    public Component getTopCard() {
        Component card = null;
        for (Component component : cards.getComponents()) {
            if (component.isVisible()) {
                card = component;
                break;
            }
        }
        return card;
    }
}

