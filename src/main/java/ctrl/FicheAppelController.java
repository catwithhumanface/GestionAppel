package ctrl;

import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "FicheAppelController", value = "/FicheAppelController")
public class FicheAppelController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idsc = request.getParameter("seance");

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            String activateFlag = "true";
            session.beginTransaction();

            List dateValide = session.createQuery("select sc.dateValidation " +
                    "from SeanceCours sc " +
                    "where sc.idSC=2").list();

            if (dateValide.get(0) != null) {
                activateFlag = "false";
            }

            Query query = session.createQuery("select e.idE, e.nom, e.prenom, p.etatP " +
                    "from Etudiant e,Presence p " +
                    "where e.idE=p.etudiant.idE " +
                    "and p.seanceCours.idSC=:idsc");
            query.setParameter("idsc", 2);
//            query.setParameter("idsc",Integer.parseInt(idsc));
            List listeAppel = query.list();

            request.setAttribute("listeAppel", listeAppel);
            request.getRequestDispatcher("ficheAppel?activateFlag=" + activateFlag).forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msg_e", ex.getMessage());
            request.getRequestDispatcher("ficheAppel?activateFlag=False").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
