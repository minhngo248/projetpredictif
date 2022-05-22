/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class InscrireClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String dateNaissance = request.getParameter("dateNaissance");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ServicePredictif service = new ServicePredictif();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
            
        Client client;
        try {
            client = service.inscrire(new Client(nom, prenom, login, password, sdf.parse(dateNaissance), adresse, telephone));
        } catch (ParseException ex) {
            client = null;
            Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("clientInscrip", client);       
    }
}
