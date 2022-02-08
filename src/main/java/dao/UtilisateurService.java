package dao;

import metier.Utilisateur;

public class UtilisateurService {
    private UtilisateurDao dao;
    private static final UtilisateurService instance = new UtilisateurService();
    private UtilisateurService(){
        dao = new UtilisateurDao();
    }

    public static UtilisateurService getInstance(){
        return instance;
    }

    public int checkLogin(String username, String pass, String type){
        int rCode = dao.checkLogin(username, pass, type);
        return rCode;
    }

    public Utilisateur getUtilisateur(String username, String pass, String type){
        Utilisateur utilisateur = dao.getUtilisateur(username, pass, type);
        return utilisateur;
    }
}
