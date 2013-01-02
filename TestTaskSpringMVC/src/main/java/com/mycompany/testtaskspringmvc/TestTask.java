/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testtaskspringmvc;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author USer
 */
@Entity
@Table(name = "T_TASK")
public class TestTask implements Serializable{

    	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer entityId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "NAME")
	private String name;
	

    public TestTask() {
    }

    public TestTask(String name, String status) {
	this.status = status;
	this.name = name;
    }

    public Integer getEntityId() {
	return entityId;
    }

    public void setEntityId(Integer entityId) {
	this.entityId = entityId;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

	
    
}
