/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseystub.domains.cluster;


import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement
public class Clusters {
    
   @XmlElement(name="Clusters") public Set<Cluster> clusters = new HashSet<Cluster>(); 
        
}
