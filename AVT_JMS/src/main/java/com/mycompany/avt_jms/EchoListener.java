/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avt_jms;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jms.core.JmsTemplate;

/**
 *
 * @author USer
 */
public class EchoListener implements IListener {
    
    private JmsTemplate jmsTemplate;
    
    Map<String, Object> map = new HashMap<String, Object>();

    public JmsTemplate getJmsTemplate() {
	return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
	this.jmsTemplate = jmsTemplate;
    }
    
    @Override
    public String echo(String msg){
	map.put(msg, msg);
	    /*System.out.println("before");
	try {
	    Thread.currentThread().join(10000);
	} catch (InterruptedException ex) {
	    Logger.getLogger(EchoListener.class.getName()).log(Level.SEVERE, null, ex);
	}
	System.out.println("out");*/
	return msg + " echo";
	//jmsTemplate.convertAndSend(msg+"echo");
    }
    
}
