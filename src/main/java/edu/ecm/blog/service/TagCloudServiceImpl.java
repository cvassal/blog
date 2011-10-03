package edu.ecm.blog.service;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Transactional;


import edu.ecm.blog.domain.Post;
import edu.ecm.blog.util.TagCloud;

public class TagCloudServiceImpl implements TagCloudService{

	@Inject
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public TagCloud buildTagCloud(){
		TagCloud tagCloud = new TagCloud();
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Post.class);
		List<Post> result = criteria.list();
		Iterator<Post> it = result.iterator();
		
		while(it.hasNext()){
			String[] tabStr = StringUtils.split(it.next().getTags(),",");
			for (String string : tabStr) {
				tagCloud.add(string);
			}
		}
		
		return tagCloud;
	}
	
	
}