/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Medium;
import metier.service.ServicePredictif;

/**
 *
 * @author bbbbb
 */
public class TrierMediumProfessionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicePredictif service = new ServicePredictif();
        List<Medium> listeMedium = service.listeMediums();
        Collections.sort(listeMedium, (m1, m2) -> m1.getClass().getTypeName().compareTo(m2.getClass().getTypeName()) );
        request.setAttribute("listeMedTri", listeMedium);
    }
    
}
