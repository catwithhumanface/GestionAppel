package metier;


import model.HibernateUtil;
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
            Etudiant e1 = new Etudiant("tong.liu@ut-capitole.fr", "123", "tong", "liu", "resources/images/avatar-01.jpg");
            Etudiant e2 = new Etudiant("joohyun.ann@ut-capitole.fr", "123", "joohyun", "ann", "resources/images/avatar-01.jpg");
            Etudiant e3 = new Etudiant("zijing.zhou@ut-capitole.fr", "123", "zijing", "zhou", "resources/images/avatar-01.jpg");
            Etudiant e4 = new Etudiant("fares.megari@ut-capitole.fr", "123", "fares", "megari", "resources/images/avatar-01.jpg");

            Scolarite s1 = new Scolarite("elodie.fontana@ut-capitole.fr", "123", "elodie", "fontana", "resources/images/avatar-01.jpg");
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

            Enseignant en1 = new Enseignant("nathalie.valles@ut-capitole.fr", "sdd", "nathalie", "valles", "resources/images/nathalie.png");
            Enseignant en2 = new Enseignant("ravat.franck@ut-capitole.fr", "123", "franck", "ravat", "resources/images/avatar-01.jpg");

            session.save(en1);
            session.save(en2);

            SeanceCours sc1 = new SeanceCours(c, en1, DF.parse("22-01-2022"), "9", "12");
            SeanceCours sc2 = new SeanceCours(c2, en2, DF.parse("23-01-2022"), "9", "12");
            SeanceCours sc3 = new SeanceCours(c, en1, DF.parse("24-01-2022"), "9", "12");
            SeanceCours sc4 = new SeanceCours(c2, en2, DF.parse("25-01-2022"), "9", "12");
            SeanceCours sc5 = new SeanceCours(c3, en2, DF.parse("31-01-2022"), "13", "15");
            SeanceCours sc6 = new SeanceCours(c,en2, DF.parse("07-02-2022"),"9","12");
            SeanceCours sc8 = new SeanceCours(c2,en2, DF.parse("07-02-2022"),"13","15");
            SeanceCours sc9 = new SeanceCours(c3,en2, DF.parse("08-02-2022"),"9","12");
            SeanceCours sc10 = new SeanceCours(c,en2, DF.parse("08-02-2022"),"13","15");
            SeanceCours sc11 = new SeanceCours(c2,en2, DF.parse("09-02-2022"),"9","12");
            SeanceCours sc12 = new SeanceCours(c,en2, DF.parse("09-02-2022"),"13","15");
            SeanceCours sc13 = new SeanceCours(c3,en1, DF.parse("10-02-2022"),"09","12");
            SeanceCours sc14 = new SeanceCours(c2,en1, DF.parse("10-02-2022"),"13","15");
            SeanceCours sc15 = new SeanceCours(c2,en1, DF.parse("11-02-2022"),"09","12");
            SeanceCours sc16= new SeanceCours(c,en1, DF.parse("14-02-2022"),"13","15");
            SeanceCours sc17= new SeanceCours(c3,en2, DF.parse("15-02-2022"),"9","12");
            SeanceCours sc18= new SeanceCours(c2,en2, DF.parse("16-02-2022"),"13","15");
            SeanceCours sc19= new SeanceCours(c,en1, DF.parse("17-02-2022"),"9","12");
            SeanceCours sc20= new SeanceCours(c2,en1, DF.parse("18-02-2022"),"13","15");
            SeanceCours sc21= new SeanceCours(c,en1, DF.parse("21-02-2022"),"9","12");
            SeanceCours sc22= new SeanceCours(c3,en2, DF.parse("22-02-2022"),"13","15");
            SeanceCours sc23= new SeanceCours(c2,en1, DF.parse("23-02-2022"),"9","12");
            SeanceCours sc24= new SeanceCours(c3,en1, DF.parse("24-02-2022"),"13","15");
            SeanceCours sc25= new SeanceCours(c,en1, DF.parse("25-02-2022"),"9","12");
            SeanceCours sc26= new SeanceCours(c2,en1, DF.parse("26-02-2022"),"13","15");

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
            session.save(sc16);
            session.save(sc17);
            session.save(sc18);
            session.save(sc19);
            session.save(sc20);
            session.save(sc21);
            session.save(sc22);
            session.save(sc23);
            session.save(sc24);
            session.save(sc25);
            session.save(sc26);

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
            SeanceCours SC16 = session.get(SeanceCours.class, 15);
            SeanceCours SC17 = session.get(SeanceCours.class, 16);
            SeanceCours SC18 = session.get(SeanceCours.class, 17);
            SeanceCours SC19 = session.get(SeanceCours.class, 18);
            SeanceCours SC20 = session.get(SeanceCours.class, 19);
            SeanceCours SC21 = session.get(SeanceCours.class, 20);
            SeanceCours SC22 = session.get(SeanceCours.class, 21);
            SeanceCours SC23 = session.get(SeanceCours.class, 22);
            SeanceCours SC24 = session.get(SeanceCours.class, 23);
            SeanceCours SC25 = session.get(SeanceCours.class, 24);
            SeanceCours SC26 = session.get(SeanceCours.class, 25);

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

            e1.present(SC16,"Absent");
            e2.present(SC16,"Present");
            e4.present(SC16,"Retard");
            e3.present(SC16,"Absent");
            e1.present(SC17,"Absent");
            e2.present(SC17,"Present");
            e4.present(SC17,"Retard");
            e3.present(SC17,"Absent");
            e1.present(SC18,"Absent");
            e2.present(SC18,"Present");
            e4.present(SC18,"Retard");
            e3.present(SC18,"Absent");
            e1.present(SC19,"Absent");
            e2.present(SC19,"Present");
            e4.present(SC19,"Retard");
            e3.present(SC19,"Absent");
            e1.present(SC20,"Absent");
            e2.present(SC20,"Present");
            e4.present(SC20,"Retard");
            e3.present(SC20,"Absent");
            e1.present(SC21,"Absent");
            e2.present(SC21,"Present");
            e4.present(SC21,"Retard");
            e3.present(SC21,"Absent");
            e1.present(SC22,"Absent");
            e2.present(SC22,"Present");
            e4.present(SC22,"Retard");
            e3.present(SC22,"Absent");
            e1.present(SC23,"Absent");
            e2.present(SC23,"Present");
            e4.present(SC23,"Retard");
            e3.present(SC23,"Absent");
            e1.present(SC24,"Absent");
            e2.present(SC24,"Present");
            e4.present(SC24,"Retard");
            e3.present(SC24,"Absent");
            e1.present(SC25,"Absent");
            e2.present(SC25,"Present");
            e4.present(SC25,"Retard");
            e3.present(SC25,"Absent");
            e1.present(SC26,"Absent");
            e2.present(SC26,"Present");
            e4.present(SC26,"Retard");
            e3.present(SC26,"Absent");

            t.commit();
        }
    }

    public static void main(String[] args){
        createLesCours();
        createLesPresence();
    }
}