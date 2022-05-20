/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.modele.Rdv;
import metier.modele.Rdv.Etat;
import metier.service.ServicePredictif;

/**
 *
 * @author bbbbb
 */
public class DetailRdvEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Long idEmp = (Long) request.getSession().getAttribute("idEmploye");
        ServicePredictif service = new ServicePredictif();
        Employe employe = service.rechercherEmploye(idEmp);
        employe.getListeRdv().size();
        Rdv rdvEnAttente = null;
        for (Rdv unRdv:employe.getListeRdv()) {
            if (unRdv.getEtat() == Etat.EN_ATTENTE || unRdv.getEtat() == Etat.EN_COURS) {
                rdvEnAttente = unRdv;
                break;
            }
        }
       
        if (rdvEnAttente != null)
            
         request.getSession().setAttribute("rdvEnAttente", rdvEnAttente);
        else
            request.getSession().setAttribute("rdvEnAttente", null);
    }   
}
