package model;

import metier.Presence;
import metier.SeanceCours;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FicheAppelDao {


    public Object checkDateValide(int idsc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tc = session.beginTransaction();
        String activateFlag = "true";

        Query query = session.createQuery("select sc.dateValidation " +
                "from SeanceCours sc " +
                "where sc.idSC=:idsc");

        query.setParameter("idsc", idsc);
        Object obj = query.uniqueResult();
        session.close();
        return obj;
    }

    public List getListeAppel(int idsc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tc = session.beginTransaction();
        Query query = session.createQuery("select e.idE, e.nom, e.prenom, p.etatP,p.url, p.etudiant.urlPhoto  " +
                "from Etudiant e,Presence p " +
                "where e.idE=p.etudiant.idE " +
                "and p.seanceCours.idSC=:idsc");

        query.setParameter("idsc", idsc);

        List listeAppel = query.list();
        session.close();

        return listeAppel;
    }

    public List<Presence> getAppel(int idsc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tc = session.beginTransaction();
        Query query = session.createQuery("from Presence p where p.seanceCours.idSC=:idsc");
        query.setParameter("idsc", idsc);
        ArrayList<Presence> list = (ArrayList<Presence>) query.list();
        session.close();
        return list;
    }

    public void updateAppel(Presence p, int id, String value){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        p.setEtatP(value);
        session.update(p);
        session.getTransaction().commit();
    }

    public void validateAppel(Presence p, int id, String value){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        p.setEtatP(value);
        SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateStr = DF.format(date);
        SeanceCours sc = p.getSeanceCours();
        try{
            java.util.Date temp = DF.parse(dateStr);
            sc = p.getSeanceCours();
            sc.setDateValidation(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        session.update(p);
        session.update(sc);
        session.getTransaction().commit();
    }
}
