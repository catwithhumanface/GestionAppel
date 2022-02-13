package model;

import metier.Cours;
import metier.SeanceCours;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeDao {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public List getSeancesEns(String monday, int ide) throws ParseException {

        List<SeanceCours> seanceSemaine = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(DF.parse(monday));
        Date thisMonday = DF.parse(monday);

        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date nextMonday = cal.getTime();


        CoursDao coursDao = new CoursDao();

        List<SeanceCours> listeSeance = coursDao.getSeanceEnsList(ide);
        for (SeanceCours sc : listeSeance) {
            Date dateC = sc.getDateSeance();
            if (dateC.equals(thisMonday) || (dateC.after(thisMonday) && dateC.before(nextMonday))) {
                seanceSemaine.add(sc);
            }
        }

        return seanceSemaine;
    }

    public List getSeancesEtu(String monday, int ide) throws ParseException {

        List<SeanceCours> seanceSemaine = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(DF.parse(monday));
        Date thisMonday = DF.parse(monday);

        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date nextMonday = cal.getTime();


        CoursDao coursDao = new CoursDao();

        Set<Cours> listeCours = coursDao.getCoursListEtu(ide);
        System.out.println(listeCours);
        for (Cours cours : listeCours) {
            Set<SeanceCours> scs = cours.getLesSeance();
            System.out.println(cours.getLibelles());
            for (SeanceCours sc : scs) {
                Date dateC = sc.getDateSeance();
//                System.out.println(DF.format(sc.getDateSeance().getTime()));
                if (dateC.equals(thisMonday) || (dateC.after(thisMonday) && dateC.before(nextMonday))) {
//                    System.out.println("Oh YES!!");
                    seanceSemaine.add(sc);
                }
            }
        }
        return seanceSemaine;
    }
}
