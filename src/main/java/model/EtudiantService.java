package model;

import metier.Presence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtudiantService {
    private final EtudiantDao dao;
    private static final EtudiantService instance = new EtudiantService();
    private EtudiantService(){
        dao = new EtudiantDao();
    }

    public static EtudiantService getInstance(){
        return instance;
    }

    public List<Presence> getAbsenceList(int ide){
        ArrayList<Presence> absenceList = (ArrayList<Presence>) dao.getAbsenceList(ide);
        return absenceList;
    }

    public List<Presence> getAbsenceListSearch(int ide, Date from){
        ArrayList<Presence> absenceList = (ArrayList<Presence>) dao.getAbsenceListSearch(ide, from);
        return absenceList;
    }

}
