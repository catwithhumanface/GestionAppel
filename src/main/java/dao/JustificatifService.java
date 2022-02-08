package dao;

import java.util.List;

public class JustificatifService {
    public List<String> afficher(int idE){
        JustificatifDao dao = new JustificatifDao();
        List<String> listJ = dao.listDepot(idE);
        return listJ;
    }
}
