package model;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



/**
 * Chargement de la configuration et création de la SessionFactory.
 * (hibernate.cfg.xml)
 */
public class HibernateUtil
{
    private static final SessionFactory SESSION_FACTORY;

    static
    {
        try	{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            configuration.addAnnotatedClass(metier.Utilisateur.class);
            configuration.addAnnotatedClass(metier.Cours.class);
            configuration.addAnnotatedClass(metier.Etudiant.class);
            configuration.addAnnotatedClass(metier.SeanceCours.class);
            configuration.addAnnotatedClass(metier.Enseignant.class);
            configuration.addAnnotatedClass(metier.Scolarite.class);
            configuration.addAnnotatedClass(metier.Presence.class);
            configuration.addAnnotatedClass(metier.Justificatif.class);
           // configuration.addAnnotatedClass(metier.PresenceID.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
            SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
        }
        catch (HibernateException ex)
        {
            /*----- Exception -----*/
            System.err.println("Initial SessionFactory creation failed.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory () { return SESSION_FACTORY; }

} /*----- Fin de la classe HibernateUtil -----*/
