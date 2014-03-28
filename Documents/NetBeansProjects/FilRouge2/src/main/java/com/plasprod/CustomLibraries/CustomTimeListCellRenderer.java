package com.plasprod.CustomLibraries;

import com.plasprod.Models.CustomObjects.CustomTime;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CustomTimeListCellRenderer extends JLabel implements ListCellRenderer {
    //@Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        CustomTime ct = (CustomTime)value;
        
        if (ct.isEnabled()) {
            c.setBackground(Color.GRAY);
        } else {
            c.setBackground(Color.RED);
        }
        
        c.setEnabled(ct.isEnabled());
        return c;
    }
}
