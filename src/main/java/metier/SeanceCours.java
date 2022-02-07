package metier;

import javax.persistence.*;
import java.util.*;

/**
 * Entité SéanceCours.
 */
@Entity(name = "SeanceCours")

public class SeanceCours {
    /*---attributes---*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSC")
    private int idSC;

    @Column(name = "HeureDeb")
    private String heureDeb;

    @Column(name = "HeureFin")
    private String heureFin;

    @Column(name = "DateSeance")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSeance;

    @Column(name = "DateValidation")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateValidation;

    /*Relation*/
    /*cours et séance cours*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdC")
    private Cours cours;

    public Map<Etudiant, Presence> getPresences() {
        return presences;
    }

    public void setPresences(Map<Etudiant, Presence> presences) {
        this.presences = presences;
    }

    /*--séance cours et enseignant--*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idE")
    private Enseignant enseignant;

    /*----- Presence -----*/
    @OneToMany(mappedBy = "seanceCours", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "IdE", updatable = false, insertable = false)
    private Map<Etudiant,Presence> presences= new HashMap(0);


    public SeanceCours(){}
    public SeanceCours(Cours cours,Enseignant enseignant,Date dateSeance,String heureDeb,String heureFin){
        this.dateSeance=dateSeance;
        this.heureDeb=heureDeb;
        this.heureFin=heureFin;
        this.cours = cours;
        this.enseignant = enseignant;
    }

    public int getIdSC() {
        return idSC;
    }

    public void setIdSC(int idSC) {
        this.idSC = idSC;
    }

    public String getHeureDeb() {
        return heureDeb;
    }

    public void setHeureDeb(String heureDeb) {
        this.heureDeb = heureDeb;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public Date getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(Date dateSeance) {
        this.dateSeance = dateSeance;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
}
