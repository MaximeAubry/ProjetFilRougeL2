/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.plasprod.Models.DataValidation;

import com.plasprod.Models.Client;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Maxime
 */
public class ClientValidation {
    public static Boolean validate(Client client) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);
        
        for (ConstraintViolation<Client> contrainte : constraintViolations) {
            System.out.println(contrainte.getRootBeanClass().getSimpleName()+ "." + contrainte.getPropertyPath() + " " + contrainte.getMessage());
        }
        
        return constraintViolations.isEmpty();
    }
}
