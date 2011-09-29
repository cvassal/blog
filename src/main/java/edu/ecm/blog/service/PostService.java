package edu.ecm.blog.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.ecm.blog.domain.Post;
import edu.ecm.blog.hibernate.HibernateUtil;

public class PostService {

	SessionFactory sessionFactory;
	
	public void save(Post post) {
		Session session = sessionFactory.openSession();

		session.save(post);

		session.close();
	}

	public void delete(Long id) {
		Session session = sessionFactory.openSession();

		session.delete(id);

		session.close();
	}

	public List find(int pageIndex, int pageSize) {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Post.class);

		// criteria.add(Restrictions.eq("", ));
		return null;
	}

	public int count() {
		Session session = sessionFactory.openSession();
		Long count = (Long) session.createQuery(
				"select count(*) from Post").uniqueResult();
		return count.intValue();

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
