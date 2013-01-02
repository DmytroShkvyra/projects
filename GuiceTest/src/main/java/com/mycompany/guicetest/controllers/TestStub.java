/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guicetest.controllers;

import java.util.Random;

/**
 *
 * @author User
 */
public class TestStub {

  public int getRandom(){
      Random random  = new Random(System.nanoTime());
      return random.nextInt(System.identityHashCode(this));
  }

  
}
