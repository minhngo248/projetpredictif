/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bbbbb
 */
public class EmployeConsulterRdvAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        Long idEmp = Long.parseLong(request.getParameter("idEmp"));
        request.getSession().setAttribute("idE", idEmp);
    }
}
