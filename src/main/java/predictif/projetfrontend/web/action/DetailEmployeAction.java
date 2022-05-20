/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.modele.Rdv;
import metier.service.ServicePredictif;

/**
 *
 * @author bbbbb
 */
public class DetailEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Long idEmploye = (Long) request.getSession().getAttribute("idEmploye");
        System.out.println(idEmploye);
        ServicePredictif service = new ServicePredictif();
        Employe employe = service.rechercherEmploye(idEmploye);
        request.setAttribute("employe", employe);
        List<Rdv> listeRdv=service.getHistorique(employe);
        request.setAttribute("listeRdv", listeRdv);
        
    }
}
