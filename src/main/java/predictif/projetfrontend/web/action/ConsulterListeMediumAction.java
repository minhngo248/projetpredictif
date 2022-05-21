/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Medium;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class ConsulterListeMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        String tri = request.getParameter("tri");
        ServicePredictif service = new ServicePredictif();
        List<Medium> listeMedium = service.listeMediums();
        
        request.setAttribute("listeMedium", listeMedium);
    }
}
