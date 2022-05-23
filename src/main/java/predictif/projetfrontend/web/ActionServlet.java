/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web;

import predictif.projetfrontend.web.action.DeconnecterEmployeAction;
import predictif.projetfrontend.web.action.DeconnecterClientAction;
import predictif.projetfrontend.web.action.ValiderCommentaireAction;
import predictif.projetfrontend.web.action.EmployeTerminerAppelAction;
import predictif.projetfrontend.web.serialisation.GenererPredictionSerialisation;
import predictif.projetfrontend.web.action.EmployeDemarrerAppelAction;
import predictif.projetfrontend.web.action.EmployePretRdvAction;
import predictif.projetfrontend.web.serialisation.ObtenirConsultationSerialisation;
import predictif.projetfrontend.web.action.ObtenirConsultationAction;
import predictif.projetfrontend.web.serialisation.ConsulterListeMediumSerialisation;
import predictif.projetfrontend.web.action.ModifierMdpEmployeAction;
import predictif.projetfrontend.web.action.ModifierMdpClientAction;
import predictif.projetfrontend.web.serialisation.DetailEmployeSerialisation;
import predictif.projetfrontend.web.action.DetailEmployeAction;
import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import predictif.projetfrontend.web.action.Action;
import predictif.projetfrontend.web.action.AuthentifierClientAction;
import predictif.projetfrontend.web.action.AuthentifierEmployeAction;
import predictif.projetfrontend.web.action.ChangerEtatEmployeAction;
import predictif.projetfrontend.web.action.ConsulterListeMediumAction;
import predictif.projetfrontend.web.action.DetailClientAction;
import predictif.projetfrontend.web.action.DetailRdvEmployeAction;
import predictif.projetfrontend.web.action.GenererPredictionAction;
import predictif.projetfrontend.web.action.InscrireClientAction;
import predictif.projetfrontend.web.action.StatistiquesEmployeAction;
import predictif.projetfrontend.web.action.TrierMediumProfessionAction;
import predictif.projetfrontend.web.serialisation.AuthentifierClientSerialisation;
import predictif.projetfrontend.web.serialisation.AuthentifierEmployeSerialisation;
import predictif.projetfrontend.web.serialisation.DetailClientSerialisation;
import predictif.projetfrontend.web.serialisation.DetailRdvEmployeSerialisation;
import predictif.projetfrontend.web.serialisation.InscrireClientSerialisation;
import predictif.projetfrontend.web.serialisation.Serialisation;
import predictif.projetfrontend.web.serialisation.SerialisationGeneral;
import predictif.projetfrontend.web.serialisation.StatistiquesEmployeSerialisation;
import predictif.projetfrontend.web.serialisation.TrierMediumProfessionSerialisation;

/**
 *
 * @author bbbbb
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession(true);

        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "connecterClient": {
                action = new AuthentifierClientAction();
                serialisation = new AuthentifierClientSerialisation();
            }
            break;
            
            case "deconnexion-client": {
                action = new DeconnecterClientAction();
                serialisation = new SerialisationGeneral();
            }
            break;

            case "connecterEmploye": {
                action = new AuthentifierEmployeAction();
                serialisation = new AuthentifierEmployeSerialisation();
            }
            break;
            
            case "deconnexion-employe": {
                action = new DeconnecterEmployeAction();
                serialisation = new SerialisationGeneral();
            }
            break;

            case "inscription": {
                action = new InscrireClientAction();
                serialisation =  new InscrireClientSerialisation();
            }
            break;

            case "consulter-detail-client": {
                action = new DetailClientAction();
                serialisation = new DetailClientSerialisation();
            }
            break;

            case "modifier-mot-de-passe-client": {
                action = new ModifierMdpClientAction();
                serialisation = new SerialisationGeneral();
            }
            break;

            case "consulter-detail-employe": {
                action = new DetailEmployeAction();
                serialisation = new DetailEmployeSerialisation();
            }
            break;

            case "modifier-mot-de-passe-employe": {
                action = new ModifierMdpEmployeAction();
                serialisation = new SerialisationGeneral();
            }
            break;

            case "consulter-liste-medium": {
                action = new ConsulterListeMediumAction();
                serialisation = new ConsulterListeMediumSerialisation();
            }
            break;

            case "obtenir-consultation": {
                action = new ObtenirConsultationAction();
                serialisation = new ObtenirConsultationSerialisation();
            }
            break;

            case "consulter-statistiques-employe": {
                action = new StatistiquesEmployeAction();
                serialisation = new StatistiquesEmployeSerialisation();
            }
            break;

            case "changer-etat-employe": {
                action = new ChangerEtatEmployeAction();
                serialisation = new SerialisationGeneral();
            }
            break;
            
            case "consulter-detail-rdv": {
                action = new DetailRdvEmployeAction();
                serialisation = new DetailRdvEmployeSerialisation();
            }
            break;
            
            case "employe-pret-rdv": {
                action = new EmployePretRdvAction();
                serialisation = new SerialisationGeneral();
            }
            break;
            
            case "employe-valider-commentaire": {
                action = new ValiderCommentaireAction();
                serialisation = new SerialisationGeneral();
            }
            break;
            
            case "employe-demarrer-appel": {
                action = new EmployeDemarrerAppelAction();
                serialisation = new SerialisationGeneral();
            }
            break;
            
            case "employe-terminer-appel": {
                action = new EmployeTerminerAppelAction();
                serialisation = new SerialisationGeneral();
            }
            break;
            
            case "generer-prediction": {
                action = new GenererPredictionAction();
                serialisation = new GenererPredictionSerialisation();
            }
            break;
            
            case "trier-profession": {
                action = new TrierMediumProfessionAction();
                serialisation = new TrierMediumProfessionSerialisation();
            }
            break;
        }

        if (action != null && serialisation != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        } else {
            response.sendError(400, "Bad Request (pas d'action ou de serialisation pour traiter cette requête");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
