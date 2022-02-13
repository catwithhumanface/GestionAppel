package ctrl;

import model.*;
import metier.Presence;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "FicheAppelController", value = "/FicheAppelController")
public class FicheAppelController extends HttpServlet {
    /**
     * Générer la liste des séances des cours en fonctionne de l'utilsateur
     * @author Zijing Zhou
     * @param request id de séance de cours
     * @param response un list de présence
     * @return page fiche d'appel pour un séance de cours
     */
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
        List<Presence> appels = dao.getAppel(Integer.parseInt(idsc));
        List listeAppel = dao.getListeAppel(Integer.parseInt(idsc));
        request.setAttribute("listeAppel", listeAppel);
        request.setAttribute("idSc", idsc);
        request.setAttribute("sc",appels.get(0).getSeanceCours());
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
    /**
     * Sauvegarder les états des présences
     * @author Zijing Zhou
     * @param request fiche d'appel
     * @param response etat changé en enregisré en BD
     * @return page list de cours
     */
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
                    RequestDispatcher rd = request.getRequestDispatcher("cours.do?m=list&source=cours");
                    rd.forward(request, response);
                }
            }
        }

    }
    /**
     * Générer la liste des séances des cours en fonctionne de l'utilsateur
     * @author Zijing Zhou
     * @param request fiche d'appel
     * @param response etat changé en validé en BD, les présences de cette fiche ne peut plus changé
     * @return page list de cours
     */
    public void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = request.getParameter("result");
        String idSc = request.getParameter("idSc");

        FicheAppelService service = FicheAppelService.getInstance();
        List<Presence> list = service.getAppel(Integer.parseInt(idSc));
        HashMap<Integer, String> map = getResult(result, idSc);

        Mail mail = new Mail();

        SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateStr = DF.format(date);

        for(Presence p : list){
            for(Map.Entry<Integer, String> elem : map.entrySet()){
                if(elem.getKey()==p.getEtudiant().getIdE()){
                    service.validateAppel(p, elem.getKey(), elem.getValue());
                    if(elem.getValue().equals("Absent")){
                        mail.sendEmailAbsence(p.getEtudiant().getMail(), p.getSeanceCours().getCours().getLibelles(), DF.format(p.getSeanceCours().getDateSeance()));
                    }
                }
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("cours.do?m=list&source=cours");
        rd.forward(request, response);
    }
}
