package ctrl;

import dao.HibernateUtil;
import org.hibernate.Session;

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
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();

            List listeAppel = session.createQuery("select e.idE, e.nom, e.prenom, p.etatP " +
                    "from Etudiant e,Presence p " +
                    "where e.idE=p.etudiant.idE").list();
            request.setAttribute("listeAppel", listeAppel);
            request.getRequestDispatcher("ficheAppel?activateFlag=true").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msg_e", ex.getMessage());
            request.getRequestDispatcher("ficheAppel?activateFlag=false").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
