package edu.ecm.blog.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	public static final ThreadLocal session = new ThreadLocal();
	public static SessionFactory sessionFactory;
	
	public static Session currentSession() throws HibernateException {
	        //on recupere le contenu du threadLocal session si il possède déjà une Session s
		Session s = (Session) session.get();
		// Ouvre une nouvelle Session, si ce Thread n'en a aucune
		if (s == null) {
			s = sessionFactory.openSession();
	                //cet variable "session" est en fait de type ThreadLocal
	                //la méthode set() permet de lui faire sauvegarder la Session s ouverte
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException {
	        //on recupere la Session s dans le ThreadLocal session
		Session s = (Session) session.get();
	        //on efface le contenu du thread
		session.set(null);
	        //on ferme la Session s si elle existait dans le thradLocal session
		if (s != null)
			s.close();
	}
}
