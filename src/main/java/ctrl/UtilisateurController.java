package ctrl;

import dao.UtilisateurService;
import dao.UtilisateurSet;
import metier.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member.do")
public class UtilisateurController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if(m != null) {
            System.out.println(m);
            m = m.trim();
            if(m.equals("form")) {
                form(request, response);
            }else if(m.equals("login")){
                login(request, response);
            }else{

            }

        }else {

        }
    }

    private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "index";
        response.sendRedirect(view);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String type = request.getParameter("typeUser");
        System.out.println(username + " "+ pass + " " + type);
        if(username != null) username = username.trim();
        if(pass != null) pass = pass.trim();

        UtilisateurService service = UtilisateurService.getInstance();
        int rCode = service.checkLogin(username, pass, type);
        request.setAttribute("rCode", rCode);
        String view = "index";

        if(rCode== UtilisateurSet.PASS){
            Utilisateur utilisateur = service.getUtilisateur(username, pass, type);
            HttpSession session = request.getSession();
            session.setAttribute("Utilisateur", utilisateur);
            view = "home";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
}
