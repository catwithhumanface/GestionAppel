package dao;
import java.util.List;

public class JustificatifService {
    private final JustificatifDao dao;
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

}



