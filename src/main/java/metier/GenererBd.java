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
            Cours c2 = new Cours("Developpment web");
            Cours c3 = new Cours("Accompagnement client");

            session.save(c);
            session.save(c2);
            session.save(c3);

            e1.ajouteCours(c);
            e1.ajouteCours(c2);
            e2.ajouteCours(c);
            e2.ajouteCours(c2);
            e3.ajouteCours(c2);
            e3.ajouteCours(c);
            e4.ajouteCours(c2);
            e4.ajouteCours(c);
            e4.ajouteCours(c3);

            Enseignant en1 = new Enseignant("nathalie.valles.ut-captitole.fr", "sdd", "nathalie", "valles");
            Enseignant en2 = new Enseignant("ravat.franck.ut-captitole.fr", "123", "franck", "ravat");

            session.save(en1);
            session.save(en2);

            SeanceCours sc1 = new SeanceCours(c, en1, DF.parse("01-01-2021"), "9", "12");
            SeanceCours sc2 = new SeanceCours(c2, en2, DF.parse("04-01-2021"), "9", "12");
            SeanceCours sc3 = new SeanceCours(c, en1, DF.parse("06-01-2021"), "9", "12");
            SeanceCours sc4 = new SeanceCours(c2, en2, DF.parse("08-01-2021"), "9", "12");
            SeanceCours sc5 = new SeanceCours(c3, en2, DF.parse("02-02-2021"), "13", "15");
            SeanceCours sc6 = new SeanceCours(c,en2, DF.parse("07-02-2022"),"9","12");
            SeanceCours sc8 = new SeanceCours(c2,en2, DF.parse("07-02-2022"),"13","15");
            SeanceCours sc9 = new SeanceCours(c3,en2, DF.parse("08-02-2022"),"9","12");
            SeanceCours sc10 = new SeanceCours(c,en2, DF.parse("08-02-2022"),"13","15");
            SeanceCours sc11 = new SeanceCours(c2,en2, DF.parse("09-02-2022"),"9","12");
            SeanceCours sc12 = new SeanceCours(c,en2, DF.parse("09-02-2022"),"13","15");
            SeanceCours sc13 = new SeanceCours(c3,en1, DF.parse("10-02-2022"),"09","12");
            SeanceCours sc14 = new SeanceCours(c2,en1, DF.parse("10-02-2022"),"13","15");
            SeanceCours sc15 = new SeanceCours(c2,en1, DF.parse("11-02-2022"),"09","12");

            session.save(sc1);
            session.save(sc2);
            session.save(sc3);
            session.save(sc4);
            session.save(sc5);
            session.save(sc6);
            session.save(sc8);
            session.save(sc9);
            session.save(sc10);
            session.save(sc11);
            session.save(sc12);
            session.save(sc13);
            session.save(sc14);
            session.save(sc15);

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
            SeanceCours SC5 = session.get(SeanceCours.class, 5);
            SeanceCours SC6 = session.get(SeanceCours.class, 6);
            SeanceCours SC8 = session.get(SeanceCours.class, 7);
            SeanceCours SC9 = session.get(SeanceCours.class, 8);
            SeanceCours SC10 = session.get(SeanceCours.class, 9);
            SeanceCours SC11 = session.get(SeanceCours.class, 10);
            SeanceCours SC12 = session.get(SeanceCours.class, 11);
            SeanceCours SC13 = session.get(SeanceCours.class, 12);
            SeanceCours SC14 = session.get(SeanceCours.class, 13);
            SeanceCours SC15 = session.get(SeanceCours.class, 14);

            e1.present(SC1,"Present");
            e2.present(SC1,"Retard");
            e3.present(SC1,"Absent");
            e4.present(SC1,"Retard");

            e1.present(SC2,"Absent");
            e2.present(SC2,"Present");
            e3.present(SC2,"Absent");
            e4.present(SC2,"Retard");

            e1.present(SC3,"Absent");
            e2.present(SC3,"Present");
            e3.present(SC3,"Retard");
            e4.present(SC3,"Present");

            e1.present(SC4,"Absent");
            e2.present(SC4,"Present");
            e4.present(SC4,"Retard");
            e3.present(SC4,"Absent");

            e1.present(SC5,"Absent");
            e2.present(SC5,"Present");
            e4.present(SC5,"Retard");
            e3.present(SC5,"Absent");

            e1.present(SC6,"Absent");
            e2.present(SC6,"Present");
            e4.present(SC6,"Retard");
            e3.present(SC6,"Absent");

            e1.present(SC8,"Absent");
            e2.present(SC8,"Present");
            e4.present(SC8,"Retard");
            e3.present(SC8,"Absent");

            e1.present(SC9,"Absent");
            e2.present(SC9,"Present");
            e4.present(SC9,"Retard");
            e3.present(SC9,"Absent");

            e1.present(SC10,"Absent");
            e2.present(SC10,"Present");
            e4.present(SC10,"Retard");
            e3.present(SC10,"Absent");

            e1.present(SC11,"Absent");
            e2.present(SC11,"Present");
            e4.present(SC11,"Retard");
            e3.present(SC11,"Absent");

            e1.present(SC12,"Absent");
            e2.present(SC12,"Present");
            e4.present(SC12,"Retard");
            e3.present(SC12,"Absent");

            e1.present(SC13,"Absent");
            e2.present(SC13,"Present");
            e4.present(SC13,"Retard");
            e3.present(SC13,"Absent");

            e1.present(SC14,"Absent");
            e2.present(SC14,"Present");
            e4.present(SC14,"Retard");
            e3.present(SC14,"Absent");

            e1.present(SC15,"Absent");
            e2.present(SC15,"Present");
            e4.present(SC15,"Retard");
            e3.present(SC15,"Absent");

            t.commit();
        }
    }

    public static void main(String[] args){
        createLesCours();
        createLesPresence();
    }
}