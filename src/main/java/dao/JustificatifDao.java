package dao;

import com.mysql.cj.QueryResult;
import metier.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.SQLDeleteAll;
import dao.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
public class JustificatifDao {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();


        public List<String> listDepot(int idE) {

            try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

                session.beginTransaction();
                List list1 = session.createQuery("SELECT p.etudiant.nom,p.etudiant.prenom,p.seanceCours.cours.libelles,p.seanceCours.heureDeb,p.seanceCours.heureFin,p.seanceCours.dateSeance,p.seanceCours.enseignant.nom,p.seanceCours.enseignant.prenom " +
                        "from Etudiant as e,Enseignant as en,SeanceCours as sc,Presence as p" +
                        " where p.etatP='absent' and p.etudiant.idE = idE").list();

                return list1;

            } catch (Exception ex) {

            }
            return null;
        }

        public void enregistrerDepot(){

        }
}


