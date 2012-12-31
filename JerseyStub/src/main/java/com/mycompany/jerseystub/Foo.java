package com.mycompany.jerseystub;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchema;

//@XmlSchema(namespace = "http://www.example.com/test";, elementFormDefault = XmlNsForm.QUALIFIED)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://www.example.com/test")
public class Foo {

	@XmlElement public String path;
	@XmlElement public String date;

	public Foo() {}

	public Foo(String path, String date) {
		this.path = path;
		this.date = date;
	}

    @Override
    public String toString() {
        return "class="+ this.getClass() +" path="+this.path+" date="+this.date;
    }

        
}
