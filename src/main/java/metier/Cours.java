package metier;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité Cours.
 */
@Entity(name = "Cours")
public class Cours implements java.io.Serializable {
    /**
     * Propriétés.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdC")
    private int idC;

    @Column(name = "Libelles")
    private String libelles;

    /**
     * Relations.
     */
    /*---Cours Etudiant---*/
    @ManyToMany(mappedBy = "lesCours")
    private Set<Etudiant> etudiants = new HashSet(0);

    /*---Cours Séance de cours---*/
    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SeanceCours> lesSeance = new HashSet(0);

    public int getIdC() {
        return idC;
    }


    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getLibelles() {
        return libelles;
    }

    public void setLibelles(String libelles) {
        this.libelles = libelles;
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
    public Set<SeanceCours> getLesSeance() {return lesSeance;}
    public void setLesSeance(Set<SeanceCours> lesSeance) {this.lesSeance = lesSeance;}
    /*---Initialisation---*/
    public Cours(){}
    public Cours(String libelles){
        this.libelles = libelles;
    }

    public static void login(){
        // verify the password and id
    }

}