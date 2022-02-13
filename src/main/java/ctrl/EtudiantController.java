package ctrl;

import model.EtudiantService;
import model.UtilisateurSet;
import metier.Presence;
import metier.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/etudiant.do")
public class EtudiantController extends HttpServlet {
    EtudiantService service = EtudiantService.getInstance();

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if(m != null) {
            System.out.println(m);
            m = m.trim();
            if(m.equals("showab")) {
                showab(request, response);
            }else if(m.equals("search")){
                search(request, response);
            }
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("from");
        SimpleDateFormat DF = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        java.util.Date dateFrom = null;
        try{
            dateFrom = DF.parse(from);
        }catch (ParseException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "listeAbsences";
        if(utilisateur != null) {
            if(utilisateur.getTypeU().equals("Etudiant")){
                List<Presence> absenceList = service.getAbsenceListSearch(utilisateur.getIdE(), dateFrom);
                System.out.println("sssssss");
                request.setAttribute("AbsenceList", absenceList);
            }
        }else{
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    private void showab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "listeAbsences";
        if(utilisateur != null) {
            if(utilisateur.getTypeU().equals("Etudiant")){
                List<Presence> absenceList = service.getAbsenceList(utilisateur.getIdE());
                System.out.println("sssssss");
                request.setAttribute("AbsenceList", absenceList);
            }
        }else{
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
