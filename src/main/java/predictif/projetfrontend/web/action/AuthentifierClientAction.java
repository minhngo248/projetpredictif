/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;


import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;

import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class AuthentifierClientAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        ServicePredictif service=new ServicePredictif();
        
        Client client = service.authentifierClient(login, password);
         request.setAttribute("client", client);

        if (client != null) {
            request.getSession().setAttribute("idClient", client.getId());

        }    
    }
}

