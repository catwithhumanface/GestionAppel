package dao;

import metier.Cours;
import metier.Etudiant;
import metier.SeanceCours;
import metier.Utilisateur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;


public class CoursDao {


    public Utilisateur getUtilisateur(String username, String pass, String type){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        SQLQuery sqlQuery;
        sqlQuery = session.createSQLQuery("Select * from Utilisateur where Mail = :username and Mdp = :mdp and TypeU = :type").addEntity(Utilisateur.class);
        sqlQuery.setParameter("username", username);
        sqlQuery.setParameter("mdp", pass);
        sqlQuery.setParameter("type", type);
        Utilisateur utilisateur = (Utilisateur) sqlQuery.uniqueResult();
        session.close();
        return utilisateur;
    }

    public ArrayList<SeanceCours> getSeanceCoursList(int idCours) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        SQLQuery sqlQuery;
        sqlQuery = session.createSQLQuery("Select * from SeanceCours where IdC =:idc").addEntity(SeanceCours.class);

        sqlQuery.setParameter("idc", idCours);
        ArrayList<SeanceCours> seanceCoursList = (ArrayList<SeanceCours>) sqlQuery.getResultList();
        session.close();
        return seanceCoursList;
    }
    public Set<Cours> getCoursList(int ide) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        SQLQuery sqlQuery;
        sqlQuery = session.createSQLQuery("Select * from SeanceCours where Ide =:ide").addEntity(SeanceCours.class);

        sqlQuery.setParameter("ide", ide);
        ArrayList<SeanceCours> seanceCoursList = (ArrayList<SeanceCours>) sqlQuery.getResultList();
        HashSet<Cours> coursList = new HashSet<>();
        for(SeanceCours s : seanceCoursList){
            coursList.add(s.getCours());
        }
        session.close();
        return coursList;
    }

    public Set<Cours> getCoursListEtu(int ide) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Set<Cours> coursList;
        Etudiant etudiant = session.get(Etudiant.class,ide);

        coursList = etudiant.getLesCours();

        session.close();
        return coursList;
    }

}
