package ctrl;

import dao.CoursService;
import dao.UtilisateurService;
import dao.UtilisateurSet;
import metier.Cours;
import metier.SeanceCours;
import metier.Utilisateur;

import javax.persistence.DiscriminatorValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/cours.do")
public class CoursController extends HttpServlet {
    CoursService service = CoursService.getInstance();

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if(m != null) {
            System.out.println(m);
            m = m.trim();
            if(m.equals("sList")) {
                seanceList(request, response);
            }else if(m.equals("list")){
                coursList(request, response);
            }

        }else {

        }
    }

    private void seanceList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idCours = Integer.parseInt(request.getParameter("id"));

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "listeSeanceCours";
        if (utilisateur != null) {
            if(utilisateur.getTypeU().equals("Enseignant")){
                ArrayList<SeanceCours> seanceCoursList = service.getSeanceCoursList(idCours);
                request.setAttribute("SeanceCoursList", seanceCoursList);
            }
        }else{
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    private void coursList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "listeCours";
        if(utilisateur != null) {
            if(utilisateur.getTypeU().equals("Enseignant")){
                Set<Cours> coursList = service.getCoursList(utilisateur.getIdE());
                request.setAttribute("CoursList", coursList);
            }
            /*
            if(utilisateur.getTypeU().equals("Etudiant")){
                Set<Cours> coursListEtu = service.getCoursListEtu(utilisateur.getIdE());
                request.setAttribute("CoursListEtu", coursListEtu);
            }
            */
        }else{
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
