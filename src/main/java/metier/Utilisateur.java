package metier;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 * Entité Employe.
 */
@Entity(name = "Utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypeU", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("utilisateur")
public class Utilisateur implements java.io.Serializable {
    /**
     * Propriétés.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idE")
    private int idE;

    @Column(name = "Mail")
    private String mail;

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    @Column(name = "Mdp")
    private String mdp;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "Nom")
    private String nom;

    public String getMail() {return mail;}

    public void setMail(String mail) {this.mail = mail;}

    public String getMdp() {return mdp;}

    public void setMdp(String mdp) {this.mdp = mdp;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    @Override
    public String toString() {
        return "Utilisateur{" + "idE=" + idE + ", mail='" + mail + '\'' + ", mdp='" + mdp + '\'' + ", prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + '}';
    }

    /*---Initialisation---*/
    public Utilisateur(){}

    public Utilisateur(String mail, String mdp, String prenom, String nom){
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }
}