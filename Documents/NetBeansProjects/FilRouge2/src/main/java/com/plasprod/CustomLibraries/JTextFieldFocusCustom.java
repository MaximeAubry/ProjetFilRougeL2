/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.CustomLibraries;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Maxime
 */
public class JTextFieldFocusCustom implements FocusListener {
    public JTextField textfield;
    public String value;

    public JTextFieldFocusCustom(JTextField textfield) {
        this.textfield = textfield;
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.value = this.textfield.getText();
    }
}