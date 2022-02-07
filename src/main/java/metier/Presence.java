package metier;

import javax.persistence.*;

/**
 * Entité Presence.
 */
@Entity
public class Presence implements java.io.Serializable{
    /**
     * Propriétés.
     */
    @EmbeddedId
    private PresenceID idP;

    @Column(name ="EtatP")
    private String etatP;

    /*Relation*/

    @ManyToOne
    @JoinColumn(name = "IdSC", insertable = false, updatable = false)	// "insertable" et "updatable" sont à "false" pour éviter la persistance.
    private SeanceCours seanceCours;												// Dans ce cas, le propriétaire de la relation est la collection côté @OneToMany.

    @ManyToOne
    @JoinColumn(name = "IdE", insertable = false, updatable = false)
    private Etudiant etudiant;


    /*Initialisation*/

    public Presence(){}
    public Presence(PresenceID idP,String etatP,SeanceCours sc1,Etudiant e1){
        this.etatP = etatP;
        this.idP = idP;
        this.seanceCours =sc1;
        this.etudiant = e1;
    }

    public String getEtatP() {
        return etatP;
    }

    public void setEtatP(String etatP) {
        this.etatP = etatP;
    }

    public PresenceID getIdP() {
        return idP;
    }

    public void setIdP(PresenceID idP) {
        this.idP = idP;
    }

    public SeanceCours getSeanceCours() {
        return seanceCours;
    }

    public void setSeanceCours(SeanceCours seanceCours) {
        this.seanceCours = seanceCours;
    }

   /* public Justificatif getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(Justificatif justificatif) {
        this.justificatif = justificatif;
    }*/

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
