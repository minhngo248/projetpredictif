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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;
import metier.modele.Rdv;

/**
 *
 * @author bbbbb
 */
public class DetailClientSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonClient = new JsonObject(); //Creation un objet Json pour un client
        JsonObject jsonClientProp = new JsonObject();

        //Lecture des Attributs de la requête (stockés par Action)
        Client client = (Client) request.getAttribute("client");

        JsonArray jsonListeRdv = new JsonArray();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        //Ajouter des propriétés au objet jsonClient
        if (client != null) {
            jsonClient.addProperty("session", Boolean.TRUE);
            jsonClientProp.addProperty("nom", client.getNom());
            jsonClientProp.addProperty("prenom", client.getPrenom());
            jsonClientProp.addProperty("dob", sdf.format(client.getDateNaissance()));
            jsonClientProp.addProperty("adresse", client.getAdressePostale());
            jsonClientProp.addProperty("tel", client.getTelephone());
            jsonClientProp.addProperty("mail", client.getMail());
            jsonClient.add("client", jsonClientProp);

            JsonObject jsonProfil = new JsonObject();
            jsonProfil.addProperty("totem", client.getProfil().getAnimalTotem());
            jsonProfil.addProperty("zodiaque", client.getProfil().getSigneZodiac());
            jsonProfil.addProperty("chinois", client.getProfil().getSigneChinois());
            jsonProfil.addProperty("couleur", client.getProfil().getCouleur());
            jsonClient.add("profil", jsonProfil);


           List <Rdv> listeRdv=(List <Rdv>) request.getAttribute("listeRdv");
            for (Rdv rdv:listeRdv) {
                JsonObject jsonRdv = new JsonObject();
                jsonRdv.addProperty("dateDemande", sdf.format(rdv.getDateHeureDemande()));
                jsonRdv.addProperty("medium", rdv.getMedium().getDenomination());
                jsonListeRdv.add(jsonRdv);
            }
            jsonClient.add("listeRdv", jsonListeRdv);
        }
        else {
            jsonClient.addProperty("session", Boolean.FALSE);
        }

        //Formattage de la structure de données Json
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        //out.println(gson.toJson(jsonClient));
        gson.toJson(jsonClient, out);
        out.close();
    }
}
