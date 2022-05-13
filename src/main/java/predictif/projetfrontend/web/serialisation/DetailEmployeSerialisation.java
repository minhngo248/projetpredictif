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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;
import metier.modele.Rdv;

/**
 *
 * @author bbbbb
 */
public class DetailEmployeSerialisation extends Serialisation {

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
        JsonObject jsonEmploye = new JsonObject(); //Creation un objet Json pour un client
        JsonObject jsonEmployeProp = new JsonObject();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Lecture des Attributs de la requête (stockés par Action)
        Employe employe = (Employe) request.getAttribute("employe");

        JsonArray jsonListeRdv = new JsonArray();

        //Ajouter des propriétés au objet jsonClient
        if (employe != null) {
            jsonEmploye.addProperty("session", Boolean.TRUE);
            jsonEmployeProp.addProperty("nom", employe.getNom());
            jsonEmployeProp.addProperty("prenom", employe.getPrenom());
            jsonEmployeProp.addProperty("etat", employe.getEtat().toString());
            jsonEmployeProp.addProperty("tel", employe.getTelephone());
            jsonEmployeProp.addProperty("mail", employe.getMail());
            jsonEmploye.add("employe", jsonEmployeProp);

            
            for (Rdv rdv : employe.getListeRdv()) {
                System.out.println(rdv);

                JsonObject jsonRdv = new JsonObject();
                jsonRdv.addProperty("date", sdf.format(rdv.getDateHeureDebut()));
                jsonRdv.addProperty("medium", rdv.getMedium().getDenomination());
                jsonRdv.addProperty("client", rdv.getClient().getPrenom() + " " + rdv.getClient().getNom());
                jsonListeRdv.add(jsonRdv);
                System.out.println(rdv);
            }
            jsonEmploye.add("listeRdv", jsonListeRdv);
        } else {
            jsonEmploye.addProperty("session", Boolean.FALSE);
        }

        //Formattage de la structure de données Json
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        out.println(gson.toJson(jsonEmploye));
        //gson.toJson(jsonClient, out);
        out.close();
    }
}
