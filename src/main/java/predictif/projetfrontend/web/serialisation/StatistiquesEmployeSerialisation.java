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
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;
import metier.modele.Medium;

/**
 *
 * @author lbezie
 */
public class StatistiquesEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject container = new JsonObject();
        JsonArray jsonListeMediums = new JsonArray();
        JsonArray jsonListeEmployes = new JsonArray();

        List<Map.Entry<Medium, Long>> topMediums = (List<Map.Entry<Medium, Long>>) request.getAttribute("topMediums");
        List<Map.Entry<Employe, Long>> topEmployes = (List<Map.Entry<Employe, Long>>) request.getAttribute("topEmployes");
        
        for (Map.Entry<Medium,Long> medium:topMediums){
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("nbConsultation", medium.getValue());
            jsonMedium.addProperty("denomination", medium.getKey().getDenomination());

            jsonListeMediums.add(jsonMedium);
      
        }
        
        for (Map.Entry<Employe,Long> employe:topEmployes){
            System.out.println(employe);
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("nbConsultation", employe.getValue());
            jsonEmploye.addProperty("nom", employe.getKey().getPrenom()+" "+employe.getKey().getNom());

            jsonListeEmployes.add(jsonEmploye);
      
        }
        container.add("listeMediums", jsonListeMediums);
        container.add("listeEmployes", jsonListeEmployes);

        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
