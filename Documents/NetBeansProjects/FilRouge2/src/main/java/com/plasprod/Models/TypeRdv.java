/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models;

/**
 *
 * @author Maxime
 */
public enum TypeRdv {
    repas("REPAS"),
    reunion("REUNION");
    
    /** L'attribut qui contient la valeur associé à l'enum */
    private final String display;

    /** Le constructeur qui associe une valeur à l'enum */
    TypeRdv(String s) {
        display = s;
    }

    /** La méthode accesseur qui renvoit la valeur de l'enum
     * @return  */
    @Override
    public String toString() {
        return display;
    }
};