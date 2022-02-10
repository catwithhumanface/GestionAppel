package metier;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresenceID that = (PresenceID) o;
        return idE == that.idE && idSC == that.idSC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idE, idSC);
    }

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
