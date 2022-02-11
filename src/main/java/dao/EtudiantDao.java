package dao;

import metier.Cours;
import metier.Presence;
import metier.SeanceCours;
import metier.Utilisateur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;


public class EtudiantDao {


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

    public List<Presence> getAbsenceList(int ide) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tc = session.beginTransaction();
        Presence p = new Presence();
        org.hibernate.query.Query query = session.createQuery("from Presence p where p.etudiant.idE=:idE and p.etatP=:etatP");
        query.setParameter("idE", ide);
        query.setParameter("etatP", "Absent");
        ArrayList<Presence> list = (ArrayList<Presence>) query.list();
        session.close();
        return list;
    }


    public List<Presence> getAbsenceListSearch(int ide, Date from) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tc = session.beginTransaction();
        Presence p = new Presence();
        org.hibernate.query.Query query = session.createQuery("from Presence p where p.etudiant.idE=:idE " +
                "and p.etatP=:etatP " +
                "and p.seanceCours.dateSeance > :fromDate ");
        query.setParameter("idE", ide);
        query.setParameter("etatP", "Absent");
        query.setParameter("fromDate", from);
        ArrayList<Presence> list = (ArrayList<Presence>) query.list();
        session.close();
        return list;
    }

}
