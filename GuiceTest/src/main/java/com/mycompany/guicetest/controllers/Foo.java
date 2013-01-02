package com.mycompany.guicetest.controllers;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;

@javax.xml.bind.annotation.XmlRootElement(namespace="http://www.example.com/test")
public class Foo {

	@XmlElement private String path;
	@XmlElement private String date;

	public Foo() {}

	public Foo(String path, String date) {
		this.path = path;
		this.date = date;
	}

	public String getPath() { return path; }
	public void setPath(String value) { path = value; }

	public String getDate() { return date; }
	public void setDate(String value) { date = value; }
}
