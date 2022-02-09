package dao;

import metier.Cours;
import metier.SeanceCours;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HomeDao {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public List getSeances(String monday, int ide) throws ParseException {

        List<SeanceCours> seanceSemaine = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(DF.parse(monday));
        Date thisMonday = DF.parse(monday);

        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date nextMonday = cal.getTime();


        CoursDao coursDao = new CoursDao();

        Set listeCours = coursDao.getCoursList(ide);

        for (Object o : listeCours) {
            Cours cours = (Cours) o;
            Set scs = cours.getLesSeance();
//            System.out.println(cours.getLibelles());
            for (Object osc : scs) {
                SeanceCours sc = (SeanceCours) osc;
                Date dateC = sc.getDateSeance();
//                System.out.println(DF.format(sc.getDateSeance().getTime()));
                if (dateC.equals(thisMonday)|| (dateC.after(thisMonday) && dateC.before(nextMonday))) {
//                    System.out.println("Oh YES!!");
                    seanceSemaine.add(sc);
                }
            }
        }
        return seanceSemaine;
    }

    public List getListeAppel(int idsc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select e.idE, e.nom, e.prenom, p.etatP " +
                "from Etudiant e,Presence p " +
                "where e.idE=p.etudiant.idE " +
                "and p.seanceCours.idSC=:idsc");

        query.setParameter("idsc", idsc);

        List listeAppel = query.list();
        session.close();
        return listeAppel;
    }
}
