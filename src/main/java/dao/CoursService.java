package dao;

import metier.Cours;
import metier.SeanceCours;
import metier.Utilisateur;

import java.util.ArrayList;
import java.util.Set;

public class CoursService {
    private CoursDao dao;
    private static final CoursService instance = new CoursService();
    private CoursService(){
        dao = new CoursDao();
    }

    public static CoursService getInstance(){
        return instance;
    }

    public ArrayList<SeanceCours> getSeanceCoursList(int idCours){
        ArrayList<SeanceCours> seanceCoursList = dao.getSeanceCoursList(idCours);
        return seanceCoursList;
    }
    public Set<Cours> getCoursList(int ide){
        Set<Cours> coursList = dao.getCoursList(ide);
        return coursList;
    }

    public Set<Cours> getCoursListEtu(int ide){
        Set<Cours> coursListEtu = dao.getCoursListEtu(ide);
        return coursListEtu;
    }

    public Utilisateur getUtilisateur(String username, String pass, String type){
        Utilisateur utilisateur = dao.getUtilisateur(username, pass, type);
        return utilisateur;
    }
}
