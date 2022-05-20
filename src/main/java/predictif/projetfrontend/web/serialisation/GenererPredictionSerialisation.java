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

/**
 *
 * @author lbezie
 */
public class GenererPredictionSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> resultat = (List<String>) request.getAttribute("predictions");
        
        JsonObject jsonPred = new JsonObject();
        jsonPred.addProperty("amour", resultat.get(0));
        jsonPred.addProperty("sante", resultat.get(1));
        jsonPred.addProperty("travail", resultat.get(2));
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        out.println(gson.toJson(jsonPred));
        //gson.toJson(jsonClient, out);
        out.close();
    }

    
    
}
