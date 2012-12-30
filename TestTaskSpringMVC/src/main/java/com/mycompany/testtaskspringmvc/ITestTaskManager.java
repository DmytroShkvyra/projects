/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testtaskspringmvc;

import java.util.List;

/**
 *
 * @author USer
 */
public interface ITestTaskManager {
    
    	/**
	 * Get all accounts in the system
	 * @return all tasks
	 */
	public List<TestTask> getAllTasks();

	/**
	 * Find an tasks by its number.
	 * @param id the account id
	 * @return the Task
	 */
	public TestTask getTask(Integer id);

	/**
	 * Takes a changed task and persists any changes made to it.
	 * @param task  The account with changes
	 */
	public void update(TestTask task);
	
	
	/**
	 * Add task
	 * @param Task the status
	 */
	public Integer addTask(TestTask task);

	/**
	 * Removes task.
	 * @param id the account id
	 */
	public void removeTask(Integer id);
    
}
