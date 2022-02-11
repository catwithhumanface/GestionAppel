package ctrl;

import dao.*;
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
import java.util.List;
import java.util.Set;

@WebServlet("/cours.do")
public class CoursController extends HttpServlet {
    CoursService service = CoursService.getInstance();

    /**
     * Obtenir les paramètres afin d'exécuter les méthodes correspendantes
     * @author Joohyun Ann
     * @param request paramètres entrants avec la demande
     * @param response page à rediriger
     */
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
            else if(m.equals("sStatic")){
                System.out.println(m);
                stats(request,response);
            }

        }else {

        }
    }

    /**
     * Générer la liste des séances des cours en fonctionne de l'utilsateur
     * @author Joohyun Ann
     * @param request id de cours
     * @param response seanceCoursList
     * @return page index ou page seanceCoursList
     */
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

    /**
     * Générer la liste des cours en fonctionne de l'utilsateur
     * @author Joohyun Ann
     * @param request source pour diriger vers la liste des cours pour statistique ou pour la fiche d'appel
     * @param response source, coursList, rCode
     * @return page index ou page listeCours
     */
    private void coursList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "listeCours";
        String source = request.getParameter("source");
        if(utilisateur != null) {
            if(utilisateur.getTypeU().equals("Enseignant")){
                Set<Cours> coursList = service.getCoursList(utilisateur.getIdE());
                request.setAttribute("CoursList", coursList);
            }
            if(source != null){
                request.setAttribute("source",source);
            }
        }else{
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    /**
     * Générer la liste des statistiques de la presence des étudiants
     * @author Joohyun Ann
     * @param request id de cours, source
     * @param response Etudiantlist, absences en moyenne, taux d'absence, rCode
     * @return page index ou page statiEnseignant
     */
    private void stats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idCours = Integer.parseInt(request.getParameter("id"));
        System.out.println(idCours);
        String source = request.getParameter("source");
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "statiEnseignant";
        if (utilisateur != null) {
            if(utilisateur.getTypeU().equals("Enseignant")){
                System.out.println("ss");
                StatDao dao = new StatDao();
                List<StatUtil> EtudiantList = dao.etudiantAbs(idCours);
                request.setAttribute("EtudiantList", EtudiantList);
                float avgabs = dao.nombreAvg(idCours);
                float tauxabs= dao.tauxAbs(idCours);
                request.setAttribute("absAvg", avgabs);
                request.setAttribute("tauxAvg", tauxabs);
                if(source != null){
                    request.setAttribute("source",source);
                }
            }
        }else{
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }


}
