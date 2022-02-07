package metier;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Etudiant")
/**
 * Relations.
 */

public class Etudiant extends Utilisateur{
    @ManyToMany
    @JoinTable(name = "CoursEtudiant",
            joinColumns = @JoinColumn(name = "IdE"),
            inverseJoinColumns = @JoinColumn(name = "IdC"))
    private Set<Cours> lesCours = new HashSet(0);

    /*----- Presence -----*/
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "IdSC", updatable = false, insertable = false)
    private Map<SeanceCours,Presence> presences= new HashMap(0);

    public Map<SeanceCours, Presence> getPresences() {
        return presences;
    }

    public void setPresences(Map<SeanceCours, Presence> presences) {
        this.presences = presences;
    }

    public Etudiant(){}
    public Etudiant(String mail,String mdp,String prenom,String nom){
        super(mail,mdp,prenom,nom);
    }

    public Set<Cours> getLesCours() {return lesCours;}
    public void setLesCours(Set<Cours> lesCours) {this.lesCours = lesCours;}

    public void ajouteCours (Cours c)
    {
        this.lesCours.add(c);
        c.getEtudiants().add(this);
    }

    public void present (SeanceCours sc1, String etatPre)
    {
        Presence pre1 = (Presence) this.presences.get(sc1);
        if (pre1 == null)
        {
            /*----- La décision est créée et ajoutée -----*/
            Presence preAdd = new Presence(new PresenceID(this.getIdE(),sc1.getIdSC()),etatPre,sc1,this);
            this.presences.put(sc1,preAdd);
            sc1.getPresences().put(this,preAdd);
        }
        else
        {
            /*----- Mise à jour de la décision -----*/
            pre1.setEtatP(etatPre);
        }
    }


    @Override
    public String toString() {
        return "Etudiant{" + "lesCours=" + lesCours + '}';
    }

}
