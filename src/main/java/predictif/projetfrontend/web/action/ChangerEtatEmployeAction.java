/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.modele.Employe.EtatsEmploye;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class ChangerEtatEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Long idEmploye = (Long) request.getSession().getAttribute("idEmploye");
        ServicePredictif service = new ServicePredictif();
        Employe employe = service.rechercherEmploye(idEmploye);
        //System.out.println(employe);

        if (employe.getEtat() == EtatsEmploye.DISPONIBLE) {
            service.changerEtatEmploye(employe, EtatsEmploye.INDISPONIBLE);

        } else {
            service.changerEtatEmploye(employe, EtatsEmploye.DISPONIBLE);

        }
        //System.out.println(employe.getEtat());

    }

}
