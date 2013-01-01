/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guicetest.controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

/**
 *
 * @author User
 */
public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TestStub.class).toInstance(new TestStub());
        bind(String.class);
        bind(ExceptionWrapper.class).in(Scopes.SINGLETON);
    }


}
