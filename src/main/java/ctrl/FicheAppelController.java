package ctrl;

import dao.FicheAppelDao;
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
        FicheAppelDao dao = new FicheAppelDao();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            String activateFlag = "true";
            session.beginTransaction();

            idsc = "2";
            List dateValide = dao.checkDateValide(Integer.parseInt(idsc));

            if (dateValide.get(0) != null) {
                activateFlag = "false";
            }

            List listeAppel = dao.getListeAppel(Integer.parseInt(idsc));

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
