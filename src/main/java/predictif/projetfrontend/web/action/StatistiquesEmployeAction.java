/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.ServicePredictif;

/**
 *
 * @author lbezie
 */
public class StatistiquesEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicePredictif service = new ServicePredictif();
        
        
        List<Map.Entry<Medium, Long>> topMediums=service.statsTopMediums(5);
        List<Map.Entry<Employe, Long>> topEmployes=service.statsTopEmployes(5);
        
        request.setAttribute("topMediums",topMediums);
        request.setAttribute("topEmployes", topEmployes);
        
        
    }

   
    
}
