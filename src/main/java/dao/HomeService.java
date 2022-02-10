package dao;

import metier.SeanceCours;
import metier.Utilisateur;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeService {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
    List<List<SeanceCours>> edt = new ArrayList<>();

    public List getEdt(String jourSemaine, Utilisateur utilisateur) {
        HomeDao homeDao = new HomeDao();
        try {
            List<SeanceCours> seanceCoursList = homeDao.getSeances(jourSemaine, utilisateur.getIdE());

            Calendar cal = Calendar.getInstance();
            cal.setTime(DF.parse(jourSemaine));
            Date dateLundi = DF.parse(jourSemaine);

            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date dateMardi = cal.getTime();

            cal.add(Calendar.DAY_OF_MONTH, 2);
            Date dateMercredi = cal.getTime();

            cal.add(Calendar.DAY_OF_MONTH, 3);
            Date dateJeudi = cal.getTime();

            cal.add(Calendar.DAY_OF_MONTH, 4);
            Date dateVendredi = cal.getTime();


            List<SeanceCours> lundi = new ArrayList<>();
            List<SeanceCours> mardi = new ArrayList<>();
            List<SeanceCours> mercredi = new ArrayList<>();
            List<SeanceCours> jeudi = new ArrayList<>();
            List<SeanceCours> vendredi = new ArrayList<>();

            for (SeanceCours sc : seanceCoursList) {
                if (sc.getDateSeance().equals(dateLundi)) {
                    lundi.add(sc);
                } else if (sc.getDateSeance().equals(dateMardi)) {
                    mardi.add(sc);
                } else if (sc.getDateSeance().equals(dateMercredi)) {
                    mercredi.add(sc);
                } else if (sc.getDateSeance().equals(dateJeudi)) {
                    jeudi.add(sc);
                } else if (sc.getDateSeance().equals(dateVendredi)) {
                    vendredi.add(sc);
                }
            }

            edt.add(lundi);
            edt.add(mardi);
            edt.add(mercredi);
            edt.add(jeudi);
            edt.add(vendredi);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return edt;
    }

}
