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
 * @author bbbbb
 */
public class DetailClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Long idClient = (Long) request.getSession().getAttribute("idClient");
        ServicePredictif service = new ServicePredictif();
        Client client = service.rechercherClient(idClient);
<<<<<<< HEAD
        //System.out.println("************************** client=" + client);
        
        client.getListeRdv().size();
        request.setAttribute("client", client);
=======
       

        request.setAttribute("client", client);
        List<Rdv> listeRdv=service.getHistorique(client);
        request.setAttribute("listeRdv", listeRdv);
//        if (client != null) {
//            request.setAttribute("nom", client.getNom());
//            request.setAttribute("prenom", client.getPrenom());
//            request.setAttribute("dob", client.getDateNaissance());
//            request.setAttribute("adresse", client.getAdressePostale());
//            request.setAttribute("tel", client.getTelephone());
//            request.setAttribute("mail", client.getMail());
//            
//            request.setAttribute("profil", client.getProfil());
//            request.setAttribute("totem", client.getProfil().getAnimalTotem());
//            request.setAttribute("zodiaque", client.getProfil().getSigneZodiac());
//            request.setAttribute("chinois", client.getProfil().getSigneChinois());
//            request.setAttribute("couleur", client.getProfil().getCouleur());
//            
//            client.getListeRdv().size();
        //request.setAttribute("listeRdv", client.getListeRdv());
        // }
>>>>>>> 4207522fd63fb47d17d115859c111d7083d2a62a
    }
}
