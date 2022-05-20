/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Rdv;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class GenererPredictionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicePredictif service = new ServicePredictif();

        Integer amour = Integer.parseInt(request.getParameter("amour"));
        Integer travail = Integer.parseInt(request.getParameter("travail"));

        Integer sante = Integer.parseInt(request.getParameter("sante"));
        
        Rdv rdvEnCours= (Rdv) request.getSession().getAttribute("rdvEnCours");
        Client client=rdvEnCours.getClient();
        
        service.getPredictions(client, amour, sante, travail).size();
        List<String> resultatPredictions=service.getPredictions(client, amour, sante, travail);
        request.setAttribute("predictions", resultatPredictions);

    }

}
