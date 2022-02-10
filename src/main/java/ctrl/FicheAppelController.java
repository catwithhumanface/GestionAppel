package ctrl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FicheAppelDao;
import dao.FicheAppelService;
import dao.HibernateUtil;
import dao.UtilisateurSet;
import metier.Presence;
import metier.SeanceCours;
import metier.Utilisateur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "FicheAppelController", value = "/FicheAppelController")
public class FicheAppelController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if(m != null) {
            System.out.println(m);
            m = m.trim();
            if(m.equals("show")) {
                show(request, response);
            }else if(m.equals("save")){
                save(request, response);
            }else if(m.equals("validate")){
                validate(request, response);
            }
        }
    }

    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idsc = request.getParameter("seance");
        FicheAppelDao dao = new FicheAppelDao();
        String activateFlag = "true";
        Object dateValide = dao.checkDateValide(Integer.parseInt(idsc));
        if (dateValide!= null) {
            activateFlag = "false";
        }
        List listeAppel = dao.getListeAppel(Integer.parseInt(idsc));
        request.setAttribute("listeAppel", listeAppel);
        request.setAttribute("idSc", idsc);
        request.getRequestDispatcher("ficheAppel?activateFlag=" + activateFlag).forward(request, response);

    }

    //traduire result
    public HashMap<Integer, String> getResult(String result, String idSc){
        int totalCharacters = 0;
        char temp;
        for (int i = 0; i < result.length(); i++) {
            temp = result.charAt(i);
            if (temp == '/')
                totalCharacters++;
        }
        int indexI;
        int indexV;
        HashMap<Integer, String> map = new HashMap<>();
  
        for(int i=0; i<totalCharacters; i++) {
            indexI = result.indexOf("/");
            indexV = result.indexOf("!");
            String idStr = result.substring(indexI + 1, indexV);
            int id = Integer.parseInt(idStr);
            result = result.substring(indexV);
            indexV = result.indexOf("/");
            String value="";
            if(indexV !=-1){
                value = result.substring(1, indexV); //Absent
                result = result.substring(indexV);
            }else{
                value = result.substring(1);
            }
            map.put(id, value);

        }
        return map;
    }
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = request.getParameter("result");
        String idSc = request.getParameter("idSc");

        FicheAppelService service = FicheAppelService.getInstance();
        List<Presence> list = service.getAppel(Integer.parseInt(idSc));
        HashMap<Integer, String> map = getResult(result, idSc);

        for(Presence p : list){
            for(Map.Entry<Integer, String> elem : map.entrySet()){
                if(elem.getKey()==p.getEtudiant().getIdE()){
                    FicheAppelService service1 = FicheAppelService.getInstance();
                    service1.updateAppel(p, elem.getKey(), elem.getValue());
                    RequestDispatcher rd = request.getRequestDispatcher("cours.do?m=list");
                    rd.forward(request, response);
                }
            }
        }

    }
    public void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = request.getParameter("result");
        String idSc = request.getParameter("idSc");

        FicheAppelService service = FicheAppelService.getInstance();
        List<Presence> list = service.getAppel(Integer.parseInt(idSc));
        HashMap<Integer, String> map = getResult(result, idSc);

        for(Presence p : list){
            for(Map.Entry<Integer, String> elem : map.entrySet()){
                if(elem.getKey()==p.getEtudiant().getIdE()){
                    service.validateAppel(p, elem.getKey(), elem.getValue());
                }
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("cours.do?m=list");
        rd.forward(request, response);
    }
}
