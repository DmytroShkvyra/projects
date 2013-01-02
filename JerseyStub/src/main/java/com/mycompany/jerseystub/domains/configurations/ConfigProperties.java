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
    @XmlElement
    public ConfigProperties properties;
    

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
