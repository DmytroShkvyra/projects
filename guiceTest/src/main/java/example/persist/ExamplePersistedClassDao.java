/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.persist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import example.xml.Contact;
import example.xml.Contacts;
import javax.persistence.EntityManager;

/**
 *
 * @author USer
 */
public class ExamplePersistedClassDao {
  protected EntityManager entityManager;
  
  @Inject
  public ExamplePersistedClassDao(EntityManager entityManager, @Assisted Contacts contact) {
      System.out.println("contact = "+contact);
    this.entityManager = entityManager;
  }
  
  public void saveInNewTransaction(Contact object) {
    entityManager.getTransaction().begin();
    save(object);
    entityManager.getTransaction().commit();
  }
  
  public void save(Contact object) {
    entityManager.persist(object);
  }
  
  public Contact getByOtherField(String number) {
    return (Contact) entityManager
        .createQuery("select e from Contact e where e.phonenumber=:phonenumber")
        .setParameter("phonenumber", number)
        .getSingleResult();
  }    
}
