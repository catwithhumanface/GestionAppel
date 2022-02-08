package ctrl;

import dao.JustificatifConstant;
import dao.JustificatifService;
import dao.JustificatifDao;
import metier.*;

import javax.servlet.RequestDispatcher;
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
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utilisateur U1= (Utilisateur)session.getAttribute("Utilisateur");
        int idE = U1.getIdE();
        JustificatifService jS = new JustificatifService();
        List<String> listJ = jS.afficher(idE);

        request.setAttribute("listJustifi", listJ);
        request.getRequestDispatcher("depotJustif").forward(request, response);
    }
}