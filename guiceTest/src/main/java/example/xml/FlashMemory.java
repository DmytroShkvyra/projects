package example.xml;

import com.google.inject.Provides;
import java.util.Collections;


public class FlashMemory implements Contacts {

  public Iterable<Contact> findByName(String name) {
    return Collections.emptyList();
  }
}
