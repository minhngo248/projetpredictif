/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Rdv;
import metier.modele.Rdv.Etat;

/**
 *
 * @author bbbbb
 */
public class DetailRdvEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JsonObject container = new JsonObject();
        JsonObject jsonClient = new JsonObject();
        Rdv rdvEnAttente = (Rdv) request.getSession().getAttribute("rdvEnAttente");

        System.out.println(rdvEnAttente);
        if (rdvEnAttente == null) {
            return;
        }

        container.addProperty("etat", rdvEnAttente.getEtat().toString());
        jsonClient.addProperty("nom", rdvEnAttente.getClient().getPrenom() + " " + rdvEnAttente.getClient().getNom());
        jsonClient.addProperty("dob", sdf.format(rdvEnAttente.getClient().getDateNaissance()));
        jsonClient.addProperty("adresse", rdvEnAttente.getClient().getAdressePostale());

        JsonObject jsonProfil = new JsonObject();
        jsonProfil.addProperty("totem", rdvEnAttente.getClient().getProfil().getAnimalTotem());
        jsonProfil.addProperty("zodiaque", rdvEnAttente.getClient().getProfil().getSigneZodiac());
        jsonProfil.addProperty("chinois", rdvEnAttente.getClient().getProfil().getSigneChinois());
        jsonProfil.addProperty("couleur", rdvEnAttente.getClient().getProfil().getCouleur());
        container.add("profil", jsonProfil);

        rdvEnAttente.getClient().getListeRdv().size();
        JsonArray jsonHistorique = new JsonArray();
        for (Rdv unRdv : rdvEnAttente.getClient().getListeRdv()) {
            if (unRdv.getEtat() == Etat.EFFECTUE) {
                JsonObject jsonRdv = new JsonObject();
                jsonRdv.addProperty("dateFin", sdf.format(unRdv.getDateHeureFin()));
                jsonRdv.addProperty("medium", unRdv.getMedium().getDenomination());
                jsonRdv.addProperty("employe", unRdv.getEmploye().getPrenom());
                jsonRdv.addProperty("cmt", unRdv.getCommentaire());
                jsonHistorique.add(jsonRdv);
            }
        }
        jsonClient.add("historique", jsonHistorique);
        container.add("client", jsonClient);

        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("denom", rdvEnAttente.getMedium().getDenomination());
        String profession = rdvEnAttente.getMedium().getClass().toString();
        jsonMedium.addProperty("profession", profession.substring(profession.lastIndexOf(".") + 1));
        jsonMedium.addProperty("presentation", rdvEnAttente.getMedium().getPresentation());
        container.add("medium", jsonMedium);

        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        out.println(gson.toJson(container));
        //gson.toJson(jsonClient, out);
        out.close();
    }

}
