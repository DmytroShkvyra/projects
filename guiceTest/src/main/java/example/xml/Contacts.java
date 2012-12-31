package example.xml;

import com.google.inject.ImplementedBy;

//@ImplementedBy(FlashMemory.class)
public interface Contacts {
  Iterable<Contact> findByName(String name);
}
