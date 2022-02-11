package ctrl;

import dao.ListeEtudiantDao;
import dao.RecapHebdoService;
import metier.Etudiant;
import metier.Presence;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListeEtudiantController", value = "/ListeEtudiantController")

/**
 * Obtenir les requettes HTTP afin de renvoyer la page avec la récapitulatif de tous les étudiants
 * @author Zijing Zhou
 * @param request
 * @param response page à rediriger vers JSP listeEtudiant avec Map rapport avec instance de Etudiant comme clé et liste de calcul
 */
public class ListeEtudiantController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecapHebdoService rhs = new RecapHebdoService();

        ListeEtudiantDao led = new ListeEtudiantDao();
        Map<Etudiant, List<Integer>> rapport = new HashMap<>();
        List<Etudiant> etuList = led.getEtuList();
        for (int i = 0; i < etuList.size(); i++) {
            Etudiant e = etuList.get(i);
            try {
                Map<String, List<Integer>> rapportEtu = rhs.loadReportEtu(e.getIdE());
                List<Integer> somme = new ArrayList<>();
                somme.add(0);
                somme.add(0);
                somme.add(0);
                for (String date :
                        rapportEtu.keySet()) {
                    somme.set(2, somme.get(2) + rapportEtu.get(date).get(2));
                    somme.set(1, somme.get(1) + rapportEtu.get(date).get(1));
                    somme.set(0, somme.get(0) + rapportEtu.get(date).get(0));
                }
                rapport.put(e, somme);


            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        request.setAttribute("rapport", rapport);
        request.getRequestDispatcher("listeEtudiant").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
