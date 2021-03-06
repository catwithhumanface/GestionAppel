package ctrl;

import model.JustificatifService;
import metier.Presence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


@WebServlet(name = "CtrlValiderJ", value = "/ctrlValiderJ")
public class CtrlValiderJ extends HttpServlet {
    JustificatifService jS = JustificatifService.getInstance();
    /**
     * Conserver le dossier déposé par un étudiant pour un cours
     * @author Tong LIU
     * @param request id d'une séance et id d'un étudiant
     * @param response changement de l'état d'un justificatif et l'état d'un étudiant
     * @return page de validation des justificatifs
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Set<Presence> setJ = jS.afficherJustifi();
        Presence p = new Presence();
        request.setAttribute("setJustifi", setJ);
        request.getRequestDispatcher("validerJustifica").forward(request, response);
    }
    protected void validerJustifi(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idE = Integer.parseInt(request.getParameter("idE"));
        int idSC = Integer.parseInt(request.getParameter("idSC"));
        jS.validerDao(idE,idSC);
        response.sendRedirect("ctrlValiderJ");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        validerJustifi(request,response);

    }

}