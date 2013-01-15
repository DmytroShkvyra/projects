/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseystub;

import com.mycompany.jerseystub.domains.configurations.RegistrationResponse;
import com.mycompany.jerseystub.restclients.AgentResource;
import com.mycompany.jerseystub.restclients.ServiceService;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import org.apache.ambari.server.agent.HeartBeatResponse;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
public class AgentSim extends TimerTask {

    private static Logger LOG = LoggerFactory.getLogger(ServiceService.class);
    private Timer timer;
    private JSONObject data;
    private AgentResource resource = new AgentResource();
    private HeartBeatResponse beatResponse;
    private RegistrationResponse registrationResponse = new RegistrationResponse();
    private String regestryData = "{\"hardwareProfile\": {\"lsbrelease\": \":core-4.0-amd64:core-4.0-noarch:graphics-4.0-amd64:graphics-4.0-noarch:printing-4.0-amd64:printing-4.0-noarch\", \"ipaddress_lo\": \"127.0.0.1\", \"selinux\": \"true\", \"memorytotal\": 7644119, \"swapfree\": \"0.00 kB\", \"processorcount\": \"2\", \"ec2_public_ipv4\": \"54.242.113.69\", \"virtual\": \"physical\", \"operatingsystem\": \"RedHat\", \"netmask_lo\": \"255.0.0.0\", \"lsbmajdistrelease\": \"6\", \"rubyversion\": \"1.8.7\", \"kernelrelease\": \"2.6.32-276.el6.x86_64\", \"facterversion\": \"1.6.10\", \"is_virtual\": false, \"network_lo\": \"127.0.0.0\", \"ec2_block_device_mapping_root\": \"/dev/sda1\", \"memoryfree\": 7434403, \"uptime_seconds\": \"3581\", \"ec2_reservation_id\": \"r-a45a5bdc\", \"sshdsakey\": \"AAAAB3NzaC1kc3MAAACBALTiLMZymPqmMFgMuK1OjT/QdWaf08BNanzzbrYzNlemIUShds3UIOlhMP20bbylznSTMwStXCo+71wqnMyHaDoexWyl2vNQvvjg3OOUI5RSLB5ElIusRSxY21lkVVK9cKiFqmEYE5HB38eKvhNFOAO6wPbKkV6SGi7U1QTzJBvjAAAAFQC4CRJ10VYudsX0mZ9NM0n/V8B6ZQAAAIAh5WIsel3oel8vmofBt4xNPkrB/PvzEd3D5rZGu+/6pchVvg+RhgEWKHSiG4vMn9yOkbYpMIeueyaOiK/uY4FBYG3/V8noZRoXQ09I2Lp9LUzNqDHm3cH4xFYxm1TLCKVmSEd84drUFAFTDzw4/If96ater6JOBMmpeBDYlp8MewAAAIAf4wgag5LmuklN5RWirTGn7KD5WIKsSVISJMnGXnxo13w7dfQ0pZdZONawdfUQjq1jpMeVn+uoEq2MdC7S5h3i6mN8gCalbK8sXNnKzJ65I89lzSCB16m1sYx+Z3chFHaOulKEYoVhHPVWBLHu7YE9fYnoojPFmU6yaChsWdTp0g==\", \"ec2_block_device_mapping_ami\": \"/dev/sda1\", \"selinux_enforced\": \"true\", \"memorysize\": 7644119, \"swapsize\": \"0.00 kB\", \"ec2_public_keys_0_openssh_key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC4YhcNwxMvZLyLECWfJyqf5rdk+R+DM2gzt0cEFYu9/SVV3GWAGvESevGCMEZaqapMDWgY+9n5uFgQRKo8UeVH1cJuRqQUZOY44ZiZokiMZ+kkY5rPOj44ArKXvqQDz0DB1EVyMYB8LATjloDtcghl51Z/y2hXMjaxpewYokTh8YTeMPvyBYvvIuRIW0EOMMDLXfR3EXaLwQAtlDjWkQYezFnkNM4lQsYJ50ohb/DA68ZCBhvTYqPYPbFmeNHubt/ymucecDaAJFbwmdHf6j+8xbT/HH/4GbCUXgU9RNCKgJfBlOcgEEBCy0cbg/hFz2sawMA+epuX4OhY2s9atugV ec2-keypair\", \"uniqueid\": \"760ae35a\", \"kernelmajversion\": \"2.6\", \"macaddress\": \"12:31:3D:08:4D:15\", \"ec2_hostname\": \"ip-10-118-90-227.ec2.internal\", \"lsbdistid\": \"RedHatEnterpriseServer\", \"network_eth0\": \"10.118.90.0\", \"uptime_hours\": \"0\", \"ec2_security_groups\": \"default\", \"rubysitedir\": \"/usr/lib/ambari-agent/lib/ruby-1.8.7-p370/lib/ruby/site_ruby/1.8\", \"architecture\": \"x86_64\", \"netmask_eth0\": \"255.255.254.0\", \"mounts\": [{\"available\": \"3960980\", \"used\": \"1884736\", \"percent\": \"33%\", \"device\": \"/dev/xvde1\", \"mountpoint\": \"/\", \"type\": \"ext4\", \"size\": \"5905712\"}, {\"available\": \"3823076\", \"used\": \"0\", \"percent\": \"0%\", \"device\": \"none\", \"mountpoint\": \"/dev/shm\", \"type\": \"tmpfs\", \"size\": \"3823076\"}, {\"available\": \"411234588\", \"used\": \"203012\", \"percent\": \"1%\", \"device\": \"/dev/xvdg\", \"mountpoint\": \"/grid/0\", \"type\": \"ext3\", \"size\": \"433455904\"}, {\"available\": \"411234588\", \"used\": \"203012\", \"percent\": \"1%\", \"device\": \"/dev/xvdf\", \"mountpoint\": \"/grid/1\", \"type\": \"ext3\", \"size\": \"433455904\"}], \"arp\": \"fe:ff:ff:ff:ff:ff\", \"kernel\": \"Linux\", \"domain\": \"ec2.internal\", \"selinux_mode\": \"targeted\", \"uptime_days\": \"0\", \"ec2_ami_launch_index\": \"1\", \"ec2_public_hostname\": \"ec2-54-242-113-69.compute-1.amazonaws.com\", \"selinux_config_policy\": \"targeted\", \"ec2_instance_type\": \"m1.large\", \"ec2_profile\": \"default-paravirtual\", \"selinux_config_mode\": \"enforcing\", \"timezone\": \"EST\", \"hardwareisa\": \"x86_64\", \"id\": \"root\", \"ec2_ami_id\": \"ami-89ad18e0\", \"ec2_local_hostname\": \"ip-10-118-90-227.ec2.internal\", \"uptime\": \"0:59 hours\", \"macaddress_eth0\": \"12:31:3D:08:4D:15\", \"hostname\": \"dshkvyra\", \"ec2_block_device_mapping_ephemeral1\": \"/dev/sdc\", \"ec2_block_device_mapping_ephemeral0\": \"/dev/sdb\", \"ec2_placement_availability_zone\": \"us-east-1c\", \"ec2_ami_manifest_path\": \"(unknown)\", \"ec2_instance_id\": \"i-cdfd7dbc\", \"ec2_local_ipv4\": \"10.118.90.227\", \"arp_eth0\": \"fe:ff:ff:ff:ff:ff\", \"hardwaremodel\": \"x86_64\", \"osfamily\": \"RedHat\", \"sshrsakey\": \"AAAAB3NzaC1yc2EAAAABIwAAAQEAo7SokPyUZuOzWvTRArcEeC/DqdKv+qGrIEcG9rb08C7U8ephWT5YOtweZ+0Lc8q8hs+IJemycDAO/5sCAeQ1sYWPqZ+bet92fpwe7ONec9iKSHIjAvAuzi8gXd4O9BXYcQCJVfB/iizasglyacDtpYknqbS2PaQCwciZTythtcYw6sX96hEiq+gIfThlW3RmLzJISKDin3AZrqNi9P7N6yKXy+98d8dfLwtvH+sxUw9AM7qcqdY9kKMrkJM4LvwdlkVMeIV4Ec7NUTvL4I7L23AclxMe01AXrvztPTHREyY2kfEEQas8EbWy02g7A9ziSzeBhcwiG9bpLmRal1OlNQ==\", \"ps\": \"ps -ef\", \"physicalprocessorcount\": 1, \"interfaces\": \"eth0,lo\", \"selinux_policyversion\": \"24\", \"ec2_kernel_id\": \"aki-08ed0761\", \"path\": \"/usr/lib/ambari-agent/lib/ruby-1.8.7-p370/bin:/usr/lib/ambari-server/*:/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin\", \"ipaddress\": \"10.118.90.227\", \"lsbdistdescription\": \"Red Hat Enterprise Linux Server release 6.3 (Santiago)\", \"kernelversion\": \"2.6.32\", \"operatingsystemrelease\": \"6.3\", \"processor0\": \"Intel(R) Xeon(R) CPU           E5507  @ 2.27GHz\", \"processor1\": \"Intel(R) Xeon(R) CPU           E5507  @ 2.27GHz\", \"fqdn\": \"ip-10-118-90-227.ec2.internal\", \"lsbdistcodename\": \"Santiago\", \"lsbdistrelease\": \"6.3\", \"ipaddress_eth0\": \"10.118.90.227\", \"selinux_current_mode\": \"enforcing\", \"netmask\": \"255.255.254.0\"}, \"timestamp\": " + System.currentTimeMillis() + ", \"hostname\": \"dshkvyra\", \"responseId\": -1, \"publicHostname\": \"dshkvyra\"}";
    private long responseId = 0;
    private String heartBeatData = "{'componentStatus': [],'hostname': 'dshkvyra','nodeStatus': {'cause': 'NONE', 'status': 'HEALTHY'}, 'reports': [], 'responseId': 0, 'timestamp': " + System.currentTimeMillis() + "}";
    private String hostname = "dshkvyra";

