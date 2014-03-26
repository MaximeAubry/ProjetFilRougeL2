/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.CustomLibraries;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Maxime
 */
public class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
    public JSpinner spinner = new JSpinner();

    public SpinnerEditor(Double defaultValue, Double MinimumValue, Double MaximumValue, Double Step) {
        SpinnerModel modelSpinnerDuree = new SpinnerNumberModel(defaultValue, MinimumValue, MaximumValue, Step);
        spinner.setModel(modelSpinnerDuree);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            return ((MouseEvent) evt).getClickCount() >= 2;
        }
        return true;
    }

    public Object getCellEditorValue() {
        return spinner.getValue();
    }
}