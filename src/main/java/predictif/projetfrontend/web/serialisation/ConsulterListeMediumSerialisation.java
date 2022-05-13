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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Medium;

/**
 *
 * @author lbezie
 */
public class ConsulterListeMediumSerialisation extends Serialisation {


    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonMed = new JsonObject();
       JsonArray jsonListeMedium = new JsonArray();
        
       List<Medium> listeMedium=(List<Medium>) request.getAttribute("listeMedium");
       for (Medium medium:listeMedium) {
                System.out.println(medium);
                JsonObject jsonMedium = new JsonObject();
    
                jsonMedium.addProperty("presentation", medium.getPresentation());
                jsonMedium.addProperty("denomination", medium.getDenomination());
                jsonMedium.addProperty("idMedium", medium.getId());
                jsonListeMedium.add(jsonMedium);
            }
        jsonMed.add("listeMed", jsonListeMedium);
        
        //Formattage de la structure de données Json
        PrintWriter out = this.getWriter(response);
      
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        out.println( gson.toJson(jsonMed) );
        //gson.toJson(jsonClient, out);
        out.close();
    }
    
       
    
}
