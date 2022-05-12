/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;


import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;

import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class AuthentifierEmployeAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        ServicePredictif service=new ServicePredictif();
        
        Employe employe = service.authentifierEmploye(login, password);
        
        request.setAttribute("employe", employe);
        if (employe != null) {
            request.setAttribute("idEmploye", employe.getId());
        }    
    }
}


