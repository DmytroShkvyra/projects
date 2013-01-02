package example.xml;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {
    
  @Id
  @GeneratedValue
  private Long id;
  
  private String phonenumber;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPhonenumber() {
	return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
    }
  
  

}
