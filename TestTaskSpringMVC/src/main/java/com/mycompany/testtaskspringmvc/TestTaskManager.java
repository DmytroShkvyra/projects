/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testtaskspringmvc;

import java.util.ArrayList;
import java.lang.Integer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Parameter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USer
 */
@Component
public class TestTaskManager implements ITestTaskManager {

    private static final Logger logger = LoggerFactory
	    .getLogger(TestTaskManager.class);
    private SessionFactory sessionFactory;
    private EntityManagerFactory emf;
    private JpaTemplate jpaTemplate;

    @Autowired
    public void setJpaTemplate(JpaTemplate jpaTemplate) {
	this.jpaTemplate = jpaTemplate;
	logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@jpaTemplate="+jpaTemplate);
    }
    

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
	this.emf = emf;
    }
    
    public TestTaskManager() {
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
	logger.debug("SessionFactory = " + sessionFactory);
	this.sessionFactory = sessionFactory;
    }

    public TestTaskManager(SessionFactory sessionFactory) {
	logger.debug("Constructor  TestTaskManager SessionFactory = " + sessionFactory);
	this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void update(TestTask task) {
	//Hibernate style
	//getCurrentSession().update(task);
	//JPA style
	/*EntityManager entityManager = emf.createEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.merge(task);
	transaction.commit();
	entityManager.close();*/
	//JPA template style
	jpaTemplate.merge(task);
    }

    @Override
    @Transactional
    public Integer addTask(TestTask task) {
	//Hibernate style
	/*getCurrentSession().persist(task);
	logger.debug("new taskid = "+task.getEntityId());*/
	//JPA style
	/*EntityManager entityManager = emf.createEntityManager();
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.persist(task);
	transaction.commit();
	entityManager.close();
	return task.getEntityId();*/
	//JPA template style
	jpaTemplate.persist(task);
	return task.getEntityId();
    }

    @Override
    @Transactional
    public void removeTask(Integer id) {
	//Hibernate style
	/*String hqlDelete = "delete TestTask t where t.entityId = :id";
	int deletedEntities = getCurrentSession().createQuery( hqlDelete )
        .setInteger( "id", id ).executeUpdate();
	logger.debug("removed "+ deletedEntities +" tasks");*/
	//JPA style
	/*EntityManager entityManager = emf.createEntityManager();
	TestTask task = entityManager.find(TestTask.class, id);
	EntityTransaction transaction = entityManager.getTransaction();
	transaction.begin();
	entityManager.remove(task);
	transaction.commit();
	entityManager.close();*/
	//JPA template style
	TestTask task = jpaTemplate.find(TestTask.class, id);
	jpaTemplate.remove(task);
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<TestTask> getAllTasks() {
	//Hibernate style
	//return getCurrentSession().createQuery("from TestTask").list();
	//JPA style
	/*EntityManager entityManager = emf.createEntityManager();
	return entityManager.createQuery("from TestTask").getResultList();*/
	//JPA Template style
	return jpaTemplate.find("from TestTask");
    }

    @Override
    @Transactional(readOnly = true)
    public TestTask getTask(Integer id) {
	//Hibernate style
	/*String hql = "from TestTask as task where task.entityId = :id";
	List<TestTask> res = getCurrentSession().createQuery( hql ).setInteger( "id", id ).list();
	return (res.size()>0)? res.get(0): null;*/
	//JPA style
	/*EntityManager entityManager = emf.createEntityManager();
	return entityManager.find(TestTask.class, id);*/
	//JPA template style
	return jpaTemplate.find(TestTask.class, id);
    }

    /**
     * Returns the session associated with the ongoing reward transaction.
     *
     * @return the transactional session
     */
    protected Session getCurrentSession() {
	return sessionFactory.getCurrentSession();
    }
}