    public AgentSim() throws JSONException, InterruptedException {
        data = new JSONObject(regestryData);
        data.put("hostname", hostname);
        data.put("publicHostname", hostname);
        JSONObject hardwareProfile = (JSONObject) data.get("hardwareProfile");
        hardwareProfile.put("hostname", hostname);
        LOG.info("--------------------------------------------------------\n" + data.toString());
        registrationResponse = resource.postRegister(RegistrationResponse.class, hostname, data);
        responseId = registrationResponse.getResponseId();
        LOG.info(registrationResponse.toString() + "\n--------------------------------------------------------");
        timer = new Timer();
        timer.schedule(this, 1000, 3000);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException, InterruptedException {
        new AgentSim();
    }

    public void run() {
            try {
                data = new JSONObject(heartBeatData);
                data.put("hostname", hostname);
                data.put("responseId", responseId);
            } catch (JSONException ex) {
                java.util.logging.Logger.getLogger(AgentSim.class.getName()).log(Level.SEVERE, null, ex);
            }
            LOG.info("--------------------------------------------------------\n" + data.toString());
            beatResponse = resource.postHeartbeat(HeartBeatResponse.class, hostname, data);
            responseId = beatResponse.getResponseId();
            LOG.info(beatResponse.toString() + "\n--------------------------------------------------------");
        }
}
