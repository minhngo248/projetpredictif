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
public class ModifierMdpClientAction extends Action {

     @Override
    public void executer(HttpServletRequest request) {
        
        String password = request.getParameter("password");
        
        ServicePredictif service=new ServicePredictif();
        
        Long idClient = (Long)(request.getSession().getAttribute("idClient"));
      
        Client client = service.rechercherClient(idClient);
        service.changerMdp(client, password);
      
    }
    
}
