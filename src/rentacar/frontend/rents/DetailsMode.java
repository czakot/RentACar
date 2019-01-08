/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.frontend.rents;

/**
 *
 * @author czakot
 */
public enum DetailsMode {
    DETAILS("Kiválasztott bérlés adatai"),
    NEW("Új bérlés indítása"),
    FINISHER("Kiválasztott bérlés lezárása"),
    EMPTY(" ");
    private final String title;

    private DetailsMode(String title) {
        this.title = title;
    }
    
    public String getTitle() { return title; }
}
