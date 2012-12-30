/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
 
@Aspect
public class LoggingAspect {
 
	/*@Before("execution(* com.mycompany.avt_jms.IListener.echo(..))")
	public void logBefore(JoinPoint joinPoint) {
 
		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}*/
	
 
	@Before("execution(* com.mycompany.testtaskspringmvc.ITestTaskManager.*(..))")
	public void logAfter(JoinPoint joinPoint) {
 
		System.out.println("logAfter() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		
		System.out.println("******"+joinPoint.getArgs());
		System.out.println("******"+joinPoint.getTarget());
 
	}
}
