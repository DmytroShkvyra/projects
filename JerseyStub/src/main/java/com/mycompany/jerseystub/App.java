package com.mycompany.jerseystub;

import com.mycompany.jerseystub.domains.cluster.Cluster;
import com.mycompany.jerseystub.domains.cluster.Clusters;
import com.mycompany.jerseystub.domains.configurations.ConfigProperties;
import com.mycompany.jerseystub.restclients.ClusterService;
import com.mycompany.jerseystub.restclients.ConfigurationService;
import com.mycompany.jerseystub.restclients.ServiceService;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException
    {
        
        ClusterService client = new ClusterService();
        String s;
        Cluster cluster = new Cluster("HDP-1.2.0");
        Clusters clusters = new Clusters();
        clusters.clusters.add(cluster);
        String clusterName = "c1";
        System.out.println("-------------------------Create cluster---------------------------");
        s = client.postCreateCluster(String.class, clusterName, clusters);
        System.out.println("^ clusterName="+clusterName+" version="+cluster.version+" "+s);
        System.out.println("-------------------------Create service---------------------");
        String serviceName = "HDFS";
        ServiceService client1 = new ServiceService();
        s = client1.postCreateServices(String.class, clusterName, serviceName);
        System.out.println("^ clusterName="+clusterName+" serviceName="+serviceName+" "+s);
        serviceName = "MAPREDUCE";
        s = client1.postCreateServices(String.class, clusterName, serviceName);
        System.out.println("^ clusterName="+clusterName+" serviceName="+serviceName+" "+s);
        serviceName = "ZOOKEEPER";
        s = client1.postCreateServices(String.class, clusterName, serviceName);
        System.out.println("^ clusterName="+clusterName+" serviceName="+serviceName+" "+s);
        serviceName = "HBASE";
        s = client1.postCreateServices(String.class, clusterName, serviceName);
        System.out.println("^ clusterName="+clusterName+" serviceName="+serviceName+" "+s);
        serviceName = "GANGLIA";
        s = client1.postCreateServices(String.class, clusterName, serviceName);
        System.out.println("^ clusterName="+clusterName+" serviceName="+serviceName+" "+s);
        serviceName = "NAGIOS";
        s = client1.postCreateServices(String.class, clusterName, serviceName);
        System.out.println("^ clusterName="+clusterName+" serviceName="+serviceName+" "+s);
        System.out.println("-------------------------Config cluster---------------------");
        ConfigurationService client2 = new ConfigurationService();
        ConfigProperties configurations = new ConfigProperties();
        configurations.type = "core-site";
        configurations.tag = "version1";
        ConfigProperties properties = new ConfigProperties();
        properties.fs_default_name = "localhost:8020";
        configurations.properties = properties;
        s = client2.postCreateConfigurations(String.class, clusterName, configurations);
        System.out.println("^ clusterName="+clusterName+" configurations="+configurations+" "+s);
        System.out.println("---------------------------------------------------------------");
        configurations = new ConfigProperties();
        configurations.type = "hdfs-site";
        configurations.tag = "version1";
        properties = new ConfigProperties();
        properties.dfs_datanode_data_dir_perm = "750";
        configurations.properties = properties;
        s = client2.postCreateConfigurations(String.class, clusterName, configurations);
        System.out.println("^ clusterName="+clusterName+" configurations="+configurations+" "+s);
        System.out.println("---------------------------------------------------------------");        
        /*Foo f = client.getJSON(Foo.class, "HHHH");
        System.out.println(f);
        System.out.println("---------------------------------------------------------------");
        try{
        s = client.getTEXT(String.class, "HHHH");
        }catch(UniformInterfaceException e){
         s = e.getMessage();
        }
        System.out.println(s);
        System.out.println("---------------------------------------------------------------");
        f = null;
        try{
        f = client.getXML(Foo.class, "FOOOO");
        }catch(Exception ex){}
        System.out.println(f);
        System.out.println("---------------------------------------------------------------");
        UniformInterfaceException s2 = client.throwErrorHTML(UniformInterfaceException.class);
        System.out.println(s2);        
        System.out.println("---------------------------------------------------------------");
        NewJerseyClient client1 = new NewJerseyClient();
        s = client1.getTEXT(String.class, "POSTTTT");
        System.out.println(s);
        client1.close();
        client.close();*/
    }
}
