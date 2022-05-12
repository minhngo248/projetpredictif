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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Rdv;

/**
 *
 * @author bbbbb
 */
public class DetailClientSerialisation extends Serialisation {

    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonClient = new JsonObject(); //Creation un objet Json pour un client
        JsonObject jsonClientProp = new JsonObject();

        //Lecture des Attributs de la requête (stockés par Action)
        Object client = request.getAttribute("client");

        JsonArray jsonListeRdv = new JsonArray();

        //Ajouter des propriétés au objet jsonClient
        if (client != null) {
            jsonClient.addProperty("session", Boolean.TRUE);
            jsonClientProp.addProperty("nom", request.getAttribute("nom").toString());
            jsonClientProp.addProperty("prenom", request.getAttribute("prenom").toString());
            jsonClientProp.addProperty("dob", request.getAttribute("dob").toString());
            jsonClientProp.addProperty("adresse", request.getAttribute("adresse").toString());
            jsonClientProp.addProperty("tel", request.getAttribute("tel").toString());
            jsonClientProp.addProperty("mail", request.getAttribute("mail").toString());
            jsonClient.add("client", jsonClientProp);

            JsonObject jsonProfil = new JsonObject();
            jsonProfil.addProperty("totem", request.getAttribute("totem").toString());
            jsonProfil.addProperty("zodiaque", request.getAttribute("zodiaque").toString());
            jsonProfil.addProperty("chinois", request.getAttribute("chinois").toString());
            jsonProfil.addProperty("couleur", request.getAttribute("couleur").toString());
            jsonClient.add("profil", jsonProfil);


            List<Rdv> listeRdv = (List<Rdv>)request.getAttribute("listeRdv");
            for (Rdv rdv:listeRdv) {
                JsonObject jsonRdv = new JsonObject();
                jsonRdv.addProperty("date", rdv.getDateHeureDebut().toString());
                jsonRdv.addProperty("medium", rdv.getMedium().toString());
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
        out.println(gson.toJson(jsonClient));
        //gson.toJson(jsonClient, out);
        out.close();
    }
}
