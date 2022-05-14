/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Rdv;

/**
 *
 * @author lbezie
 */
public class ObtenirConsultationSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Rdv rdv = (Rdv) request.getAttribute("rdv");
        JsonObject jsonRdv = new JsonObject();
        JsonObject jsonProp = new JsonObject();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        if (rdv != null) {
            jsonProp.addProperty("idRdv", rdv.getId());
            jsonProp.addProperty("heureDebut", sdf.format(rdv.getDateHeureDebut()));
            jsonRdv.add("rdv", jsonProp);
        } else {
            jsonRdv.add("rdv", null);
        }
        
        //Formattage de la structure de données Json
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        out.println( gson.toJson(jsonRdv) );
        //gson.toJson(jsonClient, out);
        out.close();
    }
}
