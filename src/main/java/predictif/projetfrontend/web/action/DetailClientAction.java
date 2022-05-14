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
 * @author bbbbb
 */
public class DetailClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Long idClient = (Long) request.getSession().getAttribute("idClient");
        ServicePredictif service = new ServicePredictif();
        Client client = service.rechercherClient(idClient);
        //System.out.println("************************** client=" + client);
        
        client.getListeRdv().size();
        request.setAttribute("client", client);
    }
}
