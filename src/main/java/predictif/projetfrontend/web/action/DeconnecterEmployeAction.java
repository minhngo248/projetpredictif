/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lbezie
 */
public class DeconnecterEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        request.getSession().removeAttribute("idEmploye");
        request.getSession().invalidate();
    }

    
    
}
