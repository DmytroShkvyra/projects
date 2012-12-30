package com.mycompany.testtaskspringmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class TestTaskController {

    private static final Logger logger = LoggerFactory
	    .getLogger(TestTaskController.class);
    private ITestTaskManager manager;

    @Autowired
    public TestTaskController(ITestTaskManager manager) {
	this.manager = manager;
    }

    TestTaskController() {
	//throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Selects the home page and populates the model with a message
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Model model) {
	return new ModelAndView("home");
    }

    @RequestMapping("/taskSummary")
    public String getTaskSummary(Model model) {
	model.addAttribute("tasks", manager.getAllTasks());
	return "summary";
    }

    @RequestMapping("/taskDetails")
    public String getTaskDetails(Integer entityId, Model model) {
	model.addAttribute("task", manager.getTask(entityId));
	return "details";
    }

    @RequestMapping("/deleteTask")
    public String deleteTask(Integer entityId) {
	manager.removeTask(entityId);
	return "redirect:taskSummary";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editTask")
    public void editTask(Integer entityId, Model model) {
	TestTask task = manager.getTask(entityId);
	model.addAttribute("task", task);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/editTask")
    public String postEditTask(TestTask task, BindingResult bindingResult, Model model) {
	validateTask(task, bindingResult);
	if (bindingResult.hasErrors()) {
	    model.addAttribute("task", task);
	    return "editTask";
	}
	manager.update(task);
	return "redirect:taskDetails?entityId=" + task.getEntityId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTask")
    public String postAddTask(TestTask task, BindingResult bindingResult, Model model) {
	validateTask(task, bindingResult);
	if (bindingResult.hasErrors()) {
	    model.addAttribute("task", task);
	    return "addTask";
	}

	return "redirect:taskDetails?entityId=" + manager.addTask(task);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addTask")
    public void addTask(Model model) {
	TestTask task = new TestTask("", "not started");
	model.addAttribute("task", task);

    }

    public void validateTask(TestTask task, Errors errors) {
	if (!StringUtils.hasText(task.getStatus())) {
	    errors.rejectValue("status", "empty.value", "required");
	}
	if (!StringUtils.hasText(task.getName())) {
	    errors.rejectValue("name", "empty.value", "required");
	}
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.setRequiredFields(new String[]{"status", "name"});
    }
}
