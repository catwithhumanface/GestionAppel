package dao;
import metier.Cours;
import metier.Presence;

import java.util.List;
import java.util.Set;

public class JustificatifService {
    private JustificatifDao dao;
    private static final JustificatifService instance = new JustificatifService();
    private JustificatifService(){
        dao = new JustificatifDao();
    }
    public static JustificatifService getInstance(){
        return instance;
    }

    public List afficher(int idE){
        List listJ =  dao.listDepot(idE);
        return listJ;
    }
    public void Insert(int idE, int idSC, String path){
        dao.enregistrerDepot(idE,idSC,path);
    }
    public Set afficherJustifi(){
        Set<Presence> setJ =  dao.toValider();
        return setJ;
    }
    public void validerDao(int idE, int idSC){
        dao.validerJ(idE,idSC);
    }
}



