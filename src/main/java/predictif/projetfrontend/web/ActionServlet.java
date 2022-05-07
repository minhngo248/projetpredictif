/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.projetfrontend.web;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import predictif.projetfrontend.web.action.Action;
import predictif.projetfrontend.web.action.AuthentifierClientAction;
import predictif.projetfrontend.web.action.InscrireClientAction;
import predictif.projetfrontend.web.serialisation.AuthentifierClientSerialisation;
import predictif.projetfrontend.web.serialisation.InscrireClientSerialisation;
import predictif.projetfrontend.web.serialisation.Serialisation;

/**
 *
 * @author bbbbb
 */
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        
        String todo=request.getParameter("todo");
        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "connecter": {
                action = new AuthentifierClientAction();
                serialisation = new AuthentifierClientSerialisation();
            }
            break;
            
            case "inscription":{
                action=new InscrireClientAction();
                serialisation=new InscrireClientSerialisation();
            }
        }

        if (action != null && serialisation != null) {

            action.executer(request);
            serialisation.serialiser(request, response);

        } else {
            response.sendError(400, "Bad Request (pas d'action ou de serialisation pour traiter cette requête");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
