package ctrl;

import dao.JustificatifService;
import metier.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "CtrlJustificatif", value = "/ctrlJustificatif")
public class CtrlJustificatif extends HttpServlet {
    /**
     * Générer la liste des absences pour un étudiant puisse déposer un justificatif
     * @author Tong LIU
     * @param request id des étudiants qui ont une absence
     * @param response Les séances qui n'ont pas de justificatif
     * @return page dépot de justificatif
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utilisateur U1= (Utilisateur)session.getAttribute("Utilisateur");
        int idE = U1.getIdE();
        JustificatifService jS = JustificatifService.getInstance();
        List listJ = jS.afficher(idE);
        request.setAttribute("listJustifi", listJ);
        request.getRequestDispatcher("depotJustif").forward(request, response);
    }
}