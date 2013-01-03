package com.mycompany.avt_jms;

import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;

/**
 * Hello world!
 *
 */
public class App  extends Frame 
{
    TextField text1;
    TextArea area1;
    Label label1;
    Button button1;
    
    @Autowired
    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
	return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
	this.jmsTemplate = jmsTemplate;
    }
    
    
    
    private static final Log log = LogFactory.getLog(App.class);
    

    public App() {
        setLayout(new FlowLayout());
        setBackground(Color.green);
        label1 = new Label("Type Name: ");
        text1 = new TextField(25);
        area1 = new TextArea(10, 50);
        button1 = new Button("Exit");
        button1.setBackground(Color.red);
        add(label1);
        add(text1);
        add(area1);
        add(button1);
	setSize(700, 500);
        setVisible(true);
    }
    

    @Override
    public boolean action(Event e, Object c) {
        if (e.target == text1) {
            try {
                jmsTemplate.convertAndSend(text1.getText());
            } catch (Exception e1) {
                area1.append("" + e1);
            }
        }
        if (e.target == button1) {
            System.exit(0);
        }
        return true;
    }    

    public void log(String msg) {
	area1.append(msg+"\n");
    }
    
    public static void main( String[] args )
    {
	ApplicationContext context = new ClassPathXmlApplicationContext("com/mycompany/avt_jms/system-config.xml");

    }
}
