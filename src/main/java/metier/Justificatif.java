package metier;

import javax.persistence.*;
import java.util.*;

/**
 * Entité Justificatif.
 */
@Entity(name = "Justificatif")

public class Justificatif {
    /**
     * Propriétés.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJ")
    private int idJ;

    @Column(name = "URL")
    private String url;

    @Column(name = "EtatValide")
    private String etatValide;

    /*Relation*/
    /*justificatif et Seance cours*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdSC")
    private SeanceCours seanceCours;

    /*justificatif et Seance cours*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdE")
    private Etudiant etudiant;



    /*initialisation*/

    public String getEtatValide() {
        return etatValide;
    }

    public void setEtatValide(String etatValide) {
        this.etatValide = etatValide;
    }

    public Justificatif(){}
    public Justificatif(String url,Etudiant e1, SeanceCours sc1){
        this.url = url;
        this.seanceCours = sc1;
        this.etudiant = e1;
    }

    public int getIdJ() {return idJ;}

    public void setIdJ(int idJ) {
        this.idJ = idJ;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justificatif that = (Justificatif) o;
        return idJ == that.idJ;
    }

    public SeanceCours getSeanceCours() {
        return seanceCours;
    }

    public void setSeanceCours(SeanceCours seanceCours) {
        this.seanceCours = seanceCours;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJ);
    }
}
