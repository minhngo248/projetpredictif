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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lbezie
 */
public class AuthentifierClientSerialisation extends Serialisation {
    @Override 
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        JsonObject jsonClient = new JsonObject(); //Creation un objet Json pour un client
        JsonObject jsonClientProp = new JsonObject();
        //Lecture des Attributs de la requête (stockés par Action)
        Object client = request.getAttribute("client");
        
        //Ajouter des propriétés au objet jsonClient
        if (client != null) {
            jsonClientProp.addProperty("idClient", request.getAttribute("idClient").toString());
            jsonClient.add("client", jsonClientProp);
        }
        
        //Formattage de la structure de données Json
        PrintWriter out = this.getWriter(response);
      
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        out.println( gson.toJson(jsonClient) );
        //gson.toJson(jsonClient, out);
        out.close();
    }
}
