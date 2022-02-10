package ctrl;

import dao.HomeDao;
import dao.HomeService;
import metier.SeanceCours;
import metier.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "HomeController", value = "/HomeController")
public class HomeController extends HttpServlet {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        HttpSession session = request.getSession();
        String jourSemaine = request.getParameter("semaine");
        if (jourSemaine==null){
            jourSemaine = DF.format(calendar.getTime());
        }
        System.out.println(jourSemaine);

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");

        HomeService homeService = new HomeService();

        List<SeanceCours> seanceFlag = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            seanceFlag.add(null);
        }



        List<List<SeanceCours>> edt = homeService.getEdt(jourSemaine, utilisateur, utilisateur.getTypeU());
        for (int i = 0; i < edt.size(); i++) {
            for (int j = 0; j < edt.get(i).size(); j++) {
                SeanceCours sc = edt.get(i).get(j);
                if (sc.getHeureDeb().equals("9")){
                    seanceFlag.set(i*2,sc);
                } else if (sc.getHeureDeb().equals("13")){
                    seanceFlag.set(i*2+1,sc);
                }
            }
        }
        request.setAttribute("lundi",jourSemaine);
        request.setAttribute("edt",seanceFlag);
        request.getRequestDispatcher("home").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
