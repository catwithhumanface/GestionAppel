package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Scolarité")

public class Scolarite extends Utilisateur{
    public Scolarite(){}
    public Scolarite(String mail, String mdp, String prenom, String nom){
        super(mail,mdp,prenom,nom);
    }


}
