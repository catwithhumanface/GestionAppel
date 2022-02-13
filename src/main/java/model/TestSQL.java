package model;

import java.util.List;

public class TestSQL {
    public static void main(String[] args){
        //JustificatifService jus = JustificatifService.getInstance();
        //jus.afficher(1);
        StatDao sta = new StatDao();
        System.out.println(sta.nombreAvg(1));
        System.out.println(sta.tauxAbs(1));


        List listr  = sta.etudiantAbs(1);


    }

}

