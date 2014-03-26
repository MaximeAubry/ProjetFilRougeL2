/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.CustomLibraries;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author Maxime
 */
public class JSpinnerChangeCustom implements ChangeListener {
    public JSpinner spinner;
    public Number value;

    public JSpinnerChangeCustom(JSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.value = (Number)this.spinner.getValue();
    }
}