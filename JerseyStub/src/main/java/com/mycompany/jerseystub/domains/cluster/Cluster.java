/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseystub.domains.cluster;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement
public class Cluster {
    
   @XmlElement public String version;

    public Cluster(String version) {
        this.version = version;
    }

    public Cluster() {
    }
   
   
}
