package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Scolarite")

public class Scolarite extends Utilisateur{
    public Scolarite(){}
    public Scolarite(String mail, String mdp, String prenom, String nom, String photoUrl){
        super(mail,mdp,prenom,nom, photoUrl);
    }


}
