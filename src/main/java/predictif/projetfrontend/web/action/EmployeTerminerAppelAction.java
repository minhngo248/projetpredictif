/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Rdv;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class EmployeTerminerAppelAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicePredictif service = new ServicePredictif();

        Rdv rdvEnCours = (Rdv) request.getSession().getAttribute("rdvEnCours");
        service.terminerRdv(rdvEnCours);
        Rdv rdvTermine=rdvEnCours;
    
        
         request.getSession().removeAttribute("rdvEnCours");
         request.getSession().setAttribute("rdvTermine",rdvTermine);
    }

    
    
}
