package dao;

import metier.Cours;
import metier.SeanceCours;
import metier.Utilisateur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;


public class CoursDao {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction t = session.beginTransaction();
    SQLQuery sqlQuery;

    public Utilisateur getUtilisateur(String username, String pass, String type){
        sqlQuery = session.createSQLQuery("Select * from Utilisateur where Mail = :username and Mdp = :mdp and TypeU = :type").addEntity(Utilisateur.class);
        sqlQuery.setParameter("username", username);
        sqlQuery.setParameter("mdp", pass);
        sqlQuery.setParameter("type", type);
        Utilisateur utilisateur = (Utilisateur) sqlQuery.uniqueResult();
        return utilisateur;
    }

    public ArrayList<SeanceCours> getSeanceCoursList(int idCours) {
        sqlQuery = session.createSQLQuery("Select * from seancecours where IdC =:idc").addEntity(SeanceCours.class);

        sqlQuery.setParameter("idc", idCours);
        ArrayList<SeanceCours> seanceCoursList = (ArrayList<SeanceCours>) sqlQuery.getResultList();

        if(seanceCoursList != null){
            return seanceCoursList;
        }else{
            return null;
        }
    }
    public Set<Cours> getCoursList(int ide) {
        sqlQuery = session.createSQLQuery("Select * from seancecours where Ide =:ide").addEntity(SeanceCours.class);
        sqlQuery.setParameter("ide", ide);
        ArrayList<SeanceCours> seanceCoursList = (ArrayList<SeanceCours>) sqlQuery.getResultList();
        HashSet<Cours> coursList = new HashSet<>();
        for(SeanceCours s : seanceCoursList){
            coursList.add(s.getCours());
        }
        return coursList;
    }

    public Set<Cours> getCoursListEtu(int ide) {
        Query query = session.createQuery("Select e.lesCours from Etudiant as e where e.idE =:ide");
        query.setParameter("ide", ide);
        ArrayList<Cours> coursListEtu = (ArrayList<Cours>) query.getResultList();
        HashSet<Cours> coursListEtuSet = new HashSet<>();
        for(Cours c : coursListEtu){
            coursListEtuSet.add(c);
        }
        return coursListEtuSet;
    }

}
