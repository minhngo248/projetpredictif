/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web;

import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import predictif.projetfrontend.web.action.Action;
import predictif.projetfrontend.web.action.AuthentifierClientAction;
import predictif.projetfrontend.web.action.DetailClientAction;
import predictif.projetfrontend.web.action.InscrireClientAction;
import predictif.projetfrontend.web.serialisation.AuthentifierClientSerialisation;
import predictif.projetfrontend.web.serialisation.DetailClientSerialisation;
import predictif.projetfrontend.web.serialisation.InscrireClientSerialisation;
import predictif.projetfrontend.web.serialisation.Serialisation;

/**
 *
 * @author bbbbb
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(true);

        String todo = request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "connecter": {
                action = new AuthentifierClientAction();
                serialisation = new AuthentifierClientSerialisation();
            }
            break;

            case "inscription": {
                action = new InscrireClientAction();
                serialisation = new InscrireClientSerialisation();
            }
            break;
            
            case "consulter-detail-client": {
                action = new DetailClientAction();
                serialisation = new DetailClientSerialisation();
            }
            break;
        }

        if (action != null && serialisation != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        } else {
            response.sendError(400, "Bad Request (pas d'action ou de serialisation pour traiter cette requête");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
