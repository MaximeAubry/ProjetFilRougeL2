package com.plasprod.filrouge2;

import com.plasprod.Models.Client;
import com.plasprod.Models.DataValidation.ClientValidation;
import com.plasprod.Models.Singleton;
import com.plasprod.Views.VueLogin;
//import com.plasprod.WebService.ImportWebService;

public class FilRouge 
{
    public static void main( String[] args )
    {
        //ImportWebService test = new ImportWebService();
        //test.connectionWebService();
        
        Client c = new Client();
        Boolean isValid = ClientValidation.validate(c);
        
        Singleton.getCurrent().vueLogin = new VueLogin();
        Singleton.getCurrent().vueLogin.setVisible(true);
    }
}
