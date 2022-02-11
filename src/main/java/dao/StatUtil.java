package dao;

public class StatUtil {
    public String nom;
    public String prenom;
    public long nb;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public long getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public StatUtil(String nom, String prenom, long nb) {
        this.nom = nom;
        this.prenom = prenom;
        this.nb = nb;
    }
    public StatUtil(){}
}
