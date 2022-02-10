package dao;

import metier.Presence;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

import org.hibernate.Transaction;

public class JustificatifDao {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        public  List listDepot(int idE) {
            Query query = session.createQuery("SELECT p.seanceCours.cours.libelles,p.seanceCours.heureDeb,p.seanceCours.heureFin,p.seanceCours.dateSeance,p.seanceCours.enseignant.nom,p.seanceCours.enseignant.prenom,p.etatValider,p.idP.idE,p.idP.idSC " +
                        "from Presence as p" +
                        " where p.etatP='Absent' and p.etudiant.idE =:idE and p.url is null");
            query.setParameter("idE", idE);
            List list1 = query.list();
            return list1;
        }

        public void enregistrerDepot(int idE,int idSC, String path){
            Query query = session.createQuery("from Presence as p where p.idP.idSC=:idSC and p.idP.idE=:idE");
            query.setParameter("idE",idE);
            query.setParameter("idSC",idSC);
            Presence presence = (Presence)query.list().get(0);
            presence.setUrl(path);
            presence.setEtatValider("false");
            session.update(presence);
            t.commit();
        }
}


