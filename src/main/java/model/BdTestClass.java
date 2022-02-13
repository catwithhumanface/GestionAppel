package model;

import metier.Etudiant;
import metier.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BdTestClass {
    /*----- Format de date -----*/
//    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public static void createEtudiant() {
        try (Session s1 = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction tc = s1.beginTransaction();
            Etudiant e1 = new Etudiant("joohyun.ann@ut-capitole.fr", "123", "joohyun", "ann", "resources/images/avatar-01.jpg");
            Etudiant e2 = new Etudiant("tong.liu@ut-capitole.fr", "123", "tong", "liu", "resources/images/avatar-01.jpg");

            s1.save(e1);
            s1.save(e2);
            tc.commit();
        }
    }

    public static void createCours() {
        try (Session S2 = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction tc = S2.beginTransaction();
            Cours c1 = new Cours("Processus metier");
            Cours c2 = new Cours("Données");

            S2.save(c1);
            S2.save(c2);
            tc.commit();
        }
    }

    public static void createLesCours() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Etudiant e1 = new Etudiant("tong.liu@ut-capitole.fr", "123", "tong", "liu", "resources/images/avatar-01.jpg");
            Etudiant e2 = new Etudiant("joohyun.ann@ut-capitole.fr", "123", "joohyun", "ann", "resources/images/avatar-01.jpg");

            session.save(e1);
            session.save(e2);
            Cours c = new Cours("Management");
            Cours c2 = new Cours("Développment web");
            session.save(c);
            session.save(c2);
            e1.ajouteCours(c);
            e2.ajouteCours(c2);
            t.commit();
        }
    }

    public static void createSeance() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();
            Cours c1 = session.get(Cours.class, 1);
            Enseignant e1 = new Enseignant("nathalie.valles.ut-captitole.fr", "sdd", "nathalie", "valles", "resources/images/avatar-01.jpg");

            session.save(e1);

            SeanceCours sc1 = new SeanceCours(c1, e1, DF.parse("01-01-2021"), "9h30", "12h");
            SeanceCours sc2 = new SeanceCours(c1, e1, DF.parse("04-01-2021"), "8h30", "12h");

            session.save(sc1);
            session.save(sc2);

            t.commit();

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void createpresence() {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction t = session.beginTransaction();

            Etudiant e1 = session.get(Etudiant.class, 1);

            SeanceCours SC1 = session.get(SeanceCours.class, 1);

            e1.present(SC1, "Présent");

            t.commit();
        }
    }

    private static void affichage(List l) {
        Iterator e = l.iterator();
        while (e.hasNext()) {
            Object[] tab_obj = ((Object[]) e.next());

            for (Object obj : tab_obj)
                System.out.print(obj + " ");

            System.out.println();
        }
    }

    public static void main(String[] args) {
////        createCours();
////        createEtudiant();
////        createLesCours();
////        createSeance();
////        createpresence();
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        Utilisateur utilisateur = session.get(Etudiant.class, 3);
//        session.close();
//        List<SeanceCours> seanceFlag = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            seanceFlag.add(null);
//        }
//        String jourSemaine = "2022-02-07";
//        HomeService homeService = new HomeService();
//        List<List<SeanceCours>> edt = homeService.getEdt(jourSemaine, utilisateur, utilisateur.getTypeU());
//        for (int i = 0; i < edt.size(); i++) {
//            for (int j = 0; j < edt.get(i).size(); j++) {
//                SeanceCours sc = edt.get(i).get(j);
//                System.out.println(sc.getCours().getLibelles());
//                if (sc.getHeureDeb().equals("9")) {
//                    seanceFlag.set(i * 2, sc);
//                } else if (sc.getHeureDeb().equals("13")) {
//                    seanceFlag.set(i * 2 + 1, sc);
//                }
//            }
//        }
////            session.beginTransaction();
////
////            List listeAppel = session.createQuery("select e.idE, e.nom, e.prenom, p.etatP " +
////                                                    "from Etudiant e,Presence p " +
////                                                    "where e.idE=p.etudiant.idE").list();
////            BdTestClass.affichage(listeAppel);
////
////            List listeAppel2 = session.createSQLQuery("select * from Presence").list();
////
////            BdTestClass.affichage(listeAppel2);
////
////            List listeAppel3 = session.createQuery("select sc.dateValidation from SeanceCours sc where sc.idSC=2").list();
////            System.out.println(listeAppel3);
////            System.out.println(listeAppel3.get(0));
//
        ListeEtudiantDao led = new ListeEtudiantDao();
        System.out.println(led.getEtuList());
    }
}
