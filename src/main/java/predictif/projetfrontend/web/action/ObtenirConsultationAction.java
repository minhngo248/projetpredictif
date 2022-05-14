/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.modele.Medium;
import metier.modele.Rdv;
import metier.service.CustomerUnavailableException;
import metier.service.NoEmployeeAvailableException;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class ObtenirConsultationAction extends Action {

   

    @Override
    public void executer(HttpServletRequest request) {
        ServicePredictif service = new ServicePredictif();
         
        Long idMedium = Long.parseLong( request.getParameter("idMedium") );
        Medium medium = service.rechercherMedium(idMedium);
         
        Long idClient = (Long)request.getSession().getAttribute("idClient");
        Client client = service.rechercherClient(idClient);
        Rdv rdv = new Rdv(); 
        try {
            rdv = service.obtenirConsultation(medium, client);
        } catch (NoEmployeeAvailableException ex) {
            ex.printStackTrace(System.out);
        } catch (CustomerUnavailableException ex) {
            Logger.getLogger(ObtenirConsultationAction.class.getName()).log(Level.SEVERE, null, ex);
        }     
        request.setAttribute("rdv", rdv);  
    }
}
