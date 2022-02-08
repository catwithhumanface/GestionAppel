package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FicheAppelDao {



    public List checkDateValide(int idsc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        String activateFlag = "true";

        Query query = session.createQuery("select sc.dateValidation " +
                "from SeanceCours sc " +
                "where sc.idSC=:idsc");

        query.setParameter("idsc", 2);

        List dateValide = query.list();
        session.close();
        return dateValide;
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
