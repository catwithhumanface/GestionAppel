package dao;

import metier.Cours;
import metier.Etudiant;
import metier.Presence;
import metier.SeanceCours;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RecapHebdoDao {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public List<Presence> getSeancesEtu(String monday, int ide) throws ParseException {

        List seanceSemaine = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(DF.parse(monday));
        Date thisMonday = DF.parse(monday);

        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date nextMonday = cal.getTime();


        Etudiant etudiant = this.getEtu(ide);

        Map<SeanceCours, Presence> presenceMap = etudiant.getPresences();
        CoursDao coursDao = new CoursDao();

        for (SeanceCours sc : presenceMap.keySet()) {
            Date dateC = sc.getDateSeance();
            if (dateC.equals(thisMonday) || (dateC.after(thisMonday) && dateC.before(nextMonday))) {
                seanceSemaine.add(sc.getPresences().get(etudiant));
            }
        }

        return seanceSemaine;
    }

    public Etudiant getEtu(int ide){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        Etudiant etudiant = session.get(Etudiant.class, ide);
        session.close();
        return etudiant;
    }
}
