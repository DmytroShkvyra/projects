/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseystub.domains.configurations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement
public class ConfigProperties {

    @XmlElement
    public String version;
    @XmlElement
    public String type;
    @XmlElement
    public String tag;
    @XmlElement(name = "fs.default.name")
    public String fs_default_name;
    @XmlElement(name = "dfs.datanode.data.dir.perm")
    public String dfs_datanode_data_dir_perm;
    @XmlElement
    public String hbase_hdfs_root_dir;
    @XmlElement(name = "mapred.job.tracker")
    public String mapred_job_tracker;
    @XmlElement(name = "mapreduce.history.server.embedded")
    public String mapreduce_history_server_embedded;
    @XmlElement(name = "mapreduce.history.server.http.address")
    public String mapreduce_history_server_http_address;
    @XmlElement(name = "hbase.rootdir")
    public String hbase_rootdir;
    @XmlElement(name = "hbase.cluster.distributed")
    public String hbase_cluster_distributed;  
    @XmlElement(name = "hbase.zookeeper.quorum")
    public String hbase_zookeeper_quorum;   
    @XmlElement(name = "zookeeper.session.timeout")
    public String zookeeper_session_timeout;
    @XmlElement
    public String nagios_web_login;
    @XmlElement
    public String nagios_web_password;        
    @XmlElement
    public String nagios_contact; 
    @XmlElement(name = "core-site")
    public String core_site;
    @XmlElement(name = "hdfs-site")
    public String hdfs_site;      
    @XmlElement
    public String global;
    @XmlElement(name = "mapred-site")
    public String mapred_site;
    @XmlElement(name = "hbase-site")
    public String hbase_site;
    @XmlElement(name = "hbase-env")
    public String hbase_env;
    @XmlElement(name = "nagios-global")
    public String nagios_global;
    
    @XmlElement
    public ConfigProperties properties;
    @XmlElement
    public ConfigProperties config;    
    
    

    public ConfigProperties() {
    }

    @Override
    public String toString() {
        try {
            return printMyself(this, new StringBuffer());
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String printMyself(ConfigProperties itself, StringBuffer buffer) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = itself.getClass().getFields();
        buffer.append("{");
        boolean firstcomma = false;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            XmlElement anotation = field.getAnnotation(XmlElement.class);
            if (anotation != null) {
                Object value = field.get(itself);
                if (field.getType().equals(String.class)) {
                    if (value != null) {
                        if (firstcomma) {
                            buffer.append(", ");
                        }
                        firstcomma = true;
                        if (anotation.name() != null && !anotation.name().equals("##default")) {
                            buffer.append(anotation.name());
                        } else {
                            buffer.append(field.getName());
                        }
                        buffer.append(" : ");
                        buffer.append(value);

                    }
                } else {
                    if (value != null) {
                        if (firstcomma) {
                            buffer.append(", ");
                        }
                        buffer.append("\n");
                        if (anotation.name() != null && !anotation.name().equals("##default")) {
                            buffer.append(anotation.name());
                        } else {
                            buffer.append(field.getName());
                        }
                        buffer.append(" : ");
                        printMyself((ConfigProperties) value, buffer);
                    }
                }
            }

        }
        buffer.append("}");
        return buffer.toString();
    }
}
