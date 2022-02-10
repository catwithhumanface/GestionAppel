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
    public int idE;

    @Column(name = "IdSC")
    public int idSC;

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

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdSC() {
        return idSC;
    }

    public void setIdSC(int idSC) {
        this.idSC = idSC;
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
