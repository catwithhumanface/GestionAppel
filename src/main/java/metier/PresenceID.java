package metier;


import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * Clé composite PresenceID
 */
@Embeddable
public class PresenceID implements java.io.Serializable{
    /**
     * Propriétés.
     */
    @Column(name = "IdE")
    private int idE;

    @Column(name = "IdSC")
    private int idSC;

  /*  @Column(name = "IdJ",nullable = true)
    private int idJ;*/

    public PresenceID(){}
    public PresenceID(int idE, int idSC){
        this.idE = idE;
        this.idSC = idSC;
    }
/*
    public PresenceID(int idE,int idSC, int idJ){
        this.idE = idE;
        this.idSC = idSC;
        this.idJ = idJ;
    }
*/


}
