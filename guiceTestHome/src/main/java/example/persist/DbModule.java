/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.persist;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author USer
 */
public class DbModule  extends AbstractModule {

  private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
      = new ThreadLocal<EntityManager>();

  public void configure() {
  }

  @Provides @Singleton
  public EntityManagerFactory provideEntityManagerFactory() {
    Map<String, String> properties = new HashMap<String, String>();
    properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
    properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:mem:app-db");
    properties.put("javax.persistence.jdbc.user", "sa");
    properties.put("javax.persistence.jdbc.password", "");
    properties.put("eclipselink.ddl-generation", "create-tables");

    return Persistence.createEntityManagerFactory("test", properties);
  }
  
  @Provides
  public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
    EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
    if (entityManager == null) {
      ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
    }
    return entityManager;
  }

    
}
