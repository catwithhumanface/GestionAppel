package dao;
import metier.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TestSQL {
    public static void main(String[] args){
        JustificatifService jus = JustificatifService.getInstance();
        jus.afficher(1);
    }

}

