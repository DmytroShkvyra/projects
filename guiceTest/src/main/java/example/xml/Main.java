/**
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example.xml;

import com.google.inject.Guice;
import com.google.inject.AbstractModule;

import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import example.persist.DbModule;
import example.persist.ExamplePersistedClassDao;
import java.net.URL;

/**
 *
 *
 */
public class Main {

  public static void main(String[] args) {
    final URL xmlUrl = Main.class.getResource("phone.xml");

    Injector injector;
	injector = Guice.createInjector(new AbstractModule() {
 protected void configure() {
   bind(Contacts.class).to(SimCard.class);
     //bind(Contacts.class).annotatedWith(FromSim.class).to(SimCard.class);
     bind(Contacts.class).annotatedWith(Assisted.class).to(FlashMemory.class);
   //bind(Contacts.class).toProvider(Provider<FlashMemory.class>);
   install(new XmlBeanModule(xmlUrl));
 }
}, new DbModule());

    	
	
    Phone phone = injector.getInstance(Phone.class);
    ExamplePersistedClassDao examplePersistedClassDao = injector.getInstance(ExamplePersistedClassDao.class);
    Contact contact = new Contact();
    contact.setPhonenumber("+380503576828");
    examplePersistedClassDao.saveInNewTransaction(contact);
    contact = examplePersistedClassDao.getByOtherField("+380503576828");
    System.out.println("contact.getId = "+contact.getId());
    

    if (phone.getContacts() == null) {
      throw new AssertionError();
    } else {
      System.out.println("It worked! Class of contacts = "+phone.getContacts().getClass());
    }
  }
}
