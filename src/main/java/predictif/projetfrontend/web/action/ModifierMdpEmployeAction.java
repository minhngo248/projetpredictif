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
public class ModifierMdpEmployeAction extends Action {

     @Override
    public void executer(HttpServletRequest request) {
        
        String password = request.getParameter("password");
        
        ServicePredictif service=new ServicePredictif();
        
        Long idEmploye = (Long)(request.getSession().getAttribute("idEmploye"));
      
        Employe employe  = service.rechercherEmploye(idEmploye);
        Employe resultat=service.changerMdp(employe, password);
        request.setAttribute("employe", resultat);
      
    }
    
}
