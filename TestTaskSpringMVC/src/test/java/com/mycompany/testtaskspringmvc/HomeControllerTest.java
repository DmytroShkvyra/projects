package com.mycompany.testtaskspringmvc;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;


public class HomeControllerTest {

	@Test
	public void testController() {
		TestTaskController controller = new TestTaskController();
		Model model = new ExtendedModelMap();
		//Assert.assertEquals("home",controller.home(model));
		Assert.assertNotNull(controller.home(model));
		
	}
}
