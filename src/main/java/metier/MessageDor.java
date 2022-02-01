/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier;

/**
 *
 * @author steve
 */
public class MessageDor {
    private int id;
    private String pseudo;
    private String message;

    public MessageDor(int id, String pseudo, String message) {
        this.id = id;
        this.pseudo = pseudo;
        this.message = message;
    }

    public MessageDor(String pseudo, String message) {
        this.pseudo = pseudo;
        this.message = message;
    }
    
    public MessageDor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageDor{" + "id=" + id + ", pseudo=" + pseudo + ", message=" + message + '}';
    }
    
    
    
    
}
