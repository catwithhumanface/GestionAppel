package dao;

import metier.Cours;
import metier.Enseignant;
import metier.SeanceCours;
import metier.Utilisateur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.SQLDeleteAll;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UtilisateurDao {


    public int checkLogin(String username, String pass, String type) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        SQLQuery sqlQuery;
        sqlQuery = session.createSQLQuery("Select * from Utilisateur where Mail = :username").addEntity(Utilisateur.class);

        sqlQuery.setParameter("username", username);
        Utilisateur utilisateur = (Utilisateur) sqlQuery.uniqueResult();

        if(utilisateur == null){
            session.close();
            return UtilisateurSet.NO_ID;
        }else{
            sqlQuery = session.createSQLQuery("Select * from Utilisateur where Mail = :username and Mdp = :mdp and TypeU = :type ").addEntity(Utilisateur.class);
            sqlQuery.setParameter("username", username);
            sqlQuery.setParameter("mdp", pass);
            sqlQuery.setParameter("type", type);
            utilisateur = (Utilisateur) sqlQuery.uniqueResult();
            session.close();
            if(utilisateur != null){
                return UtilisateurSet.PASS;
            }else{
                return UtilisateurSet.NO_PWD;
            }
        }
    }

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
}
