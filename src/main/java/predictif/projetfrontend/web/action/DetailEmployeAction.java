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
 * @author bbbbb
 */
public class DetailEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Long idEmploye = Long.parseLong(request.getParameter("idEmploye"));
        ServicePredictif service = new ServicePredictif();
        Employe employe = service.rechercherEmploye(idEmploye);
        request.setAttribute("employe", employe);
        if (employe != null) {
            request.setAttribute("nom", employe.getNom());
            request.setAttribute("prenom", employe.getPrenom());
            

            request.setAttribute("tel", employe.getTelephone());
            request.setAttribute("mail", employe.getMail());
            request.setAttribute("etat", employe.getEtat());

            request.setAttribute("listeRdv", employe.getListeRdv());
        }
    }
}
