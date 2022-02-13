package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StatDao {
    public float nombreAvg(int idC) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("select count(sc.idSC)from SeanceCours sc " +
                "where  sc.cours.idC=:idC and sc.dateValidation is not null ");
        Query query1 = session.createQuery("select count(*)from Presence as p,SeanceCours sc " +
                "where p.idP.idSC = sc.idSC and sc.dateValidation is not null and sc.cours.idC=:idC and p.etatP ='Absent'");
        query1.setParameter("idC",idC);
        query.setParameter("idC", idC);
        float nbAvg;
        nbAvg = ((Number) (query1.list().get(0))).floatValue()/ ((Number) (query.list().get(0))).floatValue();
        session.close();
        return nbAvg;

    }

    public float tauxAbs(int idC){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query queryAbs = session.createQuery("select count(*)from Presence as p,SeanceCours as sc " +
                "where p.idP.idSC = sc.idSC and sc.cours.idC=:idC and p.etatP ='Absent' and sc.dateValidation is not null");
        Query queryEtu = session.createQuery("select count(e.idE)from Etudiant as e, SeanceCours as sc " +
                "where sc.cours.idC=:idC and (sc.dateValidation is not null) and sc.cours.idC=:idC ");
        queryAbs.setParameter("idC",idC);
        queryEtu.setParameter("idC", idC);
        float res;
        res = ((Number) (queryAbs.list().get(0))).floatValue()/ ((Number) (queryEtu.list().get(0))).floatValue();
        session.close();
        return res;
    }

    public List<StatUtil> etudiantAbs(int idC){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        List<StatUtil> listRes ;
        Query query = session.createQuery("select new model.StatUtil(e.prenom, e.nom, count(*)) " +
                "from Presence as p, Etudiant as e, SeanceCours as sc where p.idP.idSC = sc.idSC and sc.cours.idC=:idC and p.etatP ='Absent' and sc.dateValidation is not null " +
                "group by e.nom,e.prenom having count(*)>3");
        query.setParameter("idC",idC);
        listRes = query.list();
        session.close();
        return listRes;
    }
}
