package dao;

import metier.Cours;
import metier.Presence;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Transaction;

public class JustificatifDao {

        public  List listDepot(int idE) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("SELECT p.seanceCours.cours.libelles,p.seanceCours.heureDeb,p.seanceCours.heureFin,p.seanceCours.dateSeance,p.seanceCours.enseignant.nom,p.seanceCours.enseignant.prenom,p.etatValider,p.idP.idE,p.idP.idSC " +
                        "from Presence as p" +
                        " where p.etatP='Absent' and p.etudiant.idE =:idE and p.url is null");
            query.setParameter("idE", idE);
            List list1 = query.list();
            session.close();
            return list1;
        }

        public void enregistrerDepot(int idE,int idSC, String path){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Presence as p where p.idP.idSC=:idSC and p.idP.idE=:idE");
            query.setParameter("idE",idE);
            query.setParameter("idSC",idSC);
            Presence presence = (Presence)query.list().get(0);
            presence.setUrl(path);
            presence.setEtatValider("false");
            session.update(presence);
            // envoyer l'alerte par mail
            SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String dateStr = DF.format(date);
            Mail.sendMailDepotJustifi(presence.getEtudiant().getPrenom(), presence.getEtudiant().getNom(),
                    presence.getEtudiant().getMail(), presence.getSeanceCours().getCours().getLibelles(), DF.format(presence.getSeanceCours().getDateSeance()));

            t.commit();
        }
        public Set<Presence> toValider(){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Query query = session.createQuery( "from Presence as p where p.etatValider='false'");
            List<Presence> listV= query.list();
            HashSet<Presence> setV = new HashSet<>();
            for(Presence p : listV){
                setV.add(p);
            }
            session.close();
            return setV;
        }

        public void validerJ(int idE,int idSC){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Query query = session.createQuery("from Presence as p where p.idP.idSC=:idSC and p.idP.idE=:idE");
            query.setParameter("idE",idE);
            query.setParameter("idSC",idSC);
            Presence presence = (Presence)query.list().get(0);
            presence.setEtatValider("true");
            presence.setEtatP("Absence justifiée");
            session.update(presence);
            t.commit();

        }
}


