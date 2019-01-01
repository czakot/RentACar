/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.utility;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.text.Format;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author czakot
 */
public class MyFormattedTextField extends JFormattedTextField {

    public MyFormattedTextField(MaskFormatter format) {
        super(format);
    }
    @Override
    public Point getToolTipLocation(MouseEvent event) {
      return new Point(getWidth(), 0);
    }
}
