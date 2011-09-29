package edu.ecm.blog.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ecm.blog.domain.Author;
import edu.ecm.blog.domain.Post;
import edu.ecm.blog.service.PostService;

public class PostServiceTest {
   private SessionFactory sessionFactory;

   @Before
   public void createSessionFactory() {
      Configuration configuration = new Configuration();

      configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
      configuration.setProperty("hibernate.connection.url", "jdbc:derby:target/testdb;create=true");
      configuration.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
      configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");

      configuration.addAnnotatedClass(Author.class);
      configuration.addAnnotatedClass(Post.class);

      sessionFactory = configuration.buildSessionFactory();
   }

   @After
   public void cleanDb() {
      Session session = sessionFactory.openSession();

      Transaction transaction = session.beginTransaction();

      session.createQuery("delete from Post").executeUpdate();

      transaction.commit();

      session.close();

      sessionFactory.close();
   }
   @Test
   public void save() {
       PostService postService = new PostService();
       postService.setSessionFactory(sessionFactory);

       Post post = new Post();
       post.setTitle("un post");
       post.setDate(new Date());

       postService.save(post);
   }
}