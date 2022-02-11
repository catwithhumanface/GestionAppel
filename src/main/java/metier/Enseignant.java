package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Enseignant")

public class Enseignant extends Utilisateur{
    public Enseignant(){}
    public Enseignant(String mail, String mdp, String prenom, String nom, String photoUrl){
        super(mail,mdp,prenom,nom, photoUrl);
    }


}
