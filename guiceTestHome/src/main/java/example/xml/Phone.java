package example.xml;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class Phone {


  Contacts contacts;


    /**
     *
     * @param contacts
     */
    public void setContacts(Contacts contacts) {
    this.contacts = contacts;
  }

  public Contacts getContacts() {
    return contacts;
  }
}
