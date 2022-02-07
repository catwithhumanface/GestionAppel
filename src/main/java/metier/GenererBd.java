package metier;


import dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GenererBd {
    /*
    Générer un table entre les cours et les étudiants,un table de séances
    * */
    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");

    public static void createLesCours() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Etudiant e1 = new Etudiant("tong.liu@ut-capitole.fr", "123", "tong", "liu");
            Etudiant e2 = new Etudiant("joohyun.ann@ut-capitole.fr", "123", "joohyun", "ann");
            Etudiant e3 = new Etudiant("zijing.zhou@ut-capitole.fr", "123", "zijing", "zhou");
            Etudiant e4 = new Etudiant("fares.megali@ut-capitole.fr", "123", "fares", "megali");

            Scolarite s1 = new Scolarite("elaine.fontaine@ut-capitole.fr", "123", "elaine", "fontaine");
            session.save(e1);
            session.save(e2);
            session.save(e3);
            session.save(e4);

            session.save(s1);

            Cours c = new Cours("Management");
            Cours c2 = new Cours("Développment web");

            session.save(c);
            session.save(c2);

            e1.ajouteCours(c);
            e1.ajouteCours(c2);
            e2.ajouteCours(c);
            e2.ajouteCours(c2);
            e3.ajouteCours(c2);
            e3.ajouteCours(c);
            e4.ajouteCours(c2);
            e4.ajouteCours(c);

            Enseignant en1 = new Enseignant("nathalie.valles.ut-captitole.fr", "sdd", "nathalie", "valles");
            Enseignant en2 = new Enseignant("ravat.franck.ut-captitole.fr", "123", "franck", "ravat");

            session.save(en1);
            session.save(en2);

            SeanceCours sc1 = new SeanceCours(c, en1, DF.parse("01-01-2021"), "9h30", "12h");
            SeanceCours sc3 = new SeanceCours(c, en1, DF.parse("06-01-2021"), "9h30", "12h");

            SeanceCours sc2 = new SeanceCours(c2, en2, DF.parse("04-01-2021"), "8h30", "12h");
            SeanceCours sc4 = new SeanceCours(c2, en2, DF.parse("08-01-2021"), "9h30", "12h");

            session.save(sc1);
            session.save(sc2);
            session.save(sc3);
            session.save(sc4);
            t.commit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void createLesPresence(){
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            Etudiant e1 = session.get(Etudiant.class, 1);
            Etudiant e2 = session.get(Etudiant.class, 2);
            Etudiant e3 = session.get(Etudiant.class, 3);
            Etudiant e4 = session.get(Etudiant.class, 4);


            SeanceCours SC1 = session.get(SeanceCours.class, 1);
            SeanceCours SC2 = session.get(SeanceCours.class, 2);
            SeanceCours SC3 = session.get(SeanceCours.class, 3);
            SeanceCours SC4 = session.get(SeanceCours.class, 4);

            e1.present(SC1,"Présent");
            e2.present(SC1,"Retard");

            e1.present(SC2,"Absent");
            e2.present(SC2,"Présent");

            e3.present(SC3,"Retard");
            e3.present(SC3,"Présent");
            e4.present(SC4,"Retard");
            e4.present(SC4,"Absent");
            e1.present(SC1,"Présent");

            t.commit();
        }
    }
    public static void main(String[] args){
      createLesCours();
      createLesPresence();
    }
}



