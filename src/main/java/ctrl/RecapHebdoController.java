package ctrl;

import dao.RecapHebdoService;
import metier.Etudiant;
import metier.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RecapHebdoController", value = "/RecapHebdoController")

/**
 * Obtenir les requettes HTTP contient l'id de étudiant afin de renvoyer son page de récapilative hebdomadaire
 * @author Zijing Zhou
 * @param request paramètres entrants avec la demande qui contient l'identifiant de l'étutiant pour la récap
 * @param response page à rediriger vers JSP recapHebdo avec Map rappportEtu et instance de l'étudiant dans l'attribute
 */
public class RecapHebdoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ide = request.getParameter("ide");

        RecapHebdoService rhs = new RecapHebdoService();

        try {
            Map<String,List<Integer>> rapportEtu = rhs.loadReportEtu(Integer.parseInt(ide));
            Etudiant etudiant = rhs.loadEtu(Integer.parseInt(ide));
            request.setAttribute("rapportEtu",rapportEtu);
            request.setAttribute("etudiant",etudiant);
            request.getRequestDispatcher("recapHebdo").forward(request, response);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
