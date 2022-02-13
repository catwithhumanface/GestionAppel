package model;

import metier.Etudiant;
import metier.Presence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RecapHebdoService {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public String getLastMonday(String monday) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DF.parse(monday));
        Date thisMonday = DF.parse(monday);

        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date nextMonday = cal.getTime();
        return DF.format(nextMonday);
    }

    public Map<String,List<Integer>> loadReportEtu(int ide) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = DF.format(cal.getTime());
        RecapHebdoDao rhd = new RecapHebdoDao();

        Map<String,List<Integer>> res = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            List<Presence> ps = rhd.getSeancesEtu(monday, ide);
            List<Integer> somme = new ArrayList<>();
            somme.add(0);
            somme.add(0);
            somme.add(0);
            for (Presence p : ps) {
                if (p.getEtatP().equals("Retard")){
                    somme.set(2, somme.get(2)+1);
                } else if (p.getEtatP().equals("Absent")){
                    somme.set(1, somme.get(1)+1);
                } else if (p.getEtatP().equals("Present")){
                    somme.set(0, somme.get(0)+1);
                }
            }
            System.out.println(somme);
            res.put(monday,somme);

            monday = this.getLastMonday(monday);
        }
        return res;
    }

    public Etudiant loadEtu(int ide){
        RecapHebdoDao rhd = new RecapHebdoDao();
        return rhd.getEtu(ide);
    }
}
