package dao;

import metier.Cours;
import metier.Etudiant;
import metier.SeanceCours;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListeEtudiantDao {

    public List<Etudiant> getEtuList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("select e.idE " +
                "from Etudiant e");
        List<Etudiant> etuList = new ArrayList<>();
        List ideList = query.list();
        System.out.println(ideList);
        for (Object o : ideList) {
            Integer i = (Integer)o;
            Etudiant etudiant = session.get(Etudiant.class,i);
            etuList.add(etudiant);
        }
        session.close();
        return etuList;
    }
}
