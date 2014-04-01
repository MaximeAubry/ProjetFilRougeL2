package com.plasprod.filrouge2;

import com.plasprod.Models.Singleton;
import com.plasprod.Views.VueLogin;
import com.plasprod.WebService.ImportWebService;

public class FilRouge 
{
    public static void main( String[] args )
    {
        //ImportWebService test = new ImportWebService();
        //test.connectionWebService();
        
        Singleton.getCurrent().vueLogin = new VueLogin();
        Singleton.getCurrent().vueLogin.setVisible(true);
    }
}
