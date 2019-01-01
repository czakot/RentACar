/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.utility;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/**
 *
 * @author czakot
 */
public class MyTextField extends JTextField {

    public MyTextField(String text) {
        super(text);
    }
    @Override
    public Point getToolTipLocation(MouseEvent event) {
      return new Point(getWidth(), 0);
    }
}
