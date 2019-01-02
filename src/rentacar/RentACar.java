/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import rentacar.frontend.GuiManager;

/**
 *
 * @author czakot
 */
public class RentACar {
 
    public final static Color BACKGROUND_DISABLED = new Color(214, 217, 223);
    public final static Color BACKGROUND_ENABLED = new Color(255, 255, 255);
    public final static String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(RentACar.class.getName()).log(Level.SEVERE, null, ex);
        }

        GuiManager.start();
    }
}
