package edu.ecm.blog.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;

import edu.ecm.blog.domain.Post;


public class PostServiceImpl implements PostService {

	@Inject
	public SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see edu.ecm.blog.service.PostService#save(edu.ecm.blog.domain.Post)
	 */
	@Override
	@Transactional
	public void save(Post post) {
	    Session session = sessionFactory.getCurrentSession();

	    session.save(post);
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.service.PostService#delete(java.lang.Long)
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Post post = (Post) session.load(Post.class, id);
		
		session.delete(post);
		
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.service.PostService#find(int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public List find(int pageIndex, int pageSize) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Post.class);
		List<Post> result = criteria.list();
		Iterator<Post> it = result.iterator();
		List<List<Post>> retour = new ArrayList<List<Post>>();
		List<Post> ssList = new ArrayList<Post>();
		int start = 0;
		while(start < pageSize && it.hasNext()) {
			start ++;
			ssList.add((Post) it.next());
			if(start == pageSize){
				retour.add(ssList);
				ssList = new ArrayList<Post>();
			}
		}
		if(retour.isEmpty()){
			retour.add(ssList);
		}
		if(!(retour.size() <= pageIndex)){
			return retour.get(pageIndex);
		} else {
			return new ArrayList<Post>(0);
		}
	}

	/* (non-Javadoc)
	 * @see edu.ecm.blog.service.PostService#count()
	 */
	@Override
	@Transactional(readOnly = true)
	public int count() {
		Session session = sessionFactory.getCurrentSession();
		Long count = (Long) session.createQuery(
				"select count(*) from Post").uniqueResult();
		return count.intValue();

	}
}
