/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testtaskspringmvc;

import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author USer
 */
public class TestTaskManagerTest {

    private ITestTaskManager manager;
    private PlatformTransactionManager transactionManager;
    private TransactionStatus transactionStatus;

    public TestTaskManagerTest() {
    }

    private SessionFactory createTestSessionFactory() throws Exception {
	// create a FactoryBean to help create a Hibernate SessionFactory
	AnnotationSessionFactoryBean factoryBean = new AnnotationSessionFactoryBean();
	factoryBean.setDataSource(createTestDataSource());
	factoryBean.setAnnotatedClasses(new Class[]{TestTask.class});
	factoryBean.setHibernateProperties(createHibernateProperties());
	// initialize according to the Spring InitializingBean contract
	factoryBean.afterPropertiesSet();
	// get the created session factory
	return (SessionFactory) factoryBean.getObject();
    }

    private DataSource createTestDataSource() {
	return new EmbeddedDatabaseBuilder()
		.setName("dataSource")
		.addScript("classpath:testdb/schema.sql")
		.addScript("classpath:testdb/test-data.sql")
		.build();
    }

    private Properties createHibernateProperties() {
	Properties properties = new Properties();
	// turn on formatted SQL logging (very useful to verify Hibernate is
	// issuing proper SQL)
	properties.setProperty("hibernate.show_sql", "true");
	properties.setProperty("hibernate.format_sql", "true");
	return properties;
    }

    @Before
    public void setUp() throws Exception {
	// setup the repository to test
	SessionFactory sessionFactory = createTestSessionFactory();
	manager = new TestTaskManager(sessionFactory);
	// begin a transaction
	transactionManager = new HibernateTransactionManager(sessionFactory);
	transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @After
    public void tearDown() {
	// rollback the transaction to avoid corrupting other tests
	if (transactionManager != null) {
	    transactionManager.rollback(transactionStatus);
	}
    }

    /**
     * Test of update method, of class TestTaskManager.
     */
    //@Test
    public void testUpdate() {
	System.out.println("update");
	TestTask task = new TestTask("Name", "status");
	task.setEntityId(0);
	manager.update(task);
	TestTask task1 = manager.getTask(0);
	assertArrayEquals("Task.name was not update", task.getName().getBytes(), task1.getName().getBytes());
	assertArrayEquals("Task.status was not update", task.getStatus().getBytes(), task1.getStatus().getBytes());
    }

    /**
     * Test of addTask method, of class TestTaskManager.
     */
    //@Test
    public void testAddTask() {
	System.out.println("addTask");
	TestTask task = new TestTask("Name", "status");
	Integer result = manager.addTask(task);
	assertNotNull("Task was not added", result);
    }

    /**
     * Test of removeTask method, of class TestTaskManager.
     */
    //@Test
    public void testRemoveTask() {
	System.out.println("removeTask");
	Integer id = 0;
	manager.removeTask(id);
	TestTask res = manager.getTask(id);
	assertNull("Task was not removed", res);
    }

    /**
     * Test of getAllTasks method, of class TestTaskManager.
     */
    //@Test
    public void testGetAllTasks() {
	System.out.println("getAllTasks");
	List result = manager.getAllTasks();
	assertTrue("Can't get tasks", result.size() > 0);
    }

    /**
     * Test of getTask method, of class TestTaskManager.
     */
    //@Test
    public void testGetTask() {
	System.out.println("getTask");
	Integer id = 0;
	TestTask result = manager.getTask(id);
	assertNotNull("Can't get task 0", result);
    }
}
