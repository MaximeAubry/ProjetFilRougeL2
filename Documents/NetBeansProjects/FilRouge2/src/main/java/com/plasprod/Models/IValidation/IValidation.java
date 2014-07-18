package com.plasprod.Models.IValidation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Map;

/**
 *
 * @author Maxime
 */
public interface IValidation {
    public Boolean isValid();
    public Map<String, String> getConstraintViolations();
}
