package ctrl;

import dao.HomeService;
import dao.UtilisateurService;
import metier.SeanceCours;
import metier.Utilisateur;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class localTest {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);


        String jourSemaine = "2020-02-07";
        if (jourSemaine==null){
            jourSemaine = DF.format(calendar.getTime());
        }
        System.out.println(jourSemaine);

        UtilisateurService service = UtilisateurService.getInstance();
        Utilisateur utilisateur = service.getUtilisateur("nathalie.valles@ut-captitole.fr", "sdd", "Enseignant");


        HomeService homeService = new HomeService();

        List<SeanceCours> seanceFlag = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            seanceFlag.add(null);
        }
        System.out.println(utilisateur.getNom()+utilisateur.getPrenom());

        List<List<SeanceCours>> edt = homeService.getEdt(jourSemaine, utilisateur);
        for (int i = 0; i < edt.size(); i++) {
            for (int j = 0; j < edt.get(i).size(); j++) {
                SeanceCours sc = edt.get(i).get(j);
                if (sc.getHeureDeb().equals("9")){
                    System.out.println(sc+"123");
                    seanceFlag.set(i*2,sc);
                } else if (sc.getHeureDeb().equals("13")){
                    seanceFlag.set(i*2+1,sc);
                    System.out.println(sc);
                }
            }
        }
        System.out.println(seanceFlag);
    }
}
