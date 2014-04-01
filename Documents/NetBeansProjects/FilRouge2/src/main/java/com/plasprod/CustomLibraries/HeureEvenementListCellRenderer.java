package com.plasprod.CustomLibraries;

import com.plasprod.Models.CustomObjects.HeureEvenement;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class HeureEvenementListCellRenderer extends JLabel implements ListCellRenderer {
    //@Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        HeureEvenement heureEvenement = (HeureEvenement)value;
        setText(heureEvenement.toString());
        
        setOpaque(true);
        setEnabled(heureEvenement.isEnabled());
        
        if (isSelected) {
            if (heureEvenement.isEnabled()) {
                setForeground(Color.WHITE);
                setBackground(Color.BLUE);
            }
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        return this;
    }
}
