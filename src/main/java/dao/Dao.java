/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import metier.MessageDor;
/**
 *
 * @author steve
 */
public class Dao {
    /*--------Propriétés de connexion--------*/
    private static String URL = "jdbc:mysql://localhost:3306/db_21912808";
    private static String LOGIN = "";
    private static String PWD = "";
    private static Connection cx = null;
    
    
    /*--------Méthode de connexion--------*/
    private static void connexion() throws MyException {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException ex){
            throw new MyException("Message.Dao.connexion() - failed loading driver"+ex.getMessage());
        }
    
        try{
            Dao.cx = DriverManager.getConnection(URL,LOGIN,PWD);
        }
        catch(SQLException ex){
            throw new MyException("Message.Dao.connexion() - failed to connect"+ex.getMessage());
        }
    }
    /*--------Lire les message--------*/
    
    public static List<MessageDor> getMessages() throws MyException {
        List<MessageDor> liste = new ArrayList<>();
        
        if (cx == null){
            Dao.connexion();
        }
        
        
        String sql = "SELECT * FROM Message";
        
        try (PreparedStatement st = cx.prepareStatement(sql)){
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                liste.add(new MessageDor(rs.getInt("NumMsg"),rs.getString("Pseudo"),rs.getString("Texte")));
            }
            
        } catch (SQLException ex) {
            throw new MyException("Dao.enregistrer() - " + ex.getMessage());
        }
        
        
        return liste;
    }
    /*--------Ecrire/insérer un message--------*/
    public static int enregistrer(MessageDor msg) throws MyException {
        if (cx == null){
            Dao.connexion();
        }
        int nb = 0;
        
        
        String sql = "INSERT INTO Message (Pseudo, Texte) VALUEs (?,?)";
        
        
        try (PreparedStatement st = cx.prepareStatement(sql)){
            st.setString(1, msg.getPseudo());
            st.setString(2, msg.getMessage());
            
            nb = st.executeUpdate();
        } catch (SQLException ex) {
            throw new MyException("Dao.enregistrer() - " + ex.getMessage());
        }
        
        
        return nb;
    }
    
    /*--------Supprimer un message--------*/
    public static int supprimer(MessageDor msg) throws MyException {
        if (cx == null){
            Dao.connexion();
        }
        int nb = 0;
        
        
        String sql = "DELETE FROM Message where NumMsg=?";
        
        
        try (PreparedStatement st = cx.prepareStatement(sql)){
            st.setInt(1, msg.getId());
            
            nb = st.executeUpdate();
        } catch (SQLException ex) {
            throw new MyException("Dao.supprimer() - " + ex.getMessage());
        }
        
        return nb;
    }
    
    /*--------Modifier un message--------*/
    public static int modifier(MessageDor msg) throws MyException {
        if (cx == null){
            Dao.connexion();
        }
        int nb = 0;
        
        
        String sql = "UPDATE Message set Pseudo=?,Texte=? where NumMsg=?";
        
        
        try (PreparedStatement st = cx.prepareStatement(sql)){
            st.setString(1, msg.getPseudo());
            st.setString(2, msg.getMessage());
            st.setInt(3, msg.getId());
            
            nb = st.executeUpdate();
        } catch (SQLException ex) {
            throw new MyException("Dao.supprimer() - " + ex.getMessage());
        }
        
        return nb;
    }
    
    /*----------------*/
    public static void main(String[] args) {
        System.out.println("HELLO");
        try{
//            
//            MessageDor m = new MessageDor("Test","reussi");
//            int nb = Dao.enregistrer(m);
//            System.out.println("nb: " + nb);
            List<MessageDor> liste = Dao.getMessages();
            for (MessageDor m : liste){
                System.out.println(m);
            }
        }
        catch (MyException ex){
            System.out.println(ex.getMessage());
        }
    }
}
